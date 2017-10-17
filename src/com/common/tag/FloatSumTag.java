package com.common.tag;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.common.Common;

public class FloatSumTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	
	private Long value;
	
	/**
	 * 转化精度
	 */
	private Long precision;
	
	/**
	 * 显示精度
	 */
	private Long showPrecision;
	
	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	public Long getPrecision() {
		return precision;
	}

	public void setPrecision(Long precision) {
		this.precision = precision;
	}
	
	public Long getShowPrecision() {
		return showPrecision;
	}

	public void setShowPrecision(Long showPrecision) {
		this.showPrecision = showPrecision;
	}

	public int doStartTag() throws JspException {
		String str = "";
		if(precision==null) {
			str = Common.toFloatSum(value);
			if(showPrecision!=null && showPrecision<2) {
				String[] arr = str.split("\\.");
				if(arr!=null && arr.length==2) {
					if(arr[1].length()>showPrecision) {
						str = arr[0]+"."+arr[1].substring(0,showPrecision.intValue());
					}
				}
			}
		} else {
			str = Common.toFloatSum(value, precision.intValue());
			if(showPrecision!=null && showPrecision<precision) {
				String[] arr = str.split("\\.");
				if(arr!=null && arr.length==2) {
					if(arr[1].length()>showPrecision) {
						str = arr[0]+"."+arr[1].substring(0,showPrecision.intValue());
					}
				}
			}
		}
		try {
			this.pageContext.getOut().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int doEndTag() throws JspException {
		return 6;
	}
}
