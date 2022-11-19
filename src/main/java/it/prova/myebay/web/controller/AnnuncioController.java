package it.prova.myebay.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AnnuncioService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@GetMapping
	public ModelAndView listAllAnnunci() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.listAll(), false));
		mv.setViewName("annuncio/list");
		return mv;
	}
	
	@PostMapping("/list")
	public String listAnnunci(HttpServletRequest request, Annuncio annuncioExample, ModelMap model) {
		UtenteDTO utenteInSessione = (UtenteDTO)request.getSession().getAttribute("userInfo");
		List<Annuncio> annunci = new ArrayList<>(); 
 		if(utenteInSessione != null && utenteInSessione.getId() != null) {
			annuncioExample.setUtente(utenteInSessione.buildUtenteModel(false));
			annunci = annuncioService.findByExampleEager(annuncioExample);
		}else {
			annunci = annuncioService.findByExample(annuncioExample);
		}
		
		model.addAttribute("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annunci, true));
		
		return "annuncio/list";
	}

}
