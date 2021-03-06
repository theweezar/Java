package ptithcm.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import ptithcm.bean.*;


@Controller
@RequestMapping("/student/")
public class StudentController {
	@RequestMapping("index")
	public String index(ModelMap model){
		Student student = new Student("Nguyễn Văn Tèo",5.3,"WEB");
		model.addAttribute("student", student);
		return "student2";
	}
//	@ModelAttribute("majors")
//	public Map<String, String> getMajors() {
//		Map<String, String> mj = new HashMap<>();
//		mj.put("IT", "Information Technology");
//		mj.put("ML", "Multi Media");
//		mj.put("IOS", "IOS");
//		return mj;
//	}
	@ModelAttribute("majors")
	public List<Majors> getMajors() {
		List<Majors> majors = new ArrayList<>();
		majors.add(new Majors("APP", "Ứng dụng phần mềm"));
		majors.add(new Majors("ML", " Multi Media"));
		return majors;
	}
}
