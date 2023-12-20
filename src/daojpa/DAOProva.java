package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Prova;

public class DAOProva extends DAO<Prova> {

	public Prova read(Object chave) {
		try {
			int id = (int) chave;

			TypedQuery<Prova> q = manager.createQuery("SELECT p FROM Prova p WHERE p.id = :n", Prova.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Prova> readAll(){
		TypedQuery<Prova> q = manager.createQuery("select p from Prova p LEFT JOIN FETCH p.chegadas order by p.id", Prova.class);
		return  q.getResultList();
	}
	
	//Quais as provas com mais de N chegadas
	public List<Prova> queryListaProvas(int qtdChegadas) {
		TypedQuery<Prova> q = manager.createQuery("SELECT p FROM Prova p LEFT JOIN FETCH p.chegadas WHERE SIZE(p.chegadas) > :x", Prova.class);
		q.setParameter("x", qtdChegadas);
		return q.getResultList();
	}
}