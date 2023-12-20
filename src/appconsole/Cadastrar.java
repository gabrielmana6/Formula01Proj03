package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){
		try {
			Fachada.inicializar();

			Fachada.criarProva();
			Fachada.criarProva();
			
			Fachada.criarPiloto("Gabriel", "ferrari");
			Fachada.criarPiloto("Geraldo", "mercedes");
			
			
			Fachada.criarChegada(1, 1, "Gabriel");
			Fachada.criarChegada(1, 2, "Geraldo");
			
			Fachada.criarChegada(2, 1, "Geraldo");
			
			
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	
	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


