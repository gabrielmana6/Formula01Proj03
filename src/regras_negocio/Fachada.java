package regras_negocio;

import java.util.ArrayList;
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

	public static void editarPiloto(String nome, String novoNome, String novaEscuderia) throws Exception {
		DAO.begin();

		Piloto piloto = daopiloto.read(nome);
		if (piloto == null) {
			DAO.rollback();
			throw new Exception("Piloto inexistente.");
		}

		Piloto novoPiloto = daopiloto.read(novoNome);
		if (novoPiloto != null && !piloto.getNome().equalsIgnoreCase(novoNome)) {
			DAO.rollback();
			throw new Exception("Já existe um piloto com nome " + novoNome);
		}

		piloto.setNome(novoNome);
		piloto.setEscuderia(novaEscuderia);
		daopiloto.update(piloto);

		DAO.commit();
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

	public static void deletarTodosPilotos() throws Exception {
		try {
			DAO.begin();
			List<Piloto> pilotos = daopiloto.readAll();
			if (pilotos == null) {
				DAO.rollback();
			}
			for (Piloto piloto : pilotos) {
				deletarPiloto(piloto.getNome());
			}
			DAO.commit();
		} catch (Exception e) {

		}
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

	public static void deletarProva(long id) throws Exception {
		DAO.begin();
		Prova prova = daoprova.read(id);
		if (prova == null) {
			DAO.rollback();
			throw new Exception("Prova de id: " + id + " inexistente.");
		}

		daoprova.delete(prova);
		DAO.commit();
	}
	
	public static void deletarTodasProvas() throws Exception {
		try {
			DAO.begin();
			List<Prova> provas = daoprova.readAll();
			if (provas == null) {
				DAO.rollback();
			}
			for (Prova prova: provas) {
				deletarProva(prova.getId());
			}
			DAO.commit();
		} catch (Exception e) {

		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// fachada para chegada

	public static void criarChegada(long id, int colocacao, String nome) throws Exception {
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

	public static Chegada listarChegada(long id) throws Exception {
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

	public static void deletarChegada(long id) throws Exception {
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
	
	public static void deletarTodasChegadas() throws Exception {
		DAO.begin();
		List<Chegada> chegadas = daochegada.readAll();
		if (chegadas == null) {
			DAO.rollback();
			throw new Exception("Chegadas vazio.");
		}
		for (Chegada chegada: chegadas) {
			deletarChegada(chegada.getId());
		}
		DAO.commit();
	}

	
	// ----------------------------------------------------------------------------------------------------------------------------------------------
	// OUTROS METODOS
	public static void editarColocacao(int idChegada, int novaColocacao) throws Exception {
		DAO.begin();

		Chegada chegada = daochegada.read(idChegada);

		if (chegada == null) {
			DAO.rollback();
			throw new Exception("Chegada inexistente.");
		}

		chegada.setColocacao(novaColocacao);
		DAO.commit();
	}

	// Quais os pilotos da escuderia X
	public static List<Piloto> queryListaEscuderias(String escuderia) throws Exception {
		DAO.begin();
		List<Piloto> pilotos = daopiloto.listaEscuderias(escuderia);
		if (pilotos == null) {
			DAO.rollback();
			throw new Exception("Nenhum piloto com essa escuderia.");
		}
		DAO.commit();
		return pilotos;
	}

	// Quais as provas com mais de N chegadas
	public static List<Prova> queryListaProvas(int qtdChegadas) throws Exception {
		DAO.begin();
		List<Prova> provas = daoprova.queryListaProvas(qtdChegadas);

		if (provas == null) {
			DAO.rollback();
			throw new Exception("Nenhum prova encontrada.");
		}
		DAO.commit();
		return provas;
	}

	// Quais as colocacoes do piloto de nome X
	public static List<Integer> queryListaChegadas(String nome) throws Exception {
		DAO.begin();
		List<Integer> colocacoes = daochegada.queryListaProvas(nome);
		if (colocacoes == null) {
			DAO.rollback();
			throw new Exception("Nenhum colocacao encontrada.");
		}
		DAO.commit();
		return colocacoes;
	}

	public static List<Long> queryProvaDoPiloto(String nome) throws Exception {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		List<Prova> provas = daoprova.listarProvasDoPiloto(piloto);

		if (provas == null) {
			DAO.rollback();
			throw new Exception("Piloto não está em nenhuma prova");
		}

		List<Long> idProvas = new ArrayList<>();
		for (Prova prova : provas) {
			idProvas.add(prova.getId());
		}

		DAO.commit();
		return idProvas;
	}

	public static Chegada obterChegada(int idProva, String nome) {
		DAO.begin();
		Piloto piloto = daopiloto.read(nome);
		Prova prova = daoprova.read(idProva);

		Long idChegada = daochegada.listarId(prova, piloto);
		Chegada chegada = daochegada.read(idChegada);

		DAO.commit();
		return chegada;
	}

}
