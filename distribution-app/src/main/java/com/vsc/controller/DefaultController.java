package com.vsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
		
       /**
        * The method is used to display the index page
        * string	
        * @return
        */
 	   @RequestMapping(value = "/default", method = RequestMethod.GET)
	   public String index() {
	       return "index";
	   }

}
