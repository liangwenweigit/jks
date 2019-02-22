package com.fly.jks.pagination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * 对分页的基本数据进行一个简单的封装
 * 用来传递分页参数和查询参数params
 */
public class Page<T> {
    private int pageNo = 1;			//页码，默认是第一页
    private int pageIndex;          //查询数据库的下标 limit pageIndex,pageSize
    private int pageSize = 10;		//每页显示的记录数，默认是10
    private int totalRecord;		//总记录数，通过数据库查询，然后放进去
    private int totalPage;			//总页数,也是在这通过计算出来
    private List<T> results;		//对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>(0);		//其他的参数我们把它分装成一个Map对象


    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 计算pageIndex:用于查询数据库的下标 limit pageIndex,pageSize
     * @param
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 计算总页数
     * @return
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


}