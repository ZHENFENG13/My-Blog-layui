package com.site.blog.pojo.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @program: FreeMarkeDemo
 * @description: 分页查询[带条件]输入映射
 * @author: 南街
 * @create: 2019-01-03 11:40
 **/
public class AjaxPutPage<T> {
    /**
     * 当前页码
     */
    Integer page;

    /**
     * 每页显示
     */
    Integer limit;

    /**
     * 从多少开始
     */
    Integer start;

    /**
     * 条件类
     */
    T condition;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        this.start = (this.page - 1) * this.limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    /**
     * 将符合Layui的格式转成mybtais-plus分页的page
     *
     * @return
     */
    public Page<T> putPageToPage() {
        return new Page<>(this.page, this.limit);
    }

    @Override
    public String toString() {
        return "AjaxPutPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", condition=" + condition +
                '}';
    }
}
