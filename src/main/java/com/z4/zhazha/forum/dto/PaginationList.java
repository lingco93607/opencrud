package com.z4.zhazha.forum.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaginationList<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -1651175945163127774L;
    private List<T> list;
    private List<Map<String, Object>> mapList;
    private long totalCount;
    private T sum;

    public PaginationList() {
        this.list = new LinkedList<T>();
    }

    /**
     * @param list
     * @param totalCount
     */
    public PaginationList(List<T> list, long totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	/**
     * @return the totalCount
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the sum
     */
    public T getSum() {
        return this.sum;
    }

    /**
     * @param sum
     *            the sum to set
     */
    public void setSum(T sum) {
        this.sum = sum;
    }

}
