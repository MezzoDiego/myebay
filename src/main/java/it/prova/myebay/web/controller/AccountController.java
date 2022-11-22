package it.prova.myebay.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AccountDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/reset")
	public String reset(Model model) {
		model.addAttribute("reset_utente_attr", new AccountDTO());
		return "utente/reset";
	}
	
	@PostMapping("/applyReset")
	public String applyReset(
			@Validated( ValidationWithPassword.class) @ModelAttribute("reset_utente_attr") AccountDTO accountDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, Principal principal) {

		if (!result.hasFieldErrors("nuovaPassword") && !accountDTO.getNuovaPassword().equals(accountDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			return "utente/reset";
		}

		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		String rawPassword = accountDTO.getVecchiaPassword();
		if(passwordEncoder.matches(rawPassword, utenteInSession.getPassword())) {
			utenteService.resetPassword(principal.getName(), accountDTO.getNuovaPassword());
		} else {
			model.addAttribute("errorMessage", "Vecchia Password errata!"); 
			return "utente/reset";
		}
		
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/executeLogout";
	}


}
