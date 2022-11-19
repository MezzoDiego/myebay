package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.model.Annuncio;
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
	public String listAnnunci(Annuncio annuncioExample, ModelMap model) {
		model.addAttribute("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.findByExample(annuncioExample), false));
		return "annuncio/list";
	}

}
