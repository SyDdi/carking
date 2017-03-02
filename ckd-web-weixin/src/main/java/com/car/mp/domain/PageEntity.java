package com.car.mp.domain;

import java.io.Serializable;

/**
 * 页码输出VO类
 *
 * @author wangjh7
 * @date 2016-05-03
 */
public class PageEntity implements Serializable {
    private boolean prePage = false;
    private boolean nextPage = false;
    private int currentPage = 0;
    private int totalCount = 0;
    private int pageSize = 0;

    @Override
    public String toString() {
        return "PageEntity{" +
                "currentPage=" + currentPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isPrePage() {
        return prePage;
    }

    public void setPrePage(boolean prePage) {
        this.prePage = prePage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     *
     */
    public PageEntity() {
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param totalCount
     */
    public PageEntity(int currentPage, int pageSize, int currentListCount, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        if (1 < currentPage) {
            this.prePage = true;
        }
        if (1 >= currentPage) {
            currentPage = 1;
        }
        if (0 >= pageSize) {
            pageSize = 10;
        }
        int index = currentPage * pageSize;
        if (index < totalCount || pageSize < currentListCount) {
            this.nextPage = true;
        }
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param totalCount:
     */
    public PageEntity(int currentPage, int pageSize, int totalCount) {
        this(currentPage, pageSize, 0, totalCount);
    }
}
