package it.prova.myebay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import it.prova.myebay.validation.ValidationWithPassword;

public class AccountDTO {
	
	@NotBlank(message = "{password.notblank}", groups =  ValidationWithPassword.class)
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String vecchiaPassword;
	
	@NotBlank(message = "{password.notblank}", groups =  ValidationWithPassword.class)
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String nuovaPassword;
	
	@NotBlank(message = "{password.notblank}", groups =  ValidationWithPassword.class)
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String confermaPassword;

	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}

	public String getNuovaPassword() {
		return nuovaPassword;
	}

	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}
	
	
	
}
