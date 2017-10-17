package com.common.tag;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FloatTag extends BodyTagSupport{

private static final long serialVersionUID = 1L;
	public static String parten = "#.#";
 	public static DecimalFormat decimal = new DecimalFormat(parten);
	private float value;
	
	public static String format(float value){
		return decimal.format(value);
	}
	
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(decimal.format(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
