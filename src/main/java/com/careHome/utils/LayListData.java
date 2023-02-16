package com.careHome.utils;

import java.util.List;

public class LayListData {
	private int code=0;
	private String msg="";
	//总条数
	private int count=100;
	//当此查询的数据
	private List data;

	public LayListData(List data) {
		this.data = data;
	}

	public LayListData(int count, List data) {
		super();
		this.count = count;
		this.data = data;
	}
	public LayListData() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
}
