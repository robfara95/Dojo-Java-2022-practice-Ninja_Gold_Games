package com.codingdojo.ninjagold.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MainController {
	public static final String TOTALGOLD = "totalGold";
	public static final String MESSAGES = "messages";
	public static final String FARM = "farm";
	public static final String CAVE = "cave";
	public static final String HOUSE = "house";
	public static final String CASINO =  "casino";	
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute(TOTALGOLD) == null || session.getAttribute(MESSAGES) == null ) {
			session.setAttribute(TOTALGOLD, 0);		
			session.setAttribute(MESSAGES,  new ArrayList<String>());
		}

		model.addAttribute(TOTALGOLD, session.getAttribute(TOTALGOLD));
		
		return "index.jsp";
	}
	
	@GetMapping("/findgold/reset")
	public String reset (HttpSession session) {
		session.invalidate();		
		return "redirect:/";
	}
	
	
	@PostMapping("/findGold")
	public String postIndex(@RequestParam("building") String building, 
							HttpSession session,
			                RedirectAttributes redirectAttributes){
		 
		redirectAttributes.addFlashAttribute("message", "Your To Do has been added");
		 
		int goldAmount = 0;
		String msg ="";
		
		if (building.equals(FARM)) {			 
			goldAmount = getNumber(10, 20);	
			msg = fmtMessage (FARM, goldAmount);
		} else if (building.equals(CAVE)) {
			goldAmount = getNumber(5, 10);
			msg = fmtMessage (CAVE, goldAmount);
		} else if (building.equals(HOUSE)) {
			goldAmount = getNumber(2, 5);
			msg = fmtMessage (HOUSE, goldAmount);
		} else if (building.equals("casino")) {
			goldAmount = getNumber(-50, 50);
			msg = fmtMessage (CASINO, goldAmount);
		} else {
			System.out.println("on no");
		}
		
		int totalGold = (int) session.getAttribute(TOTALGOLD) + goldAmount;
		session.setAttribute(TOTALGOLD, totalGold);

		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>) session.getAttribute(MESSAGES);
		messages.add(msg);
		session.setAttribute(MESSAGES, messages);
		
		return "redirect:/";
	}
	
	public String fmtMessage (String buildingType, int goldAmount) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm a");  
		LocalDateTime now = LocalDateTime.now();
		
		return "You entered  a " + buildingType + " and " + 
				(goldAmount >= 0 ? "earned " : "lost ") + goldAmount 
				+ " gold (" + dtf.format(now) + ")";
	}
	
	public int getNumber(int min, int max) {  
		return  (int) (Math.random() * (max  - min + 1) + min);
	}
	

}
