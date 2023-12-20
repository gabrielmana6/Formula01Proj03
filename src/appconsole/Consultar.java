package appconsole;

import java.util.List;

import modelo.Piloto;
import modelo.Prova;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {

		try {
			Fachada.inicializar();

			// Quais os pilotos da escuderia X
			System.out.println("\n*** Pilotos de escuderia x:");
			List<Piloto> pilotos = Fachada.queryListaEscuderias("mercedes");
			for (Piloto piloto : pilotos) {
				System.out.println(piloto);
			}
			
			
			// Quais as provas com mais de N chegadas
			System.out.println("\n*** Provas com mais de N chegadas:");
			List<Prova> provas = Fachada.queryListaProvas(0);
			for (Prova prova : provas) {
				System.out.println(prova);
			}
			
			// Quais as colocacoes do piloto de nome X
			System.out.println("\n*** Colocacoes do piloto X:");
			List<Integer> colocacoes = Fachada.queryListaChegadas("Gabriel");
			for (Integer colocacao: colocacoes) {
				System.out.println(colocacao);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
