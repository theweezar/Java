package ptithcm.controller;


import java.sql.Date;
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

import ptithcm.entity.Records;
import ptithcm.entity.Staffs;

@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value="insert", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("record", new Records());
		return "record/insert";
	}
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("record") Records record, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			record.setDate(new Date(0));
			session.save(record);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công!");
		}
		catch(Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		finally {
			session.close();
		}
		return "record/insert";
	}
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String update(ModelMap model) {
		model.addAttribute("record", new Records());
		return "record/update";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute("record") Records record,ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			record.setDate(new Date(0));
			session.update(record);
			t.commit();
			model.addAttribute("message", "Cập nhập thành công!");
		}
		catch(Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "record/update";
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(ModelMap model) {
		model.addAttribute("record", new Records());
		return "record/delete";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute("record") Records record,ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			record.setDate(new Date(0));
			session.delete(record);
			t.commit();
			model.addAttribute("message", "Xóa thành công!");
		}
		catch(Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại!");
		}
		finally {
			session.close();
		}
		return "record/delete";
	}
	
	@ModelAttribute("staffs")
	public List<Staffs> getStaffs(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Staffs> list = query.list();
		return list;
	}
}
