package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.service.AcquistoService;

@Controller
@RequestMapping(value = "/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoService acquistoService;
	
	@GetMapping
	public ModelAndView listAllAcquisti() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("acquisti_list_attribute",
				AcquistoDTO.createAcquistoDTOFromModelList(acquistoService.listAll(), false));
		mv.setViewName("acquisto/list");
		return mv;
	}
	
}
