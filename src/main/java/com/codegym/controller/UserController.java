package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.IUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private IUseService useService;
	@GetMapping ("/users")
	public ModelAndView showAllUser (){
		ModelAndView modelAndView = new ModelAndView("/list");
		Iterable<User> users = useService.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	@GetMapping ("/users/create")
	public ModelAndView showCreateForm (){
		ModelAndView modelAndView = new ModelAndView("create");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	@PostMapping ("/users/create")
	public ModelAndView addNewUser (@Validated @ModelAttribute(name = "user") User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return new ModelAndView("create");
		}
		useService.save(user);
		return new ModelAndView("redirect:/users");
	}
}
