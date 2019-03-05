package com.example.demo.vo;

import com.example.demo.bo.PageQo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/3/4
 * the previous bug derived from the previous
 */
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = -4426958360243585882L;

    private int pageNum;

    private int pageSize;

    private long total;

    private int pages;

    private List<T> list;

    public PageVo(){}

    public PageVo(PageQo pageQO) {
        this.setPageNum(pageQO.getPageNum());
        this.setPageSize(pageQO.getPageSize());
    }

    public PageVo(List<T> poList) {
        BeanUtils.copyProperties(new PageInfo<T>(poList), this);
    }

    public static <T> PageVo<T> build(List<T> poList) {
        return new PageVo<>(poList);
    }

    /**
     * @desc 构建一个分页VO对象
     *
     * @param page 数据库查出来的分页数据列表
     */
    public static <T> PageVo<T> build(Page<T> page) {
        PageVo<T> pageVO = new PageVo<T>();
        BeanUtils.copyProperties(page.toPageInfo(), pageVO);
        return pageVO;
    }

    /**
     * @desc 构建一个分页VO对象
     * 试用场景为：从数据库取出的PO列表不做任何处理，转化为VO列表返回
     *
     * @param page 数据库查出来的分页数据列表
     * @param voClazz 要转为的VO类
     */
    public static <T, E> PageVo<T> build(Page<E> page, Class<T> voClazz) {

        PageVo<T> pageVO = new PageVo<T>();
        BeanUtils.copyProperties(page, pageVO, "list");

        try {
            List<T> VOs = Lists.newArrayList();
            List<E> POs = page.getResult();
            if (!CollectionUtils.isEmpty(POs)) {
                for (E e : POs) {
                    T t = voClazz.newInstance();
                    BeanUtils.copyProperties(e, t);
                    VOs.add(t);
                }
            }
            pageVO.setList(VOs);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        return pageVO;
    }

    /**
     * @desc 构建一个分页VO对象
     * 试用场景为：将处理好的VO列表封装返回
     *
     * @param poPage 数据库查出来的分页数据
     * @param voList vo数据列表
     */
    public static <T, E> PageVo<T> build(Page<E> poPage, List<T> voList) {
        PageVo<T> page = new PageVo<T>();
        BeanUtils.copyProperties(poPage, page, "list");
        page.setList(voList == null ? Lists.newArrayList() : voList);
        return page;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static int getPages(long total, int pageSize) {
        if (total == 0 || pageSize == 0) {
            return 0;
        }
        return (int) (total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1));
    }

    public int getPages(){
        return getPages(this.total, this.pageSize);
    }


}
