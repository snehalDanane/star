package controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.VendingService;

@Controller
class VendingController {
	
	VendingService vendingService;

	@Inject
	public VendingController(VendingService vendingService) {
		this.vendingService = vendingService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String homePage(Model model) {


		model.addAttribute("vendingObj", vendingService);
		return "home";
	}

	@RequestMapping(value = "/makeSelection/{id}", method = RequestMethod.GET)
	public String itemSelection(@PathVariable int id, Model model) {
		vendingService.setProducts(vendingService.productsSelected(id));
		model.addAttribute("vendingObj", vendingService);
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value="/makePurchase")
	public String makePurchase(Model model) {
		vendingService.makePurchase();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/addMoney/{id}", method = RequestMethod.GET)
	public String addMoney(@PathVariable int id, Model model) {
		vendingService.addMoney(id);
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/changeReturn")
	public String returnChange(Model model) {
		vendingService.returnChange();
		return "redirect:/";
	}

//	model.addAttribute("products", vendingService.getProducts());
//	model.addAttribute("balance", vendingService.getBalance());
//	model.addAttribute("selection", vendingService.getSelection());
//	model.addAttribute("myChange", vendingService.getMyChange());
//	model.addAttribute("message", vendingService.getMessage());
	//vendingService.getProducts();


}