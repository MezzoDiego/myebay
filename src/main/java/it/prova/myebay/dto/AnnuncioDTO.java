package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public class AnnuncioDTO {

	private Long id;

	@NotBlank(message = "{testoannuncio.notblank}")
	@Size(min = 10, max = 100, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String testoAnnuncio;
	@NotNull(message = "{prezzo.notnull}")
	private Integer prezzo;
	@NotNull(message = "{data.notnull}")
	private Date data;

	private Boolean aperto;

	private UtenteDTO utente;

	public AnnuncioDTO() {
		super();
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, Boolean aperto, UtenteDTO utente) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utente = utente;
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, Boolean aperto) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getAperto() {
		return aperto;
	}

	public void setAperto(Boolean aperto) {
		this.aperto = aperto;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Annuncio buildAnnuncioModel() {
		return new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utente.buildUtenteModel(false));
	}

	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeUtente) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(),
				annuncioModel.getPrezzo(), annuncioModel.getData(), annuncioModel.getAperto());

		if (includeUtente)
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtente(), false));

		return result;
	}

	public static List<AnnuncioDTO> createAnnuncioDTOFromModelList(List<Annuncio> modelListInput,
			boolean includeUtente) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeUtente);
		}).collect(Collectors.toList());
	}

}
