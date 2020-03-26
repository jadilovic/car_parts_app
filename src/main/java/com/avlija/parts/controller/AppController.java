package com.avlija.parts.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avlija.parts.exception.PartsTransactionException;
import com.avlija.parts.form.FormCommand;
import com.avlija.parts.form.PartsInfoForm;
import com.avlija.parts.form.TransPartsForm;
import com.avlija.parts.entities.Orders;
import com.avlija.parts.entities.Parts;
import com.avlija.parts.repository.PartsDAO;
import com.avlija.parts.service.OrdersService;
import com.avlija.parts.service.PartsService;
import com.avlija.parts.validation.PartsValidation;

@Controller
public class AppController {
	
    @Autowired
    private PartsService service;
    
    @Autowired
    private PartsDAO partsDAO;
    
    @Autowired
    private OrdersService serviceO;
    
    @Autowired
    private PartsValidation validate;
    
    @GetMapping("/")
    public String showIndex(Model model){
    	model.addAttribute("parts", new Parts());
    	model.addAttribute("command", new FormCommand());
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
        model.addAttribute("message", "Lista svih autodijelova i stanje");
        return "listallparts";
    }
    
    @RequestMapping("/new")
    public String showNewPartsPage(Model model) {
    	String errorMessage = null;
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("parts", new Parts());
        model.addAttribute("partsForm", new PartsInfoForm());
        return "new_part";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePart(Model model, @ModelAttribute("partsForm") PartsInfoForm partsForm) {
    	Parts part = new Parts();
    	String sifra = partsForm.getSifra();
    	String grupa = partsForm.getGrupa();
    	String naziv = partsForm.getNaziv();
    	String marka = partsForm.getMarka();
    	String automobil = partsForm.getAutomobil();
    	String modelauta = partsForm.getModelauta();
    	int godina = partsForm.getGodina();
    	int kolicina = partsForm.getKolicina();
    	float cijena;
    	try {
        	cijena = Float.parseFloat(partsForm.getCijena());
    	} catch(Exception e) {
    		cijena = (float) 0.25;
    	}
    	try {
    		if(!partsForm.isEdit()) {
    			validate.checkSifra(sifra);
    		}
			validate.checkNazivMarkaModel(naziv);
			validate.checkNazivMarkaModel(marka);
			validate.checkNazivMarkaModel(modelauta);
			validate.checkCijena(cijena);
		} catch (PartsTransactionException e) {
	        model.addAttribute("partsForm", partsForm);
            model.addAttribute("parts", new Parts());
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "new_part";
		}
    	part.setSifra(sifra);
    	part.setGrupa(grupa);
    	part.setNaziv(naziv);
    	part.setMarka(marka);
    	part.setAutomobil(automobil);
    	part.setModelauta(modelauta);
    	part.setGodina(godina);
    	part.setKolicina(kolicina);
    	part.setCijena(cijena);
        service.save(part);
        return "redirect:/listall";
    }
    
    @RequestMapping("/edit/{sifra}")
    public ModelAndView showEditProductPage(@PathVariable(name = "sifra") String sifra) {
        ModelAndView mav = new ModelAndView("new_part");
        PartsInfoForm partsForm = new PartsInfoForm();
        partsForm.setEdit(true);
        Parts part = partsDAO.findById(sifra);
        partsForm.setSifra(sifra);
        partsForm.setGrupa(part.getGrupa());
        partsForm.setNaziv(part.getNaziv());
        partsForm.setMarka(part.getMarka());
        partsForm.setAutomobil(part.getAutomobil());
        partsForm.setModelauta(part.getModelauta());
        partsForm.setGodina(part.getGodina());
        partsForm.setKolicina(part.getKolicina());
        partsForm.setCijena("" + part.getCijena());
        mav.addObject("part", part);
        mav.addObject("partsForm", partsForm);
        mav.addObject("parts", new Parts());
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
        System.out.println("Trazena sifra : " + parts.getSifra());
        Parts listParts = new Parts();
        String errorMessage = null;
        if(partsDAO.findById(parts.getSifra()) == null) {
        	errorMessage = "Pogresna sifra ili ne postoji sifra sa unesenim brojem";
        } else {
        	listParts = partsDAO.findById(parts.getSifra());
        }
        System.out.println("Sifra u objektu: " + listParts.getSifra());
        mav.addObject("errorMessage", errorMessage);
        mav.addObject("listParts", listParts);
        return mav;    
    }
   
    @RequestMapping("/listgroup/{keyword}")
    public ModelAndView showGroupPage(@PathVariable(name = "keyword") String keyword) {
        ModelAndView mav = new ModelAndView("listallparts");
        mav.addObject("parts", new Parts());
        List<Parts> listParts = service.search(keyword);
        mav.addObject("listParts", listParts);
        mav.addObject("message", keyword);
        return mav;
    }
   
    @RequestMapping("/buysell/{sifra}")
    public ModelAndView showBuySell(@PathVariable(name = "sifra") String sifra, Model model) {
        ModelAndView mav = new ModelAndView("buy_sell");
        TransPartsForm transPartsForm = new TransPartsForm(sifra, 0);
        mav.addObject("transPartsForm", transPartsForm);
        mav.addObject("parts", new Parts());
        Parts part = partsDAO.findById(sifra);
        mav.addObject("part", part);
        return mav;
    }
    
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buyKolicina(Model model, TransPartsForm transPartsForm) {
System.out.println("Move BUY '" + transPartsForm.getSifra() + "' parts: " + transPartsForm.getAmount());
 
        try {
            partsDAO.buy(transPartsForm.getSifra(), //
                    transPartsForm.getAmount());
        } catch (PartsTransactionException e) {
            model.addAttribute("transPartsForm", transPartsForm);
            model.addAttribute("parts", new Parts());
            Parts part = partsDAO.findById(transPartsForm.getSifra());
            model.addAttribute("part", part);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/buy_sell";
        }
        float ukupno = transPartsForm.getAmount() * partsDAO.findById(transPartsForm.getSifra()).getCijena();
        Orders order = new Orders(new Date(), transPartsForm.getSifra(), partsDAO.findById(transPartsForm.getSifra()).getNaziv(),
        		partsDAO.findById(transPartsForm.getSifra()).getMarka(), partsDAO.findById(transPartsForm.getSifra()).getGrupa(),
        							transPartsForm.getAmount(), partsDAO.findById(transPartsForm.getSifra()).getCijena(), ukupno);
        				serviceO.save(order);
        	List<Orders> listOrders = serviceO.listAll();
        		int index = listOrders.lastIndexOf(order);
        		Orders createdOrder = listOrders.get(index);
        	long orderNum = createdOrder.getOrdernum();
        return "redirect:/orderinfo/" + orderNum;
    }
    
    
    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public String sellKolicina(Model model, TransPartsForm transPartsForm) {
System.out.println("Move SELL '" + transPartsForm.getSifra() + "' parts: " + transPartsForm.getAmount());
 
        try {
            partsDAO.sell(transPartsForm.getSifra(), //
                    transPartsForm.getAmount());
        } catch (PartsTransactionException e) {
            model.addAttribute("transPartsForm", transPartsForm);
            model.addAttribute("parts", new Parts());
            Parts part = partsDAO.findById(transPartsForm.getSifra());
            model.addAttribute("part", part);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/buy_sell";
        }
        float ukupno = transPartsForm.getAmount() * partsDAO.findById(transPartsForm.getSifra()).getCijena();
        Orders order = new Orders(new Date(), transPartsForm.getSifra(), partsDAO.findById(transPartsForm.getSifra()).getNaziv(),
        		partsDAO.findById(transPartsForm.getSifra()).getMarka(), partsDAO.findById(transPartsForm.getSifra()).getGrupa(),
        							transPartsForm.getAmount(), partsDAO.findById(transPartsForm.getSifra()).getCijena(), ukupno);
        				serviceO.save(order);
        	List<Orders> listOrders = serviceO.listAll();
        		int index = listOrders.lastIndexOf(order);
        			Orders createdOrder = listOrders.get(index);
        	long orderNum = createdOrder.getOrdernum();
        return "redirect:/orderinfo/" + orderNum;
    }
    
    @RequestMapping("/status/{sifra}")
    public ModelAndView partStatus(@PathVariable(name = "sifra") String sifra) {
        ModelAndView mav = new ModelAndView("status");
        mav.addObject("parts", new Parts());
        Parts part = partsDAO.findById(sifra);
        mav.addObject("part", part);
        return mav;    
    }
    
    @RequestMapping("/orderinfo/{orderNum}")
    public ModelAndView partStatus(@PathVariable(name = "orderNum") long orderNum) {
        ModelAndView mav = new ModelAndView("orderinfo");
        mav.addObject("parts", new Parts());
        Orders order = serviceO.get(orderNum);
        mav.addObject("order", order);
        return mav;    
    }
    
    @RequestMapping("/listorders")
    public String viewOrders(Model model) {
        List<Orders> listOrders = serviceO.listAll();
    	model.addAttribute("parts", new Parts());
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("message", "Lista svih narudžbi");
        return "listallorders";
    }
    
    @GetMapping("/showorder")
    public String showOrder(Model model){
    	model.addAttribute("order", new Orders());
    	model.addAttribute("parts", new Parts());
    	model.addAttribute("command", new FormCommand());
        return "searchorders";
    }
    
    @RequestMapping(value = "/showorder", method = RequestMethod.POST)
    public ModelAndView showOrder(@ModelAttribute("command") FormCommand command) {
        ModelAndView mav = new ModelAndView("searchorders");
        String errorMessage = null;
        long orderNum;
        Orders order = new Orders();
        try {
            orderNum = Long.parseLong(command.getOrderNum());
            order = serviceO.get(orderNum);
        } catch(Exception e) {
        	errorMessage = "Uneseni broj narudzbe ne postoji ili pogresan unos";
        }
        mav.addObject("errorMessage", errorMessage);
        mav.addObject("parts", new Parts());
        mav.addObject("command", new FormCommand());
        mav.addObject("order", order);
        return mav;    
    }
    
    @GetMapping("/showbydate")
    public String showByDate(Model model){
    	model.addAttribute("order", new Orders());
    	model.addAttribute("parts", new Parts());
    	FormCommand command = new FormCommand();
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        String strDate = formatter.format(date);  
    	command.setFromDate(strDate);
    	command.setToDate(strDate);
    	command.setDatetimeField(strDate);
    	model.addAttribute("command", command);
    	model.addAttribute("message", "Lista narudžbi po datumu");
        return "searchbydate";
    }
    
    @RequestMapping(value = "/showbydate", method = RequestMethod.POST)
    public ModelAndView showByDate(@ModelAttribute("command") FormCommand dates) {
        ModelAndView mav = new ModelAndView("searchbydate");
    	String fromDate = dates.getFromDate();
    		if(fromDate == null) {
    		    Date date = new Date();  
    		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    		    fromDate = formatter.format(date); 
    		}
    	Date date1 = null;
		try {
			date1 = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        String toDate = dates.getToDate();
			if(toDate == null) {
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
				toDate = formatter.format(date); 
		}
        Date date2 = null;
		try {
			date2 = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        mav.addObject("parts", new Parts());
    	FormCommand command = new FormCommand();
    		Date date = new Date();  
    		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    		String strDate = formatter.format(date);  
    		command.setFromDate(strDate);
    		command.setToDate(strDate);
    		mav.addObject("command", command);
        List<Orders> listOrders = serviceO.searchFromTo(date1, date2);
        mav.addObject("listOrders", listOrders);
        mav.addObject("message", "Lista narudžbi po datumu");
        return mav;    
    }
    
    @GetMapping("/search")
    public String searchByDate(Model model){
    	model.addAttribute("order", new Orders());
    	model.addAttribute("parts", new Parts());
    	FormCommand command = new FormCommand();
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        String strDate = formatter.format(date);  
    	command.setDatetimeField(strDate);
    	model.addAttribute("command", command);
    	model.addAttribute("message", "Lista narudžbi po datumu");
        return "narudzbadate";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchByDate(@ModelAttribute("command") FormCommand dates) {
        ModelAndView mav = new ModelAndView("narudzbadate");
    	String strDate = dates.getDatetimeField();
    		if(strDate == null) {
    		    Date date = new Date();  
    		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    		    strDate = formatter.format(date); 
    		}
    	Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mav.addObject("parts", new Parts());
    	FormCommand command = new FormCommand();
    		command.setDatetimeField(strDate);
    		mav.addObject("command", command);
        List<Orders> listOrders = serviceO.search(date);
        mav.addObject("listOrders", listOrders);
        mav.addObject("message", "Lista narudžbi po datumu");
        return mav;    
    }
    
    @RequestMapping("/test")
    public String test(){

        return "index3";
    }
}
