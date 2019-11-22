package com.yoyo.ventas.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yoyo.ventas.business.ClientBusiness;
import com.yoyo.ventas.domain.Address;
import com.yoyo.ventas.domain.Client;
import com.yoyo.ventas.domain.ContactInformation;
import com.yoyo.ventas.form.AddressForm;
import com.yoyo.ventas.form.ClientForm;
import com.yoyo.ventas.form.ContactInformationForm;


@Controller
public class ClientController {
	
	@Autowired
	private ClientBusiness clientBusiness;

	private Client client;
	private Address address;
	private ContactInformation contactInformation;
	
	@RequestMapping(value="/singin/client", method=RequestMethod.GET)
	public String initiateClientSingUp(Model model) {
		model.addAttribute("errorEmail", null);
		model.addAttribute("errorPassword", null);
		model.addAttribute("clientForm", new ClientForm());
		return "singIn";
	}
	
	@RequestMapping(value="/singin/client", method=RequestMethod.POST)
	public String getClient(@Valid ClientForm clientForm, BindingResult br, Model model) {
		
		System.out.println(clientForm.getPassword());
		System.out.println(clientForm.getConfirmPassword());
		if(!clientForm.getPassword().trim().equals(clientForm.getConfirmPassword().trim()) ) {
			System.out.println(true);
			model.addAttribute("clientForm", clientForm);
			model.addAttribute("errorPassword", "Passwords do not match");
			return "singIn";
		}
		else if(clientBusiness.findEmail(clientForm.getUsername()) != false) {
			model.addAttribute("clientForm", clientForm);
			model.addAttribute("errorEmail", "The username is already in use");
			return "singIn";	
		}
		else if(br.hasErrors()) {
			model.addAttribute("clientForm", clientForm);
			return "singIn";	
		} else {
			client = new Client();
			BeanUtils.copyProperties(clientForm, client);
			model.addAttribute("addressForm", new AddressForm());
			return "insertAddress";
		}//end if/else
	}
	
	@RequestMapping(value="/singin/address", method=RequestMethod.GET)
	public String initiateAddressSingUp(Model model) {
		model.addAttribute("addressForm",new AddressForm());
		return "insertAddress";
	}
	
	@RequestMapping(value="/singin/address", method=RequestMethod.POST)
	public String getAddress(@Valid AddressForm addressForm, BindingResult br, Model model) {
		 if(br.hasErrors()) {
			model.addAttribute("addressForm", addressForm);
			return "insertAddress";	
		} else {
			address = new Address();
			BeanUtils.copyProperties(addressForm, address);
			model.addAttribute("contactInformationForm", new ContactInformation());
			return "insertContactInformation";
		}//end if/else
	}
	
	@RequestMapping(value="/singin/contactInformation", method=RequestMethod.GET)
	public String initiatecontactInformationSingUp(Model model) {
		model.addAttribute("errorPhone", null);
		model.addAttribute("errorPhone2", null);
		model.addAttribute("contactInformationForm", new ContactInformation());
		return "insertcontactInformation";
	}
	
	@RequestMapping(value="/singin/contactInformation", method=RequestMethod.POST)
	public String save(@Valid ContactInformationForm contactInformationForm, BindingResult br, Model model) {
		
		if(clientBusiness.findByPhone(contactInformationForm.getPhone()) != false) {
			model.addAttribute("contactInformationForm", contactInformationForm);
			model.addAttribute("errorPhone", "The phone is already in use");
			return "insertcontactInformation";	
		}
		else if(clientBusiness.findByPhone(contactInformationForm.getPhone2()) != false) {
			model.addAttribute("contactInformationForm", contactInformationForm);
			model.addAttribute("errorPhone2", "The phone is already in use");
			return "insertcontactInformation";	
		}
		else if(clientBusiness.findFax(contactInformationForm.getFax()) != false) {
			model.addAttribute("contactInformationForm", contactInformationForm);
			model.addAttribute("errorFax", "The fax is already in use");
			return "insertcontactInformation";	
		}
		else if(br.hasErrors()) {
			model.addAttribute("contactInformationForm", contactInformationForm);
			return "insertcontactInformation";	
		} else {
			contactInformation = new ContactInformation();
			BeanUtils.copyProperties(contactInformationForm, contactInformation);
			clientBusiness.insertClient(address, contactInformation, client);
			return "successSingUp";
		}//end if/else
	}
}//end class