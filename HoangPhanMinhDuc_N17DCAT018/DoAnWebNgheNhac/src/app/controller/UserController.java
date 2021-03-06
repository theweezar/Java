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
		String mode = req.getParameter("m") == null ? "":req.getParameter("m").trim();
		System.out.print(mode);
		if (mode.equalsIgnoreCase("login") || mode.equalsIgnoreCase("")) model.addAttribute("mode", 1);
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
		PlayListQuery plQuery = new PlayListQuery(ftr);
		User cUser = uQuery.get("username="+username);
		if (cUser != null){
			if (password.equals(cUser.getPassword())){
				HttpSession httpss = req.getSession();
				httpss.setAttribute("logged", true);
				httpss.setAttribute("username", username);
				httpss.setAttribute("userId", cUser.getId());
//				httpss.setAttribute("userObj", list.get(0));				
				httpss.setAttribute("lovePlId", plQuery.getPlayList(cUser.getId(), 1).get(0).getId());
				res.sendRedirect("./home.htm");
			}
			else res.sendRedirect("./account.htm?m=login");
		}
		else res.sendRedirect("./account.htm?m=login");
	}
	
	@Transactional
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public void register(@ModelAttribute User user,
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		UserQuery userQuery = new UserQuery(ftr);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		
		String rePw = req.getParameter("rePassword").trim();
		user.setUsername(user.getUsername().trim());
		user.setPassword(user.getPassword().trim());
		user.setEmail(user.getEmail().trim());
		
		if (userQuery.get("username="+user.getUsername()) == null){
			if (userQuery.get("email="+user.getEmail())== null){
				if (user.getPassword().equals(rePw)){
					userQuery.add(user);
					PlayList pL = new PlayList();
					pL.setUser(user);
					pL.setLater(1);
					plQuery.addPlayList(pL);
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
//		httpss.removeAttribute("userObj");
		httpss.removeAttribute("username");
		httpss.removeAttribute("userId");
		httpss.removeAttribute("lovePlId");
		res.sendRedirect("./home.htm");
	}
	
}
