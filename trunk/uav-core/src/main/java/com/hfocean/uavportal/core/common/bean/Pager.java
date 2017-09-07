package com.hfocean.uavportal.core.common.bean;

import com.hfocean.common.util.VerdictUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
* 
* @author Leslie.Lam
* @create 2017-06-22 16:41
**/
public class Pager{

    private int pageNumber;//页数

    private long totalElements;//总条数

    private long totalPages;//总页数

    private int pageSize;//页大小

    private int numberOfElements;//当前页条数

    private Collection content;

    public Pager(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Pager(HttpServletRequest request) {
        String pn = request.getParameter("page");
        pageNumber = (VerdictUtil.isBlank(pn)||Integer.parseInt(pn)<0)?1:Integer.parseInt(pn);
        String ps = request.getParameter("size");
        pageSize = (VerdictUtil.isBlank(ps)||Integer.parseInt(ps)<0)?10:Integer.parseInt(ps);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNumberOfElements() {
        return null==content?numberOfElements:content.size();
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public Collection getContent() {
        return content;
    }

    public void setContent(Collection content) {
        this.content = content;
    }
}
