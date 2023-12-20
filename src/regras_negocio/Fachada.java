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

	public static Piloto criarPiloto(String nome, String escuderia) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		if (piloto != null)
			throw new Exception("Piloto " + nome + " já existe.");
		piloto = new Piloto(nome, escuderia);

		daopiloto.create(piloto);
		DAO.commit();
		return piloto;
	}

	public static Piloto listarPiloto(String nome) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		if (piloto == null)
			throw new Exception("Piloto inexistente.");
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
		if (prova == null)
			throw new Exception("Prova inexistente.");
		return prova;
	}

	public static List<Prova> listarProvas() {
		DAO.begin();
		List<Prova> provas = daoprova.readAll();
		DAO.commit();
		return provas;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// fachada para chegada

	public static void criarChegada(int id, int colocacao, String nome) throws Exception {
		DAO.begin();

		if (colocacao <= 0)
			throw new Exception("Colocacao deve ser maior que zero");

		Piloto pilotoChegada = daopiloto.read(nome);
		if (pilotoChegada == null)
			throw new Exception("Piloto inexistente");

		Prova provaChegada = daoprova.read(id);
		if (provaChegada == null)
			throw new Exception("Prova inexistente.");

		for (Chegada c : provaChegada.getChegadas()) {
			if (c.getColocacao() == colocacao)
				throw new Exception("Piloto " + c.getPiloto().getNome() + " já está na posicao " + colocacao);
			if (c.getPiloto() == pilotoChegada)
				throw new Exception("Piloto " + nome + " já existe nessa chegada na colocacao " + c.getColocacao());
		}

		Chegada chegada = new Chegada(provaChegada, colocacao, pilotoChegada);
		provaChegada.addChegada(chegada);

		daochegada.create(chegada);
		daoprova.update(provaChegada);

		DAO.commit();
	}

	public static Chegada listarChegada(int id, String nome) throws Exception {
		DAO.begin();
		Piloto pilotoChegada = daopiloto.read(nome);
		if (pilotoChegada == null)
			throw new Exception("Piloto inexistente.");

		Prova provaChegada = daoprova.read(id);
		if (provaChegada == null)
			throw new Exception("Prova inexistente.");

		Chegada chegada = null;
		for (Chegada c : provaChegada.getChegadas()) {
			if (c.getPiloto().getNome().equalsIgnoreCase(nome)) {
				chegada = c;
				break;
			}
		}

		if (chegada == null)
			throw new Exception("Chegada inexistente.");
		DAO.commit();
		return chegada;
	}

	public static List<Chegada> listarChegadas() {
		DAO.begin();
		List<Chegada> chegadas = daochegada.readAll();
		DAO.commit();
		return chegadas;
	}
}
