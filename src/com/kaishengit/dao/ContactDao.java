package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Contact;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class ContactDao {

	private DBHelp<Contact> db = new DBHelp<Contact>();
	
	public List<Contact> findContactsByProjectId(String id) {
		String sql = "select id,name,companyname,mobile,tel,email,address,website,weibo,content,projectid,createtime from t_contact where projectid=?";
		return db.executeQueryForList(sql, new ContactRowMapper(), id);
	}

	private class ContactRowMapper implements RowMapper<Contact>{

		public Contact mapperRow(ResultSet rs) throws SQLException {
			Contact c = new Contact();
			c.setId(rs.getString("id"));
			c.setName(rs.getString("name"));
			c.setCompanyname(rs.getString("companyname"));
			c.setMobile(rs.getString("mobile"));
			c.setTel(rs.getString("tel"));
			c.setEmail(rs.getString("email"));
			c.setAddress(rs.getString("address"));
			c.setWebsite(rs.getString("website"));
			c.setWeibo(rs.getString("weibo"));
			c.setContent(rs.getString("content"));
			c.setProjectid(rs.getString("projectid"));
			c.setCreatetime(rs.getString("createtime"));
			return c;
		}
	}

	public void save(Contact c) {
		String sql = "insert into t_contact(id,name,companyname,mobile,tel,email,address,website,weibo,content,projectid,createtime) value(?,?,?,?,?,?,?,?,?,?,?,?)";
		db.executeSQL(sql, c.getId(), c.getName(), c.getCompanyname(), c.getMobile(), c.getTel(), c.getEmail(), c.getAddress(), c.getWebsite(), c.getWeibo(), c.getContent(), c.getProjectid(), c.getCreatetime());
	}

	public Contact findContactById(String id) {
		String sql = "select id,name,companyname,mobile,tel,email,address,website,weibo,content,projectid,createtime from t_contact where id=?";
		return db.executeQueryForObject(sql, new ContactRowMapper(), id);
	}

	public void update(Contact c) {
		String sql = "update t_contact set name=?,companyname=?,mobile=?,tel=?,email=?,address=?,website=?,weibo=?,content=?,projectid=?,createtime=? where id=?";
		db.executeSQL(sql, c.getName(), c.getCompanyname(), c.getMobile(), c.getTel(), c.getEmail(), c.getAddress(), c.getWebsite(), c.getWeibo(), c.getContent(), c.getProjectid(), c.getCreatetime(), c.getId());
	}

	public void deleteById(String id) {
		String sql = "delete from t_contact where id=?";
		db.executeSQL(sql, id);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_contact where projectid=?";
		db.executeSQL(sql, pid);
	}
}
