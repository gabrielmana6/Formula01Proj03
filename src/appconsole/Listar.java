package appconsole;

import modelo.Chegada;
import modelo.Piloto;
import modelo.Prova;
import regras_negocio.Fachada;

public class Listar {
	
	public Listar(){
		try {
			Fachada.inicializar();

			System.out.println("\n*** Listagem de piloto:");
			for(Piloto p : Fachada.listarPilotos())		
				System.out.println(p);
			
			System.out.println();
			//Piloto piloto = Fachada.listarPiloto("Gabriel");
			//System.out.println(piloto);
			
			System.out.println("\n*** Listagem de prova:");
			for(Prova p : Fachada.listarProvas())		
				System.out.println(p);
			
			System.out.println();
			//Prova prova = Fachada.listarProva(1);
			//Prova prova2 = Fachada.listarProva(2);
			//System.out.println(prova);
			//System.out.println(prova2);
			
			System.out.println("\n*** Listagem de chegada:");
			for(Chegada c : Fachada.listarChegadas())		
				System.out.println(c);
			
			System.out.println();
			Chegada chegada = Fachada.listarChegada(1);
			Chegada chegada2 = Fachada.listarChegada(2);
			Chegada chegada3 = Fachada.listarChegada(3);
			//System.out.println(chegada);
			//System.out.println(chegada2);
			//System.out.println(chegada3);

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

