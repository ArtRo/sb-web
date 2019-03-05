package com.example.demo.bo;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Created by dhf_ndm on 2019/3/4
 * the previous bug derived from the previous
 */
public class PageQo implements Serializable {

    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer pageNum;

    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    String orderBy;

    public PageQo() {
    }

    public PageQo(Integer pageNum, Integer pageSize, String orderBy) {
        this.orderBy = orderBy;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
