package entities ;

import java.util.Scanner;

import entities.ContaCorrente;
import entities.ContaEmpresa;
import entities.ContaEspecial;
import entities.ContaEstudantil;
import entities.ContaPoupanca;

public class TesteConta {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
		
		ContaEstudantil cest1 = new ContaEstudantil(14,"123.456.789-55",1000);
		ContaPoupanca cp1 = new ContaPoupanca(156,"222.333.444-58",5);
		ContaCorrente cc1 = new ContaCorrente(147,"147.258.369-98",3);
		ContaEspecial cesp1 = new ContaEspecial(148,"369.258.147",3,1000);
		ContaEmpresa  cemp1 = new ContaEmpresa(123,"159753852.99",50000);
		
		char tipo;
		double valor=0.00;
		char op;
		int dia;
		for (int x=1; x<=10; x++) {
			System.out.println("MOVIMENTO "+x);
			System.out.println("SALDO ATUAL R$ :"+cp1.getSaldo());
			System.out.print("Movimento D-d�bito ou C-cr�dito ? :");
			tipo = leia.next().toUpperCase().charAt(0);
			System.out.print("Digite o valor :");
			valor = leia.nextDouble();
			if (tipo=='D') {
				cp1.debito(valor);
			} 
			else if (tipo=='C') {
				cp1.credito(valor);
			} else {
				System.out.println("N�o foi informado um tipo correto...");
			}
			System.out.println("Continua S/N : ");
			op = leia.next().toUpperCase().charAt(0);
			if (op=='N') {
				
				break;
			}
			
		}
		
		//EXEMPLO USANDO A CONTA POUPANÇA
		System.out.println("SALDO ATUALIZADO R$ "+cp1.getSaldo());
		System.out.println("Informe o dia de hoje :");
		dia = leia.nextInt();
		cp1.correcao(dia);
		System.out.println("SALDO FINAL R$ " + cp1.getSaldo());
		
		

	}

}
