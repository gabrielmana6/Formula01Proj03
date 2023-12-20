package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Piloto;

public class DAOPiloto extends DAO<Piloto> {

	public Piloto read (Object chave) {
		try {
			String nome = (String) chave;
			TypedQuery<Piloto> q = manager.createQuery("select p from Piloto p where p.nome=:n", Piloto.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}