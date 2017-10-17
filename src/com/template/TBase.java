package com.template;

import java.util.List;

public interface TBase {
	public void createTemplate(List<TableBean> columnList,List<TableBean> priList,List<TableBean> forList, List<TableBean> noPriList,String tableName,String packageName,String path);
}
