package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Utente;

public interface UtenteService {

	public List<Utente> listAll();

	public Utente caricaSingoloElemento(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idToDelete);
}
