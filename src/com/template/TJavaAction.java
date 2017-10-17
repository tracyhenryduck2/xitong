package com.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import com.template.utils.TStringUtils;
import com.template.utils.Utils;

public class TJavaAction implements TBase {

	public void createTemplate(List<TableBean> columnList,
			List<TableBean> priList, List<TableBean> forList,
			List<TableBean> noPriList, String tableName, String packageName,
			String path) {

		String className = TStringUtils.tableName2ClassName(tableName);
		String bean = TStringUtils.columnName2Attr(tableName) + "Bean";
		String upBean = className + "Bean";

		String str = "";
		str += "package " + packageName + ".action;\r\n";
		str += "\r\n";
		str += "import java.util.List;\r\n";
		str += "import java.util.Map;\r\n";
		str += "import " + packageName + ".bean." + className + "Bean;\r\n";
		str += "import " + packageName + ".dao." + className + "DAO;\r\n";
		str += "import com.common.Common;\r\n";
		str += "import com.common.BaseActionSupport;\r\n";
		str += "\r\n";
		str += "/**\r\n";
		str += " * " + Utils.getTableComments(tableName) + "\r\n";
		str += " * @author " + Static.author + "\r\n";
		str += " *" + "\r\n";
		str += " */" + "\r\n";
		str += "public class " + className
				+ "Action extends BaseActionSupport {" + "\r\n";
		str += "	/**" + "\r\n";
		str += "	 * " + "\r\n";
		str += "	 */" + "\r\n";
		str += "	private static final long serialVersionUID = 1L;" + "\r\n";
		str += "    private " + className + "DAO dao = new " + className
				+ "DAO(); \r\n";
		String forStr = "";
		for (int i = 0; i < forList.size(); i++) {
			String colName = forList.get(i).getColumnName().substring(0,
					forList.get(i).getColumnName().length() - 3);
			String forClassName = TStringUtils.tableName2ClassName(colName);
			String varForClassName = TStringUtils.columnName2Attr(colName);
			forStr += "        List<Map<String, Object>> " + varForClassName
					+ "List = " + varForClassName + "DAO.getList();\r\n";
			forStr += "        request.setAttribute(\"" + varForClassName
					+ "List\", " + varForClassName + "List);\r\n";
			str += "    private " + forClassName + "DAO " + varForClassName
					+ "DAO = new " + forClassName + "DAO(); \r\n";
		}

		str += "    private " + upBean + " " + bean + " = new " + className
				+ "Bean();    \r\n";
		str += "    private final String tableDesc = \""
				+ Utils.getTableComments(tableName) + "\";\r\n";

		str += "    /**    \r\n";
		str += "     * 转向添加页面  \r\n";
		str += "     * @return  \r\n";
		str += "     */    \r\n";
		str += "    public String toAdd" + className + "() {\r\n";
		str += forStr;
		str += "        if (\"1\".equals(oper)) {   \r\n";
		String priPramStr = "";
		for (int i = 0; i < priList.size(); i++) {
			priPramStr += ","
					+ bean
					+ ".get"
					+ TStringUtils.tableName2ClassName(priList.get(i)
							.getColumnName()) + "()";
		}
		if (priPramStr.length() > 0) {
			priPramStr = priPramStr.substring(1);
		}
		str += "    	    " + bean + " = dao.select(" + className
				+ "Bean.class," + priPramStr + ");  \r\n";
		str += "    	}    \r\n";
		str += "    	return \"toAdd" + className + "\";    \r\n";
		str += "    } \r\n";
		str += " \r\n";

		str += "    /**    \r\n";
		str += "     * 新增\r\n";
		str += "     */    \r\n";
		str += "    public String add" + className + "() {  \r\n";
		str += "        try {   \r\n";
		str += "            showMessage = \"新增\"+tableDesc; \r\n";
		str += "            boolean result = true;  \r\n";
		str += "            if (\"1\".equals(oper)) {    \r\n";
		str += "                showMessage = \"编辑\"+tableDesc;  \r\n";
		str += "                result = dao.update(" + bean + "); \r\n";
		str += "            } else { \r\n";
		str += "                result = dao.insert(" + bean + "); \r\n";
		str += "            }\r\n";
		str += "            if (result) {  \r\n";
		str += "                showMessage += \"成功\";  \r\n";
		str += "                return reload_success; \r\n";
		str += "            } else {\r\n";
		str += "                showMessage += \"失败\";  \r\n";
		str += "                return error;   \r\n";
		str += "            }  \r\n";
		str += "        } catch (Exception e) {    \r\n";
		str += "            showMessage = \"数据异常，操作失败\";   \r\n";
		str += "            return error;  \r\n";
		str += "        } \r\n";
		str += "    } \r\n";
		str += " \r\n";

		str += "    /**    \r\n";
		str += "     * 编辑部分字段专用\r\n";
		str += "     * @return\r\n";
		str += "     */\r\n";
		str += "    public String addTest2(){\r\n";
		str += "        showMessage = \"编辑2\"+tableDesc;\r\n";
		str += "        String[] param={\r\n";
		String fStr = "";
		for(int i=0;i<columnList.size();i++) {
			if(i!=0) {
				fStr += ",";
			}
			fStr += "\""+TStringUtils.columnName2Attr(columnList.get(i).getColumnName())+"\"";
			if((i>0 && i%5==0) || i==columnList.size()-1) {
				str += "            "+fStr+"\r\n";
				fStr = "";
			}
		}
		str += "        };\r\n";
		str += "        boolean result=dao.update("+bean
				+ ",param);\r\n";
		str += "        if (result) { \r\n";
		str += "            showMessage += \"成功\";\r\n";
		str += "            return reload_success;\r\n";
		str += "        } else {\r\n";
		str += "            showMessage += \"失败\"; \r\n";
		str += "           return error; \r\n";
		str += "        }\r\n";
		str += "    }\r\n";

		str += "    /**    \r\n";
		str += "     * 删除操作 \r\n";
		str += "     */    \r\n";
		str += "    public String del" + className + "() {  \r\n";
		str += "    	try {\r\n";
		str += "    		boolean result = dao.delete(" + className + "Bean.class,"
				+ priPramStr + ");  \r\n";
		str += "    		if (result) {\r\n";
		str += "    		    showMessage = \"删除\"+tableDesc+\"成功\"; //reload   \r\n";
		str += "    		    return reload_success;  \r\n";
		str += "    		} else {\r\n";
		str += "    		    showMessage = \"删除\"+tableDesc+\"失败\";  \r\n";
		str += "    		    return error; \r\n";
		str += "    		}  \r\n";
		str += "    	} catch (Exception e) {  \r\n";
		str += "    		return exception; \r\n";
		str += "    	}    \r\n";
		str += "    } \r\n";
		str += " \r\n";

		str += " \r\n";
		str += "    /**    \r\n";
		str += "     * 删除操作 \r\n";
		str += "     */    \r\n";
		str += "    public String del" + className + "s() { \r\n";
		str += "    	try {\r\n";
		str += "    		String[] idArr = request.getParameterValues(\"idArr\");   \r\n";
		str += "    		String ids = Common.array2String(idArr);   \r\n";
		str += "    		boolean result = dao.deletes(" + className
				+ "Bean.class,ids);\r\n";
		str += "    		if (result) {\r\n";
		str += "    		    showMessage = \"删除\"+tableDesc+\"成功\"; //reload   \r\n";
		str += "    		    return reload_success;  \r\n";
		str += "    		} else {\r\n";
		str += "    		    showMessage = \"删除\"+tableDesc+\"失败\";  \r\n";
		str += "    		    return error; \r\n";
		str += "    		}  \r\n";
		str += "    	} catch (Exception e) {  \r\n";
		str += "    		return exception; \r\n";
		str += "    	}    \r\n";
		str += "    } \r\n";

		str += "    public String search() {\r\n";
		str += "        return \"search\"; \r\n";
		str += "    }\r\n";

		str += " \r\n";
		str += "    /**    \r\n";
		str += "     * 查询列表页面  \r\n";
		str += "     * @return  \r\n";
		str += "     */    \r\n";
		str += "    public String list() {\r\n";
		str += "    	page.execute(request, \"ID\", \"desc\");\r\n";
		str += "    	List<Map<String, Object>> list = dao.getPageList(page, "
				+ bean + ");\r\n";
		str += "    	request.setAttribute(\"list\", list);   \r\n";
		str += "    	return \"list\";    \r\n";
		str += "    } \r\n";
		str += " \r\n";
		str += "    public " + upBean + " get" + upBean + "() { \r\n";
		str += "    	return " + bean + ";    \r\n";
		str += "    } \r\n";
		str += " \r\n";
		str += "    public void set" + upBean + "(" + upBean + " " + bean
				+ ") {   \r\n";
		str += "    	this." + bean + " = " + bean + ";\r\n";
		str += "    } \r\n";
		str += "}\r\n";
		try {
			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(path + "\\action\\" + className
							+ "Action.java"), "UTF-8");
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
