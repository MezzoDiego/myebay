package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService{

	@Autowired
	private AnnuncioRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Annuncio annuncioInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}
	
	
}
