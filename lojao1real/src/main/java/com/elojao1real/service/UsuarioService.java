package com.elojao1real.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elojao1real.Model.UsuarioLogin;
import com.elojao1real.Model.UsuarioModel;
import com.elojao1real.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.of(usuarioRepository.save(usuario));
    
    }

    public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {
        
        if(usuarioRepository.findById(usuario.getId()).isPresent()) {

            Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

            if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.ofNullable(usuarioRepository.save(usuario));
            
        }

        return Optional.empty();
    
    }   

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

        Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

        if (usuario.isPresent()) {

            if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

            	usuarioLogin.get().setId(usuario.get().getId());
    			usuarioLogin.get().setNomeUsuario(usuario.get().getNomeUsuario());
    			usuarioLogin.get().setFoto(usuario.get().getFoto());
    			usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));
    			usuarioLogin.get().setSenha(usuario.get().getSenha());
    			usuarioLogin.get().setTipo(usuario.get().getTipo());
    			

                return usuarioLogin;

            }
        }   

        return Optional.empty();
        
    }

    private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder.encode(senha);

    }
    
    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder.matches(senhaDigitada, senhaBanco);

    }

    private String gerarBasicToken(String usuario, String senha) {

        String token = usuario + ":" + senha;
        byte[] tokenBytes = token.getBytes(StandardCharsets.US_ASCII);
        String tokenBase64 = Base64.getEncoder().encodeToString(tokenBytes);
        return "Basic " + tokenBase64;

    }


    }


	

