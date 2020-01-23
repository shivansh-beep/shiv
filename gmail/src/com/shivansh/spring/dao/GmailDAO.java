package com.shivansh.spring.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shivansh.spring.model.MailInfoDATO;
import com.shivansh.spring.model.RegDTO;

public interface GmailDAO {
	public boolean register(RegDTO dto);
	public boolean logUser(HttpServletRequest req);
	public boolean sentMail(HttpServletRequest req);
	public List<MailInfoDATO> sent(String email);
	public List<MailInfoDATO> inbox(String fromMail);
	public List<MailInfoDATO> draft(String email);
	
}
