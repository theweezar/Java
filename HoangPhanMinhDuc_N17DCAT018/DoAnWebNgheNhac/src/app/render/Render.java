package app.render;

import org.springframework.ui.ModelMap;

public class Render {
	private String rootFolder;
	private String cssFolder;
	private String jsFolder;
	private String imgFolder;
	private ModelMap model;
	
	public Render(ModelMap model){
		this.rootFolder = "public";
		this.cssFolder = "css";
		this.jsFolder = "js";
		this.imgFolder = "img";
		this.model = model;
	}
	
	public void setModelAttr(String paramName, Object paramValue){
		model.addAttribute(paramName, paramValue);
	}
	
	private String getLinkCss(String fileName){
		return rootFolder + "/" + cssFolder + "/" + fileName;
	}
	
	private String getLinkJs(String fileName){
		return rootFolder + "/" + jsFolder + "/" + fileName;
	}
	
	public String render(String render){
		return render;
	}
	
	public String render(String layout, String render){
		model.addAttribute("cssLink", getLinkCss("main"));
		model.addAttribute("bootstrap", getLinkCss("bootstrap"));
		model.addAttribute("jsLink", getLinkJs("jquery.min"));
		model.addAttribute("render", render);
		return layout;
	}
}
