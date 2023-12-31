package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Chegada;
import modelo.Piloto;
import modelo.Prova;

public class DAOChegada extends DAO<Chegada> {

	public Chegada read(Object chave) {
		try {
			long id = (long) chave;
			TypedQuery<Chegada> q = manager.createQuery("select c from Chegada c where c.id = :n ", Chegada.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Chegada> readAll() {
		TypedQuery<Chegada> q = manager.createQuery(
				"select ch from Chegada ch LEFT JOIN FETCH ch.prova  JOIN FETCH ch.piloto order by ch.id",
				Chegada.class);
		return q.getResultList();
	}

	// Quais as colocacoes do piloto de nome X
	public List<Integer> queryListaProvas(String nome) {
		try {
			TypedQuery<Integer> q = manager.createQuery("select c.colocacao from Chegada c where c.piloto.nome = :x",
					Integer.class);
			q.setParameter("x", nome);
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Long listarId(Prova prova, Piloto piloto) {
	    TypedQuery<Long> query = manager.createQuery(
	        "SELECT c.id FROM Chegada c WHERE c.prova = :prova AND c.piloto = :piloto", Long.class);
	    query.setParameter("prova", prova);
	    query.setParameter("piloto", piloto);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; 
	    }
	}

}
