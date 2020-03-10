package com.avlija.parts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.avlija.parts.entities.Parts;
import com.avlija.parts.service.PartsService;

@Controller
public class AppController {
	
    @Autowired
    private PartsService service;
    
    @GetMapping("/")
    public String showIndex(Model model){
    	model.addAttribute("parts", new Parts());
        return "page";
    }

    @RequestMapping("/index")
    public String home(Model model) {
    	model.addAttribute("parts", new Parts());
        return "index";
    }
    
    @RequestMapping("/listall")
    public String viewHomePage(Model model) {
        List<Parts> listParts = service.listAll();
    	model.addAttribute("parts", new Parts());
        model.addAttribute("listParts", listParts);
        return "listallparts";
    }
    
    @RequestMapping("/new")
    public String showNewPartsPage(Model model) {
        Parts parts = new Parts();
        model.addAttribute("parts", parts);
         
        return "new_part";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePart(@ModelAttribute("parts") Parts parts) {
        service.save(parts);
        return "redirect:/listall";
    }
    
    @RequestMapping("/edit/{sifra}")
    public ModelAndView showEditProductPage(@PathVariable(name = "sifra") String sifra) {
        ModelAndView mav = new ModelAndView("edit_part");
        mav.addObject("parts", new Parts());
        Parts part = service.get(sifra);
        mav.addObject("part", part);
         
        return mav;
    }
    
    @RequestMapping("/delete/{sifra}")
    public String deleteProduct(@PathVariable(name = "sifra") String sifra) {
        service.delete(sifra);
        return "redirect:/listall";       
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public ModelAndView showPart(@ModelAttribute("parts") Parts parts) {
        ModelAndView mav = new ModelAndView("listpart");
        Parts listParts = service.get(parts.getSifra());
        mav.addObject("listParts", listParts);
        return mav;    
    }
    
    @RequestMapping("/test")
    public String test(){

        return "index3";
    }
}
