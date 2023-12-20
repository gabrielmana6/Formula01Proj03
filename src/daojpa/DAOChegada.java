package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Chegada;
import modelo.Piloto;

public class DAOChegada extends DAO<Chegada> {

	public Chegada read(Object chave) {
		try{
			int id = (int) chave;
			TypedQuery<Chegada> q = manager.createQuery("select c from Chegada c where c.id = :n ",Chegada.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Chegada> readAll(){
		TypedQuery<Chegada> q = manager.createQuery("select ch from Chegada ch LEFT JOIN FETCH ch.prova  JOIN FETCH ch.piloto order by ch.id", Chegada.class);
		return  q.getResultList();
	}
	

}
