package appconsole;

import regras_negocio.Fachada;

public class Alterar {
	
	public Alterar() {
		try {
			Fachada.inicializar();
			
			//Fachada.editarPiloto("Gabriel", "Gugu", "ferrari");
			
			//Fachada.editarPiloto("Geraldo", "Wolverine", "garras afiadas");
			
			//Fachada.editarColocacao(3, 5);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
