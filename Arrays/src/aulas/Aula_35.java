package aulas;

import java.util.Scanner;

public class Aula_35 {

public static void main(String[] args) {

final int TOTAL_ALUNOS = 4;
final int TOTAL_BIMESTRES = 4;
final double NOTA_MINIMA = 7.0;

Scanner input = new Scanner(System.in);


String[] nomeAlunos = new String[TOTAL_ALUNOS];
//array list

double[][] ntaAlunos = new double[TOTAL_ALUNOS][TOTAL_BIMESTRES];
double[] mediaAlunos = new double[TOTAL_ALUNOS];

//Obter nome dos alunos
for(int i = 0; i < TOTAL_ALUNOS; ++i){
System.out.println("Informe o nome do " + (i+1) + "° aluno:");
nomeAlunos[i] = input.nextLine();
}

System.out.println("");

//Obter notas dos alunos todos os bimetres
for(int i = 0; i < TOTAL_ALUNOS; ++i){
for(int j = 0; j < TOTAL_BIMESTRES; ++j){
System.out.println("Informe a nota do aluno " + nomeAlunos[i] +
" para o " + (j+1) + "° bimestre");
ntaAlunos[i][j] = input.nextDouble();
}
}

//calcular media alunos
for(int i = 0; i < TOTAL_ALUNOS; ++i){
for(int j = 0; j < TOTAL_BIMESTRES; ++j){
mediaAlunos[i] += ntaAlunos[i][j];
}
mediaAlunos[i] /= TOTAL_BIMESTRES;
}

//Mostrar situacao dos alunos
System.out.println("======== RESULTADO FINAL =======");

for(int i = 0; i < TOTAL_ALUNOS; ++i){

if(mediaAlunos[i] >= NOTA_MINIMA ){
System.out.println("Nome: " + nomeAlunos[i] + " Media: " + mediaAlunos[i] +
" Situação: Aprovado");
continue;//como se fosse o ELSE na estrutura 
}

System.out.println("Nome: " + nomeAlunos[i] + " Media: " + mediaAlunos[i] +
" Situação: Reprovado");}
}

}
