package app.render;

import org.springframework.ui.ModelMap;

public class Render {
	private String rootFolder;
	private String cssFolder;
	private String jsFolder;
	private String imgFolder;
	
	public Render(){
		this.rootFolder = "public";
		this.cssFolder = "css";
		this.jsFolder = "js";
		this.imgFolder = "img";
	}
	
	private String getLinkCss(String fileName){
		return rootFolder + "/" + cssFolder + "/" + fileName;
	}
	
	private String getLinkJs(String fileName){
		return rootFolder + "/" + jsFolder + "/" + fileName;
	}
	
	public String render(ModelMap model, String layout, String render){
		model.addAttribute("cssLink", getLinkCss("main"));
		model.addAttribute("jsLink", getLinkJs("jquery.min"));
		model.addAttribute("render", render);
		return layout;
	}
}
