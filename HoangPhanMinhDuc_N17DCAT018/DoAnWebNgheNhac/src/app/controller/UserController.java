package app.controller;
//import org.hibernate.Query;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import app.entity.*;
import app.query.*;
import app.render.Render;

@Controller
public class UserController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping("account")
	public String account(@ModelAttribute User user ,ModelMap model,
			HttpServletRequest req){
		Render r = new Render(model);
		String mode = req.getParameter("m").trim();
		System.out.print(mode);
		if (mode.equalsIgnoreCase("login") || mode == null) model.addAttribute("mode", 1);
		else if (mode.equalsIgnoreCase("register")) model.addAttribute("mode", 2); 
		return "user/account";
	}
	
	
	
	@Transactional
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(@ModelAttribute User user,
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		String username = user.getUsername().trim();
		String password = user.getPassword().trim();
		
		UserQuery uQuery = new UserQuery(ftr);
//		PlayListQuery plQuery = new PlayListQuery(ftr);
		List<User> list = uQuery.get("username="+username);
		if (list.size() != 0 && list != null){
			if (password.equals(list.get(0).getPassword())){
//				int lovePlId = plQuery.getPlayList(list.get(0).getId(), 1).get(0).getId();
				HttpSession httpss = req.getSession();
				httpss.setAttribute("logged", true);
				httpss.setAttribute("username", list.get(0).getUsername());
				httpss.setAttribute("userId", list.get(0).getId());
//				httpss.setAttribute("lovePlId", lovePlId);
			}
		}
		res.sendRedirect("./home.htm");
	}
	
	@Transactional
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public void register(@ModelAttribute User user,
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		UserQuery userQuery = new UserQuery(ftr);
		
		String rePw = req.getParameter("rePassword").trim();
		user.setUsername(user.getUsername().trim());
		user.setPassword(user.getPassword().trim());
		user.setEmail(user.getEmail().trim());
		
		if (userQuery.get("username="+user.getUsername()) != null){
			if (userQuery.get("email="+user.getEmail())!= null){
				if (user.getPassword().equals(rePw)){
					userQuery.add(user);
					res.sendRedirect("./userlist.htm");
				}
				else res.sendRedirect("./passErr.htm");
			}
			else res.sendRedirect("./emailErr.htm");
		}
		else res.sendRedirect("./usernameErr.htm");
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession httpss = req.getSession();
		httpss.removeAttribute("logged");
		httpss.removeAttribute("username");
		httpss.removeAttribute("userId");
		httpss.removeAttribute("lovePlId");
		res.sendRedirect("./home.htm");
	}
	
}
