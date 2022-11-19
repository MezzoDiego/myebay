package it.prova.myebay.repository.utente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.prova.myebay.model.Utente;

public class CustomUtenteRepositoryImpl implements CustomUtenteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}

}
