package entities;

public class ContaEmpresa extends Conta{
	
	// attribute
		private	double	emprestimoEmpresa;
	
	// constructor
		public ContaEmpresa(int numero, String cpf, double emprestimoEmpresa) {
			super(numero, cpf);
			this.emprestimoEmpresa = emprestimoEmpresa;
		}
		
	// encapsulation
		public double getEmprestimoEmpresa() {
			return emprestimoEmpresa;
		}

		

		public void setEmprestimoEmpresa(double emprestimoEmpresa) {
			this.emprestimoEmpresa = emprestimoEmpresa;
		}
		
	// methods
		public void pedirEmprestimo(double emprestimo)
		{
			super.credito(emprestimo);
			this.emprestimoEmpresa -= emprestimo;
			System.out.println("Saldo atual emprestimo R$ "+this.emprestimoEmpresa);
		}
	
}
