package com.web.utils;

import java.util.List;

public class PageModel<T> {
	private Integer pageIndex;
	private Integer totalRecord;
	private Integer pageSize;
	private List<T> list;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex == null || pageIndex < 1 ? 1 : pageIndex;
	}

	public Integer getStartIndex() {
		return (pageIndex - 1) * pageSize;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalPage() {
		return totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize == null || pageSize < 5 ? 5 : pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
