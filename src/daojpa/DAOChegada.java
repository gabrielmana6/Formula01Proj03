package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Chegada;
import modelo.Piloto;

public class DAOChegada extends DAO<Chegada> {

	public Chegada read(Object chave) {
		try {

			Piloto piloto = (Piloto) chave;

			TypedQuery<Chegada> q = manager.createQuery("SELECT c FROM Chegada c WHERE c.piloto = :piloto",
					Chegada.class);
			q.setParameter("piloto", piloto);
			return q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}
