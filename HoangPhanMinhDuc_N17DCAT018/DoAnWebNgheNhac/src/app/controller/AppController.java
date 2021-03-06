package app.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.*;

import app.bean.SongBean;
import app.entity.*;
import app.render.*;
import app.query.*;

@Controller
public class AppController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping("home")
	public String home(ModelMap model, HttpServletRequest req, HttpServletResponse res) throws NullPointerException{
		Render r = new Render(model);
		SongQuery sQuery = new SongQuery(ftr);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		List<SongBean> sLBean = new ArrayList<>();
		List<Song> songList = sQuery.getAll();
		HttpSession httpss = req.getSession();
		if (httpss.getAttribute("logged") != null){
			r.setModelAttr("currUsername", httpss.getAttribute("username"));
		}
		System.out.print(httpss.getAttribute("logged"));
		for(Song s: songList){
			if (httpss.getAttribute("logged") != null){
				if (plQuery.getDetail((int)httpss.getAttribute("lovePlId"), s.getId()) == null) 
					sLBean.add(new SongBean(s, false));
				else sLBean.add(new SongBean(s, true));
			}
			else sLBean.add(new SongBean(s, false));
		}
		if (sLBean.size() > 0){
			r.setModelAttr("emp", false);
			r.setModelAttr("songList", sLBean);
		}
		else r.setModelAttr("emp", true);
		return r.render("mainLayout", "home");
	}
	
}
