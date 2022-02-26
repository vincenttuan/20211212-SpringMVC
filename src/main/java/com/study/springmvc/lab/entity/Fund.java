package com.study.springmvc.lab.entity;

import java.util.Date;
import java.util.List;

public class Fund {
	private Integer fid;
	private String fname;
	private Date createtime;
	private List<Fundstock> fundstocks;
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public List<Fundstock> getFundstocks() {
		return fundstocks;
	}
	public void setFundstocks(List<Fundstock> fundstocks) {
		this.fundstocks = fundstocks;
	}
	
	
}
