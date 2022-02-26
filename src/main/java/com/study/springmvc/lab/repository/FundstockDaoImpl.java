package com.study.springmvc.lab.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fundstock;

@Repository
public class FundstockDaoImpl implements FundstockDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Fundstock> queryAll() {
		String sql = "select s.sid, s.fid, s.symbol, s.share , "
				+ "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
				+ "from fundstock s left join fund f " + "on f.fid = s.fid order by s.sid ";
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = 
				JdbcTemplateMapperFactory
				.newInstance()
				.addKeys("sid") // Fundstock' sid																			// 的主鍵
				.newResultSetExtractor(Fundstock.class);

		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		if(offset < 0) {
			return queryAll();
		}
		String sql = "select s.sid, s.fid, s.symbol, s.share , "
				+ "f.fid as fund_fid , f.fname as fund_fname , f.createtime as fund_createtime  "
				+ "from fundstock s left join fund f " + "on f.fid = s.fid order by s.sid ";
		sql += String.format(" limit %d offset %d ", FundstockDao.LIMIT, offset);
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = 
				JdbcTemplateMapperFactory
				.newInstance()
				.addKeys("sid") // Fundstock' sid																			// 的主鍵
				.newResultSetExtractor(Fundstock.class);

		return jdbcTemplate.query(sql, resultSetExtractor);
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
