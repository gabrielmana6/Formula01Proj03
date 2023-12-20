package appconsole;

import modelo.Piloto;
import regras_negocio.Fachada;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();

			System.out.println("*** Listagem de piloto:");
			for(Piloto p : Fachada.listarPilotos())		
				System.out.println(p);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}


	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

