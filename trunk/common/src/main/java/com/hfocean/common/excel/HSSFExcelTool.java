package com.hfocean.common.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作Excel的工具类
 */
public final class HSSFExcelTool {

	/**
	 * 导出Excel方法
	 * @param titles 标题行
	 * @param sheetname 工作单的名称
	 * @param fileName 文件名
	 * @param data 数据
	 */
	public static void exportExcel(String[] titles, String sheetname,
			String fileName, List<?> data, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		/** 创建工作簿 */
		HSSFWorkbook workbook = new HSSFWorkbook();
		/** 创建工作单 */
		HSSFSheet sheet = workbook.createSheet(sheetname);
		/** 创建第一行作为标题行 */
		HSSFRow row = sheet.createRow(0);
		/** 循环创建第一行中所有的列 */
		for (int i = 0; i < titles.length; i++){
			/** 创建列 */
			HSSFCell cell = row.createCell(i);
			/** 设置列中的值 */
			cell.setCellValue(titles[i]);
		}
		
		/** 把集合data中的数据写入Excel */
		for (int i = 0; i < data.size(); i++){
			/** 创建行 */
			row = sheet.createRow(i + 1);
			/** 获取集合中的元素 */
			Object obj =  data.get(i);
			/** 利用反射获取java对象中所有的Field */
			Field[] fields = obj.getClass().getDeclaredFields();
			/** 循环所有的Field创建列 */
			for (int j = 0; j < fields.length; j++){
				/** 创建列 */
				HSSFCell cell = row.createCell(j);
				/** 获取字段名id */
				String fieldName = fields[j].getName();
				/** 把它转化成getId */
				String getMethodName = "get" + StringUtils.capitalize(fieldName);
				/** 获取get方法 */
				Method method = obj.getClass().getMethod(getMethodName);
				/** 调用方法 */
				Object res = method.invoke(obj);
				/** 设置列中的内容 */
				cell.setCellValue(res != null ? res.toString() : "");
			}
		}
		
		/** 获取浏览器 */
		String userAgent = request.getHeader("user-agent");
		String encoding = "utf-8";
		/** 浏览器类型判断 */
		if (userAgent.toLowerCase().indexOf("msie") != -1){
			encoding = "gbk";
		}
		/** 响应过程: 
		 *  服务器： utf-8 -- iso8859-1 
		 *  浏览器：iso8859-1 -- utf-8 (firefox、chrome) msie iso8859-1 -- gbk
		 */
		fileName = new String(fileName.getBytes(encoding), "iso8859-1");
		/** 告诉浏览器输出文件(响应内容的性质) */
		response.setHeader("Content-disposition", "attachment;filename="+ fileName +".xls");
		/** 向浏览器输出 */
		workbook.write(response.getOutputStream());
	}
	
	/**
	 * 读取Excel方法
	 * @param excel 文件
	 * @return List集合
	 */
	public static List<List<Object>> importExcel(File excel) throws Exception{
		/** 通过文件创建工作簿 */
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excel));
		
		/** 获取第一个工作单 */
		HSSFSheet sheet = workbook.getSheetAt(0);
		/** 获取最后一行的索引号 */
		int lastRowNum = sheet.getLastRowNum();
		
		/** 定义集合来封装Excel中的数据 */
		List<List<Object>> excelData = new ArrayList<List<Object>>();
		
		/** 循环所有的行(第一行不要) */
		for (int i = 1; i <= lastRowNum; i++){
			/** 获取行 */
			HSSFRow row = sheet.getRow(i);
			/** 获取这一行中最后一列的索引号 */
			int lastCellNum = row.getLastCellNum();
			
			/** 定义集合来封装一行数据 */
			List<Object> rowData = new ArrayList<Object>();
			
			/** 循环所有的列 */
			for (int j = 0; j < lastCellNum; j++){
				/** 获取一列 */
				HSSFCell cell = row.getCell(j);
				/** 对列中的数据类型做判断 */
				if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){ // 布尔
					rowData.add(cell.getBooleanCellValue());
				}else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){ // 数值
					/** 判断是不是日期类型 */
					if (DateUtil.isCellDateFormatted(cell)){ // 日期
						rowData.add(cell.getDateCellValue());
					}else{
						rowData.add(cell.getNumericCellValue());
					}
				}else{
					rowData.add(cell.getStringCellValue());
				}
			}
			/** 添加一行数据 */
			excelData.add(rowData);
		}
		return excelData;
	}
}