package com.template;

public class TableBean {

	private String columnName;
	
	/**
	 * 列位�?1,2,3
	 */
	private String ordinalPosition;//ORDINAL_POSITION
	
	/**
	 * YES,NO
	 */
	private String isNullable;	//
	
	/**
	 *  int,varchar
	 */
	private String dataType; 
	
	/**
	 * 默认�?
	 */
	private String columnDefault;
	
	/**
	 * 字符�?大长�?
	 */
	private String characterMaximumLength;
	
	/**
	 * 备注
	 */
	private String columnComment;
	
	/**
	 * PRI=表示主键 MUL=外键
	 */
	private String columnKey;

	/**
	 * 
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * 
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	public void setCharacterMaximumLength(String characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
}
