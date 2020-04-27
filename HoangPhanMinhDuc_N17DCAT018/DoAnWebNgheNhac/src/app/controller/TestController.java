package app.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import app.entity.*;
import app.render.*;

@Controller
public class TestController {
	@Autowired
	SessionFactory ftr;
	
	@RequestMapping("index")
	public String home(){
		return "index";
	}
	
	@RequestMapping("testlayout")
	public String testLayout(ModelMap model){
		return new Render().render(model,"mainLayout","testLayout");
	}
	
	@Transactional
	@RequestMapping("test")
	public String test(ModelMap model){
		Session session = ftr.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("list", list);
		return "test";
	}
}
