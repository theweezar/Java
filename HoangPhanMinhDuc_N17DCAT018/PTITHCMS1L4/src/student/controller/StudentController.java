package student.controller;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import student.bean.*;

@Controller
@RequestMapping("/student/")
public class StudentController {
	@Autowired
	ServletContext application;
	@RequestMapping("index")
	public String index(HttpServletRequest request, 
			HttpSession session) {
		
		application.setAttribute("name", "Huỳnh Trung Trụ");
		application.setAttribute("level", 2);
		session.setAttribute("name", "Phan Quang Công");
		session.setAttribute("level", 4);
		request.setAttribute("name", "Nguyễn Bá Hoàng");
		request.setAttribute("level", 3);
		session.setAttribute("salary", 1000);
		request.setAttribute("photo", "../images/student.png");
		
		return "student/index";
	}
	
	@RequestMapping("index2")
	public String index2(ModelMap model){
		Student sv1 = new Student("Phạm Minh Tuấn", 5.5, "Ứng dụng phần mềm");
		Student sv2 = new Student("Nguyễn Thị Kiều Oanh", 9.5, "Thiết kế trang web");
		Student sv3 = new Student("Lê Phạm Tuấn Kiệt", 3.5, "Thiết kế trang web");
		
		List<Student> list = new ArrayList<>();
		list.add(sv2);
		list.add(sv3);
		
		Map<String, Student> map = new HashMap<>();
		map.put("OanhNTK", sv2);
		map.put("KietLPT", sv3);
		
		model.addAttribute("bean", sv1);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		return "student/index2";
	}
}
