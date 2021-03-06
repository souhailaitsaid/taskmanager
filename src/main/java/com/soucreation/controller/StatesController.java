package com.soucreation.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.State;
import com.soucreation.repository.StateRepository;

@Controller
@RequestMapping("/states")
public class StatesController {
	private StateRepository stateRepository;
	private static final Sort SORTING_DESC=new Sort(Sort.Direction.DESC, "stateId");
	
	@Autowired
	public StatesController(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model,Principal principal) {
		List<State> stateList = stateRepository.findAll(SORTING_DESC);
		if (stateList != null) {
			model.addAttribute("stateList", stateList);
		}
		model.addAttribute("state", new State());
		model.addAttribute("userName", principal.getName());
		return "stateManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, State state,Principal principal) {
		try{
			stateRepository.save(state);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<State> stateList = stateRepository.findAll(SORTING_DESC);
		if (stateList != null) {
			model.addAttribute("stateList", stateList);
		}
		model.addAttribute("state", new State());
		model.addAttribute("userName", principal.getName());
		return "stateManagement";
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id,Principal principal) {
		State r = null;
		try{
			 r=stateRepository.findOne(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<State> stateList = stateRepository.findAll(SORTING_DESC);
		if (stateList != null) {
			model.addAttribute("stateList", stateList);
			model.addAttribute("state", r);
		}
		model.addAttribute("userName", principal.getName());
		return "stateManagement";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id,Model model,Principal principal) {
		try{
			stateRepository.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<State> stateList = stateRepository.findAll(SORTING_DESC);
		if (stateList != null) {
			model.addAttribute("stateList", stateList);
		}
		model.addAttribute("state", new State());
		model.addAttribute("userName", principal.getName());
		return "stateManagement";
	}
}