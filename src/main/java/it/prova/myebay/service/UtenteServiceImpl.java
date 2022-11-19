package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.utente.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{

	@Autowired
	private UtenteRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public List<Utente> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Utente caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Utente utenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}

}
