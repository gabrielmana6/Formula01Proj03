package regras_negocio;

import java.util.List;

import daojpa.DAO;
import daojpa.DAOChegada;
import daojpa.DAOPiloto;
import daojpa.DAOProva;
import modelo.Piloto;

public class Fachada {
	private Fachada() {}

	private static DAOPiloto daopiloto = new DAOPiloto();
	private static DAOChegada daochegada = new DAOChegada();
	private static DAOProva daoprova= new DAOProva();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	//----------------------------------------------------------------------------------------------------------------------------------------------
		// fachada para piloto

	    public static Piloto criarPiloto(String nome, String escuderia) throws Exception{
			DAO.begin();
			Piloto piloto = daopiloto.read(nome);
			if (piloto!=null)
				throw new Exception("Piloto " + nome + " já existe.");
			piloto = new Piloto(nome, escuderia);
			
			daopiloto.create(piloto);
			DAO.commit();
			return piloto;
	    }

	    public static Piloto listarPiloto(String nome) throws Exception{
	    	DAO.begin();
	    	Piloto piloto = daopiloto.read(nome);
	    	if(piloto == null)
	    		throw new Exception("Piloto inexistente.");
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
	    	if (piloto == null) throw new Exception("Piloto inexistente.");
	    	
	    	Piloto novoPiloto = daopiloto.read(novoNome);
	    	if (novoPiloto != null && !piloto.getNome().equalsIgnoreCase(novoNome))	throw new Exception("Já existe um piloto com nome " + novoNome);
	    	
			piloto.setNome(novoNome);
			piloto.setEscuderia(novaEscuderia);
			daopiloto.update(piloto);
	    	
	    	DAO.commit();
	    }

	    /*
	    public static void deletarPiloto(String nome) throws Exception{
	    	DAO.begin();
	    	Piloto piloto = daopiloto.read(nome);
	    	if(piloto == null)
	    		throw new Exception("Piloto inexistente.");
	    	
	    	List<Prova> provas = daoprova.ListarProvasDoPiloto(piloto);
	    	for(Prova prova: provas) {
				deletarChegada(prova.getId(), nome);
	    	}
	    	
	    	daopiloto.delete(piloto);
	    	DAO.commit();
	    }
	    
	    public static void deletarTodosPilotos() {
	    	DAO.begin();
	    	deletarTodasChegadas();
	    	daopiloto.deleteAll();
	    	DAO.commit();
	    }
	    */

}
