package app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import app.query.*;

@Transactional
@Controller
public class AjaxController {
	@Autowired
	SessionFactory ftr;
	@RequestMapping(value="addtoplaylist",method=RequestMethod.POST)
	public void addtoplaylist(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String songId = req.getParameter("songId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		System.out.print(plQuery.getPlayList(1, 1).get(0).getId());
		res.getWriter().print(songId);
	}
}
