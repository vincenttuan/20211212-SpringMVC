package com.study.springmvc.lab.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;

@Repository
public class FundDaoImpl implements FundDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Fund> queryAll() {
		return queryAllCase2();
	}
	
	private List<Fund> queryAllCase1() {
		String sql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Fund>(Fund.class));
	}
	
	private List<Fund> queryAllCase2() {
		String sql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		RowMapper<Fund> rm = (ResultSet rs, int rowNum) -> {
			Fund fund = new Fund();
			fund.setFid(rs.getInt("fid"));
			fund.setFname(rs.getString("fname"));
			fund.setCreatetime(rs.getDate("createtime"));
			// 根據 fid 查詢 fundstock 列表
			String sql2 = "select s.sid, s.fid, s.symbol, s.share "
					+ "from fundstock s "
					+ "where s.fid = ? "
					+ "order by s.sid";
			Object[] args = {fund.getFid()};
			List<Fundstock> fundstocks = jdbcTemplate.query(
					sql2, 
					args, 
					new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
			fund.setFundstocks(fundstocks);
			return fund;
		};
		return jdbcTemplate.query(sql, rm);
	}
	

	@Override
	public List<Fund> queryPage(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fund get(Integer fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Fund fund) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Fund fund) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer fid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
