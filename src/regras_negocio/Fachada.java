package regras_negocio;

import java.util.List;

import daojpa.DAO;
import daojpa.DAOChegada;
import daojpa.DAOPiloto;
import daojpa.DAOProva;
import modelo.Chegada;
import modelo.Piloto;
import modelo.Prova;

public class Fachada {
	private Fachada() {
	}

	private static DAOPiloto daopiloto = new DAOPiloto();
	private static DAOChegada daochegada = new DAOChegada();
	private static DAOProva daoprova = new DAOProva();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// fachada para piloto

	public static void criarPiloto(String nome, String escuderia) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		if (piloto != null) {
			DAO.rollback();
			throw new Exception("Piloto " + nome + " já existe.");
		}
		piloto = new Piloto(nome, escuderia);

		daopiloto.create(piloto);
		DAO.commit();
	}

	public static Piloto listarPiloto(String nome) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		if (piloto == null) {
			DAO.rollback();
			throw new Exception("Piloto inexistente.");
		}
		return piloto;
	}

	public static List<Piloto> listarPilotos() {
		DAO.begin();
		List<Piloto> pilotos = daopiloto.readAll();
		DAO.commit();
		return pilotos;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// fachada para prova

	public static Prova criarProva() {
		DAO.begin();
		Prova prova = new Prova();
		daoprova.create(prova);
		DAO.commit();
		return prova;
	}

	public static Prova listarProva(int id) throws Exception {
		DAO.begin();
		Prova prova = daoprova.read(id);
		if (prova == null) {
			DAO.rollback();
			throw new Exception("Prova inexistente.");
		}
		return prova;
	}

	public static List<Prova> listarProvas() {
		DAO.begin();
		List<Prova> provas = daoprova.readAll();
		DAO.commit();
		return provas;
	}

	public static void deletarPiloto(String nome) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		if (piloto == null) {
			DAO.rollback();
			throw new Exception("Piloto inexistente.");
		}

		daopiloto.delete(piloto);
		DAO.commit();
	}

	public static void deletarProva(int id) throws Exception {
		DAO.begin();
		Prova prova = daoprova.read(id);
		if (prova == null) {
			DAO.rollback();
			throw new Exception("Prova de id: " + id + " inexistente.");
		}

		daoprova.delete(prova);
		DAO.commit();
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// fachada para chegada

	public static void criarChegada(int id, int colocacao, String nome) throws Exception {
		DAO.begin();

		if (colocacao <= 0) {
			DAO.rollback();
			throw new Exception("Colocacao deve ser maior que zero");
		}

		Piloto pilotoChegada = daopiloto.read(nome);
		if (pilotoChegada == null) {
			DAO.rollback();
			throw new Exception("Piloto inexistente");
		}

		Prova provaChegada = daoprova.read(id);
		if (provaChegada == null) {
			DAO.rollback();
			throw new Exception("Prova inexistente.");
		}

		for (Chegada c : provaChegada.getChegadas()) {
			if (c.getColocacao() == colocacao) {
				DAO.rollback();
				throw new Exception("Piloto " + c.getPiloto().getNome() + " já está na posicao " + colocacao);
			}
			if (c.getPiloto() == pilotoChegada) {
				DAO.rollback();
				throw new Exception("Piloto " + nome + " já existe nessa chegada na colocacao " + c.getColocacao());
			}
		}

		Chegada chegada = new Chegada(provaChegada, colocacao, pilotoChegada);
		provaChegada.addChegada(chegada);

		daochegada.create(chegada);

		DAO.commit();
	}

	public static Chegada listarChegada(int id) throws Exception {
		DAO.begin();
		Chegada chegada = daochegada.read(id);
		if (chegada == null) {
			DAO.rollback();
			throw new Exception("Chegada inexistente.");
		}
		DAO.commit();
		return chegada;
	}

	public static List<Chegada> listarChegadas() {
		DAO.begin();
		List<Chegada> chegadas = daochegada.readAll();
		DAO.commit();
		return chegadas;
	}

	public static void deletarChegada(int id) throws Exception {
		DAO.begin();

		Chegada chegada = daochegada.read(id);

		if (chegada == null) {
			DAO.rollback();
			throw new Exception("Chegada inexistente.");
		}
		
		Piloto piloto = chegada.getPiloto();
        Prova prova = chegada.getProva();
		
		piloto.rmvChegada(chegada);
		prova.rmvChegada(chegada);
		
		daochegada.delete(chegada);
		DAO.commit();
	}
}
