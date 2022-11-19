package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}

}
