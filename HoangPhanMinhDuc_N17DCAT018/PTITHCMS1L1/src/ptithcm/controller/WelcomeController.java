package ptithcm.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {
	@RequestMapping("welcome")
//	
	public String welcome(){
		return "welcome";
	}
	@RequestMapping("student/form")
	public String showForm(){
		return "student/form";
	}
	@RequestMapping("student/savedata")
	public String saveData(HttpServletRequest req){
		String name = req.getParameter("name");
		String mark = req.getParameter("mark");
		String major = req.getParameter("major");
		
		req.setAttribute("name", name);
		req.setAttribute("mark", mark);
		req.setAttribute("major", major);
		
		return "student/success";
	}
}
