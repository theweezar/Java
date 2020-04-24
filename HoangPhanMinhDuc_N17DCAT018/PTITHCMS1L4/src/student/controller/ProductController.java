package student.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import student.bean.*;
@Controller
@RequestMapping("/product/")
public class ProductController {
	@RequestMapping("list")
	public String list(ModelMap model){
		List<Product> list = new ArrayList<>();
		list.add(new Product("Nokia Star",1000.0,0.05));
		list.add(new Product("iPhone 9",1500.0,0.1));
		list.add(new Product("Samsung Edge",750.0,0.15));
		list.add(new Product("Sony Experia",500.0,0.0));
		
		model.addAttribute("prods", list);
		return "product/list";
		
	}
}
