package student.controller;

import student.bean.Student;
//import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student-mgr")
public class StudentMgrController {
	@RequestMapping()
	public String index(ModelMap model){
		model.addAttribute("message","You call index()");
		return "student-mgr";
	}
	
	@RequestMapping(params="btnInsert")
	public String insert(ModelMap model,
			@RequestParam("name")String name,
			@RequestParam("mark")Double mark,
			@RequestParam("major")String major){
		model.addAttribute("message","You call insert()");
		model.addAttribute("name", name);
		model.addAttribute("mark", mark);
		model.addAttribute("major", major);
		return "student/success";
	}
	
	@RequestMapping(params="btnUpdate")
	public String update(ModelMap model,Student student){
		model.addAttribute("message","You call update()");
		model.addAttribute("student", student);
		return "success2";
	}
	
	@RequestMapping(params="btnDelete")
	public String Delete(ModelMap model){
		model.addAttribute("message","You call delete()");
		return "student-mgr";
	}
	
	@RequestMapping(params="btnEdit")
	public String edit(ModelMap model){
		model.addAttribute("message","You call edit()");
		return "student-mgr";
	}
}
