package com.study.springmvc.lab.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fundstock;

@Repository
public class FundstockDaoImpl implements FundstockDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Fundstock> queryAll() {
		String sql = "select s.sid, s.fid, s.symbol, s.share from fundstock s order by s.sid";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fundstock get(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Fundstock fundstock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Fundstock fundstock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer sid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
