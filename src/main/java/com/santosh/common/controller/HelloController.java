package com.santosh.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.santosh.common.exception.CustomGenericException;
import com.santosh.common.resolver.CRequestParam;

@Controller
@RequestMapping("/welcome")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model, @CRequestParam("p") String p) {

	model.addAttribute("message", "Spring 4 MVC Hello World");
	System.out.println("Request param " + p);
	return "hello";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/exception2")
    public String getException1(ModelMap model, @CRequestParam("p") String p, HttpServletRequest request) {

	System.out.println("Exception 2 " + p);
	request.setAttribute("p", p);
	throw new CustomGenericException("1", "2");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/exception3")
    public String getException2(ModelMap model, @CRequestParam("p") String p) throws Exception {

	System.out.println("Exception 3 " + p);
	throw new Exception("3");
    }

    /**
     * Handlers are as below
     * 
     * @param model
     * @return
     */
    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex, HttpServletRequest request) {

	ModelAndView model2 = new ModelAndView("error/generic_error");
	model2.addObject("exception", ex);
	System.out.println(request.getAttribute("p"));
	System.out.println("CustomGenericException  ");
	return model2;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex, HttpServletRequest request, @CRequestParam("p") String p) {

	ModelAndView model = new ModelAndView("error/exception_error");
	System.out.println("Exception  ");
	return model;

    }

}