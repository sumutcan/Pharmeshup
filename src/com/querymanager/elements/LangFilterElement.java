package com.querymanager.elements;

public class LangFilterElement extends FilterElement {

	public LangFilterElement(String var, String langCode) {
		super("langMatches(lang("+var+"), \""+langCode+"\")");
		// TODO Auto-generated constructor stub
	}
	

}
