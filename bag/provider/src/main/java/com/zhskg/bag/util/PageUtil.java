package com.zhskg.bag.util;

/**
 * Created by fuhaibo on 2017/6/16.
 */
public class PageUtil {
    //当前页
    private int page;
    //总记录数
    private int total;
    //总页数
    private int pageCount;
    // 当前页记录开始的位置
    private int pageIndex;
    // 每页显示的记录数
    private int pageSize = 5;
    // 开始的索引值
    private int startIndex;
    // 结束的索引值
    private int endIndex;

    public PageUtil(int page, int pageSize)
    {
        this(page,pageSize,0);
    }

    public PageUtil(int page, int pageSize, int total) {
        // 计算当前页
        this.page = page;
        this.pageSize = pageSize;
        // 计算出当前页开始的位置
        this.pageIndex = this.startIndex = (page - 1) * pageSize;
        // 计算总页数
        this.pageCount = total;
        if (this.total % this.pageSize == 0) {
            this.pageCount = this.total / this.pageSize;
        } else {
            this.pageCount = this.total / this.pageSize + 1;
        }

        if (this.page <= 1) {
            this.pageIndex = this.startIndex = 1;
        }
        this.endIndex = this.pageIndex + this.pageSize-1;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}

