package net.cst.zap.api.model;

public enum SeleniumDriver {

	HTMLUNIT("HtmlUnit"), 
	FIREFOX("Firefox"),

	CHROME("Chrome"),
	PHANTOMJS("PhantomJS");
	
	private String name;
	
	private SeleniumDriver(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
