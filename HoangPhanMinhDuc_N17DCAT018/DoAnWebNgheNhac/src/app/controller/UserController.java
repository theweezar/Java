package app.controller;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import app.entity.*;
import app.query.*;

@Controller
public class UserController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
//		Session session = ftr.getCurrentSession();
//		String hql = "FROM User u WHERE u.username LIKE :username";
//		Query query = session.createQuery(hql);
//		query.setParameter("username", username);
//		List<User> list = query.list();
		
		UserQuery query = new UserQuery(ftr);
		List<User> list = query.get("username="+username);
		
		if (list.size() != 0 && list != null){
			if (password.equals(list.get(0).getPassword())){
				HttpSession httpss = req.getSession();
				httpss.setAttribute("logged", true);
				httpss.setAttribute("username", list.get(0).getUsername());
				httpss.setAttribute("userId", list.get(0).getId());
			}
		}
		res.sendRedirect("./home.htm");
	}
	
	@Transactional
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public void register(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String rePw = req.getParameter("rePassword");
		String email = req.getParameter("email");
		
		UserQuery query = new UserQuery(ftr);
		if (query.get("username="+username).size() != 0){
			if (query.get("email="+email).size() != 0){
				if (password.equals(rePw)){
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					query.add(user);
				}
			}
		}
		res.sendRedirect("./home.htm");
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res){
		HttpSession httpss = req.getSession();
		try{
			httpss.removeAttribute("logged");
			httpss.removeAttribute("username");
			httpss.removeAttribute("userId");
			res.sendRedirect("./home.htm");
		}
		catch(Exception e){
			
		}
		
	}
	
}
