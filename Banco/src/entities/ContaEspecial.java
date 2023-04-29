package entities;

public class ContaEspecial extends Conta{
	
	// attribute
		private	double	 limite;
	
	// constructor
		public ContaEspecial(int numero, String cpf, double  limite, double i) {
			super(numero, cpf);
			this. limite =  limite;
		}
		
	
		

		// encapsulation
		public double getlimite() {
			return limite;
		}

		

		public void setlimite(double limite) {
			this. limite =  limite;
		}
		
	// methods
		public void usarLimite(double limite)
		{
			super.credito( limite);
			this.limite-=  limite;
			System.out.println("Saldo atual limite R$ "+this. limite);
		}
	
}