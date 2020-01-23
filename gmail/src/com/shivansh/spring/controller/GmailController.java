package com.shivansh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shivansh.spring.model.MailInfoDATO;
import com.shivansh.spring.model.RegDTO;
import com.shivansh.spring.service.GmailService;

@Component
@RequestMapping("/")
public class GmailController {
	
	
	@Autowired
	GmailService gs;
	HttpSession hss;
	@RequestMapping("/Reg")
public String reg(){
	return "Regi";
}
	@RequestMapping(value = "/registrationData", method = RequestMethod.POST)
	public ModelAndView regi(@ModelAttribute RegDTO dto){
		boolean b=gs.register(dto);
		if(b){
		return new ModelAndView("Regi","msg","signup Successfull "); 
		}else{
			return new ModelAndView("Regi","msg","try another email");	
		}
	}
	
	@RequestMapping("/login")
public String login(){
	return "login";
}
	@RequestMapping(value = "/loginData", method = RequestMethod.POST)
	public ModelAndView logUser(HttpServletRequest req){
		String email=req.getParameter("email");
		boolean b=gs.logUser(req);
		if(b){
			hss=req.getSession();
			hss.setAttribute("email", email);
			return new ModelAndView("home","msg","logged in using :- "+email  );
		}else{
			return new ModelAndView("login","msg","login failed try again please... ");
		}
	}
		
	@RequestMapping("/compose")
	public String compose() {
		
		return "compose";
		
	}

	
	@RequestMapping(value="/sendMail",method=RequestMethod.POST)
	public ModelAndView sent(HttpServletRequest req) {
		
	
	boolean b=gs.sentMail(req);
	if(b){
		return new ModelAndView("home","msg","Mail Successfull Sent");
	}else {
		return new ModelAndView("home","msg","Mail Send Failed");
	}
		
	}
	
	@RequestMapping("/Sent")
	public ModelAndView sentInbox(HttpServletRequest req) {
		hss=req.getSession(false);
		String email=(String) hss.getAttribute("email");
		List<MailInfoDATO>mlist=gs.sent(email);
		return new ModelAndView("SentMails","mlist",mlist);
		
	}
	@RequestMapping("/Inbox")
	public ModelAndView Inbox(HttpServletRequest req) {
		hss=req.getSession(false);
		String fromMail=(String) hss.getAttribute("email");
		List<MailInfoDATO>mlist=gs.inbox(fromMail);
		return new ModelAndView("Inbox","mlist",mlist);
		
	}
       
	
	@RequestMapping("/Draft")
	public ModelAndView draft(HttpServletRequest req) {
		hss=req.getSession(false);
		String email=(String) hss.getAttribute("email");
		List<MailInfoDATO>mlist=gs.draft(email);
		return new ModelAndView("Draft","mlist",mlist);
		}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		hss=req.getSession(false);
		if(hss!=null){
			hss.invalidate();
			return new ModelAndView("login","msg","logged out successfuly");
		}else{
			return new ModelAndView("home");
		}
	}
	}


