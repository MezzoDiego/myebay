package it.prova.myebay.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ModelAndView listAllAnnunci() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.listAll(), false, false));
		mv.setViewName("annuncio/list");
		return mv;
	}

	@RequestMapping("/list")
	public String listAnnunci(Annuncio annuncioExample, ModelMap model) {

		model.addAttribute("annunci_list_attribute", AnnuncioDTO
				.createAnnuncioDTOFromModelList(annuncioService.findByExample(annuncioExample), false, false));

		return "annuncio/list";
	}

	@GetMapping("/show/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloElementoConCategorie(idAnnuncio);
		AnnuncioDTO result = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, false, true);
		model.addAttribute("show_annuncio_attr", result);
		model.addAttribute("categorie_annuncio_attr", CategoriaDTO
				.createCategoriaDTOListFromModelList(categoriaService.cercaCategorieByIds(result.getCategorieIds())));
		return "annuncio/show";
	}

	@GetMapping("/showUtente/{idAnnuncio}")
	public String showAnnuncio(HttpServletRequest request, @PathVariable(required = true) Long idAnnuncio,
			Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloElementoConCategorie(idAnnuncio);
		UtenteDTO utenteInSessione = (UtenteDTO) request.getSession().getAttribute("userInfo");
		annuncioModel.setUtente(utenteInSessione.buildUtenteModel(false));
		AnnuncioDTO result = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, true, true);
		model.addAttribute("show_annuncio_attr", result);
		model.addAttribute("categorie_annuncio_attr", CategoriaDTO
				.createCategoriaDTOListFromModelList(categoriaService.cercaCategorieByIds(result.getCategorieIds())));
		return "annuncio/showUtente";
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("categorie_list_attribute",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		return "annuncio/search";
	}

	@RequestMapping("/listUtente")
	public String listAnnunciUtente(HttpServletRequest request, Annuncio annuncioExample, ModelMap model) {
		UtenteDTO utenteInSessione = (UtenteDTO) request.getSession().getAttribute("userInfo");
		annuncioExample.setUtente(utenteInSessione.buildUtenteModel(false));
		model.addAttribute("annunci_list_attribute", AnnuncioDTO
				.createAnnuncioDTOFromModelList(annuncioService.findByExampleEager(annuncioExample), true, false));

		return "annuncio/listUtente";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
			return "annuncio/insert";
		}
		UtenteDTO utenteInSessione = (UtenteDTO) request.getSession().getAttribute("userInfo");
		annuncioDTO.setUtente(utenteInSessione);
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/listUtente";
	}

}
