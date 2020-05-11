package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Users;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql = "FROM Users";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> list = query.list();
		model.addAttribute("users", list);
		return "user/index";
	}
	@RequestMapping(value="insert", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user", new Users());
		return "user/insert";
	}
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("user") Users user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		finally {
			session.close();
		}
		return "user/insert";
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(ModelMap model) {
		model.addAttribute("user", new Users());
		return "user/delete";
	}
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("user") Users user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(user);
			t.commit();
			model.addAttribute("message", "Xóa thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}
		finally {
			session.close();
		}
		return "user/delete";
	}
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String update(ModelMap model) {
		model.addAttribute("user", new Users());
		return "user/update";
	}
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("user") Users user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
			model.addAttribute("message", "Cập nhập thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "user/update";
	}
}
