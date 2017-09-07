package com.hfocean.uavportal.auth.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class Pager<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long currentPage;
	private int pageSize = 12;
	private long totalRows;
	private long totalPages;
	private long startRow;
	private Collection<T> pageData;
	
	public static final int maxSize = Integer.MAX_VALUE;

	public Pager(int pageSize) {
		currentPage = 1L;
		startRow = 0L;
		this.pageSize = pageSize;
	}
	
	public Pager(HttpServletRequest request) {
		String cp = request.getParameter("currentPage");
		if(cp==null || "".equals(cp.trim())) cp = "1";
		currentPage = Integer.parseInt(cp);
		startRow = 0L;
		String ps = request.getParameter("limit");
		if(ps==null||ps.trim().equals(""))
			ps = request.getParameter("pageSize");
		if(ps==null || "".equals(ps.trim())){}else{
			this.pageSize = Integer.parseInt(ps);
		}
		
	}

	public Pager(HttpServletRequest request, int pageSize) {
		String cp = request.getParameter("currentPage");
		if(cp==null || "".equals(cp.trim())) cp = "1";
		currentPage = Integer.parseInt(cp);
		startRow = 0L;
		this.pageSize = pageSize;
	}

	public Pager() {
		currentPage = 1L;
		startRow = 0L;
	}

	public Pager(Map<String, Object> request, int pageSize) {
		currentPage = (request.get("currentPage") == null ? 1L : Integer.parseInt(String.valueOf(request.get("currentPage"))));
		startRow = 0L;
		this.pageSize = pageSize;
	}

	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
		currentPage=currentPage<1?1:currentPage;
		totalPages=((totalRows-1)/pageSize)+1;
		startRow = ((currentPage - 1L) * pageSize);
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getStartRow() {
		return startRow != 0L ? startRow : (currentPage - 1L) * pageSize;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public Collection<T> getPageData() {
		return pageData;
	}

	public void setPageData(Collection<T> pageData) {
		this.pageData = pageData;
	}

	public static void main(String[] args) {
		List<Integer> i = new ArrayList<Integer>();
		i.add(1);
		i.add(2);
		i.add(3);
		for(Integer b : i){
			if(b==2)continue;
			System.out.println(b);
		}
	}
}
