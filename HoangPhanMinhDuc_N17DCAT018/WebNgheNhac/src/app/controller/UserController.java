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

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import app.entity.*;
import app.query.*;
import app.render.Render;

@Transactional
@Controller
public class UserController {
	@Autowired
	SessionFactory ftr;
	
	
	@RequestMapping("account")
	public String account(@ModelAttribute User user ,ModelMap model,
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		if (req.getSession().getAttribute("logged") != null){
			res.sendRedirect("./home.htm");
		}
		String mode = req.getParameter("m") == null ? "":req.getParameter("m").trim();
		if (mode.equalsIgnoreCase("login") || mode.equalsIgnoreCase("")) model.addAttribute("mode", 1);
		else if (mode.equalsIgnoreCase("register")) model.addAttribute("mode", 2);
		return "user/account";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login1(HttpServletResponse res) throws IOException{
		res.sendRedirect("./account.htm?m=login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute User user, ModelMap model, 
			HttpServletRequest req, HttpServletResponse res, BindingResult err) throws IOException{
		String username = user.getUsername().trim();
		String password = user.getPassword().trim();
		
		UserQuery uQuery = new UserQuery(ftr);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		
		if (username.length() == 0){
			err.rejectValue("username", "user", "Username is empty");
		}
		else if (password.length() == 0){
			err.rejectValue("password", "user", "Password is empty");
		}
		else {
			User cUser = uQuery.get("username="+username);
			if (cUser == null){
				err.rejectValue("password", "user", "Username or Password is wrong");
			}
			else if (!password.equals(cUser.getPassword())){
				err.rejectValue("password", "user", "Username or Password is wrong");
			}
			else{
				HttpSession httpss = req.getSession();
				httpss.setAttribute("logged", true);
				httpss.setAttribute("username", username);
				httpss.setAttribute("userId", cUser.getId());
//				httpss.setAttribute("userObj", list.get(0));				
				httpss.setAttribute("lovePlId", plQuery.getPlayList(cUser.getId(), 1).get(0).getId());
				res.sendRedirect("./home.htm");
			}
		}
		model.addAttribute("mode", 1);
		return "user/account";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void register1(HttpServletResponse res) throws IOException{
		res.sendRedirect("./account.htm?m=register");
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(@ModelAttribute User user, ModelMap model,
			HttpServletRequest req, HttpServletResponse res, BindingResult err) throws IOException{
		
		UserQuery userQuery = new UserQuery(ftr);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		
		String rePw = req.getParameter("rePassword").trim();
		user.setUsername(user.getUsername().trim());
		user.setPassword(user.getPassword().trim());
		user.setEmail(user.getEmail().trim());
		
		if (user.getUsername().length() == 0){
			err.rejectValue("username", "user", "Username is empty");
		}
		else if (user.getPassword().length() == 0){
			err.rejectValue("password", "user", "Password is empty");
		}
		else if (user.getEmail().length() == 0){
			err.rejectValue("email", "user", "Email is empty");
		}
		else if (userQuery.get("username="+user.getUsername()) != null){
			err.rejectValue("username", "user", "Username is existed");
		}
		else if (userQuery.get("email="+user.getEmail()) != null){
			err.rejectValue("email", "user", "Email is existed");
		}
		else if (!user.getPassword().equals(rePw)){
			err.rejectValue("password", "user", "Repassword is incorrect");
		}
		else{
			userQuery.add(user);
			PlayList pL = new PlayList();
			pL.setUser(user);
			pL.setLater(1);
			plQuery.addPlayList(pL);
			res.sendRedirect("./userlist.htm");
		}
		model.addAttribute("mode", 2);
		return "user/account";
	}
	
	@RequestMapping(value="/changepw",method=RequestMethod.GET)
	public String showform(@ModelAttribute User user,
			ModelMap model, HttpServletRequest req, HttpServletResponse res) throws Exception{
		if (req.getSession().getAttribute("logged") == null){
			res.sendRedirect("./login.htm");
		}
		model.addAttribute("mode", 3);
		return "user/account";
	}
	
	@RequestMapping(value="/changepw",method=RequestMethod.POST)
	public String changepw(@ModelAttribute User user, ModelMap model,
			HttpServletRequest req, HttpServletResponse res, BindingResult err) throws Exception{
		String newPw = req.getParameter("newPassword").trim();
		String rePw = req.getParameter("rePassword").trim();
		if (user.getPassword().length() == 0){
			err.rejectValue("password", "user", "Password is empty");
		}
		else if (newPw.length() == 0 || rePw.length() == 0){
			model.addAttribute("error1", true);
		}
		else{
			UserQuery uQuery = new UserQuery(ftr);
			User cUser = uQuery.get("username="+(String)req.getSession().getAttribute("username"));
			if (!cUser.getPassword().equals(user.getPassword().trim())){
				err.rejectValue("password", "user", "Password is incorrect");
			}
			else if (!newPw.equals(rePw)){
				model.addAttribute("error2", true);
			}
			else{
				cUser.setPassword(newPw);
				uQuery.update(cUser);
				res.sendRedirect("./home.htm");
			}
		}
		model.addAttribute("mode", 3);
		return "user/account";
	}
	
	@RequestMapping(value="/forgetpw",method=RequestMethod.GET)
	public String showform1(@ModelAttribute User user, ModelMap model, 
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		model.addAttribute("mode", 4);
		return "user/account";
	}
	
	@RequestMapping(value="/gencode",method=RequestMethod.POST)
	public void sendcode(@ModelAttribute User user, ModelMap model, 
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		
	}
	
	@RequestMapping(value="/forgetpw",method=RequestMethod.POST)
	public String retrievepw(@ModelAttribute User user, ModelMap model, 
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		return "user/account";
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if (req.getSession().getAttribute("logged") != null){
			HttpSession httpss = req.getSession();
			httpss.removeAttribute("logged");
//			httpss.removeAttribute("userObj");
			httpss.removeAttribute("username");
			httpss.removeAttribute("userId");
			httpss.removeAttribute("lovePlId");	
		}
		res.sendRedirect("./home.htm");
	}
	
}
