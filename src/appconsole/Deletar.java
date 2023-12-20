package appconsole;

import regras_negocio.Fachada;

public class Deletar {

	public Deletar() {
		try {
			Fachada.inicializar();

			//Fachada.deletarPiloto("Gabriel");
			//Fachada.deletarPiloto("Geraldo");
			
			//Fachada.deletarProva(1);
			
			//Fachada.deletarChegada(3);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Deletar();
	}
}
