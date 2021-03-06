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

@Transactional
@Controller
public class AppController {
	@Autowired
	SessionFactory ftr;
	
//	Khi đăng nhập để nó load userName để trình bày ở frontend
	@ModelAttribute("userName")
	public String showUsername(HttpServletRequest req){
		if (req.getSession().getAttribute("logged") != null) return req.getSession().getAttribute("username").toString();
		else return "";
	}
	
//	Này để trong cái chỗ Like ngay góc bên phải frontend
	@ModelAttribute("lovePl")
	public Collection<PlayListDetail> getsongfromlovepl(HttpServletRequest req){
		if (req.getSession().getAttribute("logged") != null){
			PlayListQuery plQuery = new PlayListQuery(ftr);
			return plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 1).get(0).getPlDetail();
		}
		else return null;
	}
	
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest req, HttpServletResponse res) throws NullPointerException{
		Render r = new Render(model);
		String name = req.getParameter("search");
		SongQuery sQuery = new SongQuery(ftr);
		PlayListQuery plQuery = new PlayListQuery(ftr);
//		SongBean dưới này có thêm thuộc tính xét xem được add vào Love PLaylist hay chưa
		List<SongBean> sLBean = new ArrayList<>();
		List<Song> songList = new ArrayList<>();
		List<PlayList> playList = new ArrayList<>();
		HttpSession httpss = req.getSession();
		
		if (name == null) songList = sQuery.getAll();
		else songList = sQuery.search(name);
		
		if (httpss.getAttribute("logged") != null){
			r.setModelAttr("currUsername", httpss.getAttribute("username"));
			playList = plQuery.getPlayList((int)httpss.getAttribute("userId"), 0);
			if (playList.size() > 0){
				r.setModelAttr("empPl", false);
				r.setModelAttr("playList", playList);
			}
			else r.setModelAttr("empPl", true);
		}
//		System.out.print(httpss.getAttribute("logged"));
		for(Song s: songList){
			if (httpss.getAttribute("logged") != null){
				if (plQuery.getDetail((int)httpss.getAttribute("lovePlId"), s.getId()) == null) 
					sLBean.add(new SongBean(s, false));
				else sLBean.add(new SongBean(s, true));
			}
			else sLBean.add(new SongBean(s, false));
		}
//		Kiểm tra số lượng bài hát, nếu ko có thì set biến "emp" = true.
//		Tránh lỗi NullPointerExcetion
		if (sLBean.size() > 0){
			r.setModelAttr("emp", false);
			r.setModelAttr("songList", sLBean);
		}
		else r.setModelAttr("emp", true);
		return r.render("mainLayout", "song/home");
	}
	
}
