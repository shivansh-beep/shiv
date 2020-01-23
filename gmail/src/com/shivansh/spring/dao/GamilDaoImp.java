package com.shivansh.spring.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shivansh.spring.model.MailInfoDATO;
import com.shivansh.spring.model.RegDTO;
@Component
public class GamilDaoImp implements GmailDAO {

	@Autowired
	SessionFactory sf;
	HttpSession hss;
	@Override
	public boolean register(RegDTO dto) {
		// TODO Auto-generated method stub
		Session ss=sf.openSession();
		System.out.println(dto.getUserName());
		Criteria cr=ss.createCriteria(RegDTO.class);
		cr.add(Restrictions.eq("email", dto.getEmail()));
		String eml= dto.getEmail();
		RegDTO rdto=(RegDTO) cr.uniqueResult();
		if(rdto==null&&eml.contains("@")&&eml.contains(".com")){
			ss.save(dto);
			ss.beginTransaction().commit();
			ss.close();
			return true;
		}else{	ss.close();
		return false;
		}
	
	}

	@Override
	public boolean logUser(HttpServletRequest req) {

		Session ss=sf.openSession();
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Criteria cr=ss.createCriteria(RegDTO.class);
		cr.add(Restrictions.eq("email", email));
		cr.add(Restrictions.eq("password", password));
		RegDTO rdto=(RegDTO) cr.uniqueResult();
		if(rdto!=null){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public boolean sentMail(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String	toemail=req.getParameter("email");
		String inbox=req.getParameter("uinbox");
		this.hss=req.getSession(false);
		String fromMail=(String) this.hss.getAttribute("email");
		Session ss=sf.openSession();
		
		MailInfoDATO mdto=null;
		Query qry=ss.createQuery("from RegDTO where email='"+toemail+"'");
		RegDTO rdto=(RegDTO) qry.uniqueResult();
		List<MailInfoDATO> mlist;
		
		
		
		if(rdto!=null) {
			mdto=new MailInfoDATO();
			mdto.setUto(toemail);
			mdto.setUinbox(inbox);
			mdto.setUsent(fromMail);
			
			mlist=rdto.getMlist();
			mlist.add(mdto);
			ss.saveOrUpdate(rdto);
			ss.beginTransaction().commit();
			ss.close();
		return true;
		}else {
				mdto=new MailInfoDATO();
				mdto.setUdraft(toemail);
				mdto.setUsent(fromMail);
				mdto.setUinbox(inbox);
				qry=ss.createQuery("from RegDTO where email='"+fromMail+"'");
				rdto=(RegDTO) qry.uniqueResult();
				mlist=rdto.getMlist();
				mlist.add(mdto);
				ss.saveOrUpdate(rdto);
				ss.beginTransaction().commit();
				ss.close();
			return false;
		}
	}
	@Override
	public List<MailInfoDATO> sent(String email) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailInfoDATO.class);
		cr.add(Restrictions.eq("usent", email));
		cr.add(Restrictions.isNotNull("uto"));
		List<MailInfoDATO>mlist=cr.list();
		ss.close();
		return mlist;
	}
	
	@Override
	public List<MailInfoDATO> inbox(String fromMail) {
		
		Session ss=sf.openSession();
Criteria cr=ss.createCriteria(RegDTO.class);
System.out.println(fromMail);
cr.add(Restrictions.eq("email", fromMail));
//cr.add(Restrictions.isNull("udraft"));
RegDTO rdto=(RegDTO) cr.uniqueResult();

		List<MailInfoDATO> mlist=rdto.getMlist();
	for (MailInfoDATO mailInfoDATO : mlist) {
		System.out.println(mailInfoDATO.getUinbox());
	}
		ss.close();
		return mlist;
		
	}
	
	@Override
	public List<MailInfoDATO> draft(String email) {
		
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailInfoDATO.class);
		cr.add(Restrictions.eq("usent", email));
		cr.add(Restrictions.isNull("uto"));
		cr.add(Restrictions.isNotNull("udraft"));
		List<MailInfoDATO>mlist=cr.list();
		ss.close(); 
		return mlist;
		
	}

}
