package com.shivansh.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shivansh.spring.dao.GmailDAO;
import com.shivansh.spring.model.MailInfoDATO;
import com.shivansh.spring.model.RegDTO;
@Component
public class GmailServiceImp implements GmailService{
@Autowired
GmailDAO gd;

	@Override
	public boolean register(RegDTO dto) {
		// TODO Auto-generated method stub
		boolean b=gd.register(dto);
		return b;
	}

	@Override
	public boolean logUser(HttpServletRequest req) {

		return gd.logUser(req);
	}

	@Override
	public boolean sentMail(HttpServletRequest req) {
		// TODO Auto-generated method stub
		boolean b=gd.sentMail(req);
		return b;
	}
	@Override
	public List<MailInfoDATO> sent(String email) {
		// TODO Auto-generated method stub
		
		return gd.sent(email);
	}
	
	@Override
	public List<MailInfoDATO> inbox(String fromMail) {
		// TODO Auto-generated method stub
		
		return gd.inbox(fromMail);
	}
	
	public List<MailInfoDATO> draft(String email) {
		// TODO Auto-generated method stub
		return gd.draft(email);
	}
}
