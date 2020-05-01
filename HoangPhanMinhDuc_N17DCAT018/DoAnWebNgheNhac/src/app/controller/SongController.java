package app.controller;
//import org.hibernate.Query;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import app.entity.*;
import app.query.*;
import app.render.Render;

@Controller
public class SongController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@ModelAttribute("kind")
	public List<Kind> getKind(){
		return new KindQuery(ftr).get();
	}
	
	@RequestMapping(value="upload",method=RequestMethod.GET)
	public String uploadPage(ModelMap model, HttpServletRequest req, HttpServletResponse res ) throws Exception{
		Render r = new Render(model);
		HttpSession httpss = req.getSession();
		if (httpss.getAttribute("logged") == null) res.sendRedirect("./home.htm");
//		r.setModelAttr("listKind", new KindQuery(ftr).get());
		return r.render("mainLayout", "upload");
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public void upload(HttpServletRequest req, HttpServletResponse res ) throws Exception{
		
	}
}
