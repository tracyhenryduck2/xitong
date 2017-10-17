package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import com.template.utils.TStringUtils;
import com.template.utils.Utils;

/**
 * 
 * @author xuxuelin
 * 
 */
public class TJavaBean implements TBase {

	public void createTemplate(List<TableBean> columnList,
			List<TableBean> priList, List<TableBean> forList,
			List<TableBean> noPriList, String tableName, String packageName,
			String path) {
		String className = TStringUtils.tableName2ClassName(tableName);

		String str = "";
		str += "package " + packageName + ".bean;\r\n";
		str += "\r\n";
		str += "import com.avatar.db.annotation.*;\r\n";
		str += "import com.common.*;\r\n";
		str += "\r\n";
		str += "/**\r\n";
		str += " * " + Utils.getTableComments(tableName) + "\r\n";
		str += " * @author " + Static.author + "\r\n";
		str += " *" + "\r\n";
		str += " */" + "\r\n";
		str += "@Table(name=\"" + tableName + "\")\r\n";
		str += "public class " + className + "Bean {" + "\r\n";

		/**
		 * 生成属性
		 */
		for (int i = 0; i < columnList.size(); i++) {
			TableBean tBean = columnList.get(i);
			str += "\r\n";
			str += "	/**\r\n";
			str += "	 * " + tBean.getColumnComment() + " \r\n";
			str += "	*/\r\n";
			if ("id".equals(tBean.getColumnName())) {
				str += "	@Column(name=\"id\",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)\r\n";
			} else {
				str += "	@Column(name=\"" + tBean.getColumnName() + "\")\r\n";
			}
			if ("int".equals(tBean.getDataType())|| "bigint".equals(tBean.getDataType())) {
				str += "	private Long "
						+ TStringUtils.columnName2Attr(tBean.getColumnName())
						+ ";\r\n";
			} else if ("date".equals(tBean.getDataType())
					|| "datetime".equals(tBean.getDataType())
					|| "timestamp".equals(tBean.getDataType())) {
				str += "	private Date "
						+ TStringUtils.columnName2Attr(tBean.getColumnName())
						+ ";\r\n";
			} else {
				str += "	private String "
						+ TStringUtils.columnName2Attr(tBean.getColumnName())
						+ ";\r\n";
			}
		}

		/**
		 * 生成数据库对应方法
		 */
		for (int i = 0; i < columnList.size(); i++) {
			TableBean tBean = columnList.get(i);
			String type = "String";
			if ("int".equals(tBean.getDataType())) {
				type = "Long";
			} else if ("date".equals(tBean.getDataType())
					|| "datetime".equals(tBean.getDataType())
					|| "timestamp".equals(tBean.getDataType())) {
				type = "Date";
			}
			str += "    	\r\n";
			str += "    /** \r\n";
			str += "     * " + tBean.getColumnComment() + "\r\n";
			str += "     * @return \r\n";
			str += "     */ \r\n";
			str += "    public " + type + " get"
					+ TStringUtils.tableName2ClassName(tBean.getColumnName())
					+ "() {\r\n";
			str += "    	return "
					+ TStringUtils.columnName2Attr(tBean.getColumnName())
					+ ";\r\n";
			str += "    }\r\n";

			str += "    \r\n";
			str += "    /** \r\n";
			str += "     * " + tBean.getColumnComment() + "\r\n";
			str += "     * @param \r\n";
			str += "     */ \r\n";
			str += "    public void set"
					+ TStringUtils.tableName2ClassName(tBean.getColumnName())
					+ "(" + type + " "
					+ TStringUtils.columnName2Attr(tBean.getColumnName())
					+ ") {\r\n";
			str += "    	this."
					+ TStringUtils.columnName2Attr(tBean.getColumnName())
					+ " = "
					+ TStringUtils.columnName2Attr(tBean.getColumnName())
					+ ";\r\n";
			str += "    }\r\n";
		}

		/**
		 * 生成‘日期、金额’中Action处理方法
		 */

		try {

			for (int i = 0; i < columnList.size(); i++) {
				TableBean tBean = columnList.get(i);
				if ("int".equals(tBean.getDataType())
						&& tBean.getColumnComment() != null) {
					String type=null;
					if(tBean.getColumnComment().contains("日期")){
						type="Date";
					}else if(tBean.getColumnComment().contains("时间")){
						type="Time";
					}else if(tBean.getColumnComment().contains("金额")){
						type="Money";
					}
					if (type!=null) {
						str += "    	\r\n";
						str += "    public String get"
								+ TStringUtils.tableName2ClassName(tBean
										.getColumnName()) + "Str() {\r\n";
						str += "    	return Common."+type+"LongToStr("
								+ TStringUtils.columnName2Attr(tBean
										.getColumnName()) + ");\r\n";
						str += "    }\r\n";

						str += "    \r\n";
						str += "    public void set"
								+ TStringUtils.tableName2ClassName(tBean
										.getColumnName())
								+ "Str(String "
								+ TStringUtils.columnName2Attr(tBean
										.getColumnName()) + ") {\r\n";
						str += "    	this."
								+ TStringUtils.columnName2Attr(tBean
										.getColumnName())
								+ " = Common."+type+"StrToLong("
								+ TStringUtils.columnName2Attr(tBean
										.getColumnName()) + ");\r\n";
						str += "    }\r\n";
					}
				}
			}
		} catch (Exception e) {

		}

		str += "}";

		try {
			String allPath = path + "\\bean\\" + className + "Bean.java";
			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(allPath), "UTF-8");

			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
