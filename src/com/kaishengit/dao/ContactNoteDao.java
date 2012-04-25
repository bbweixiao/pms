package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.ContactNote;
import com.kaishengit.entity.Employee;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class ContactNoteDao {

	private DBHelp<ContactNote> db = new DBHelp<ContactNote>();
	
	public List<ContactNote> findCNsByContactId(String contactid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select tcn.id,tcn.content,tcn.createtime,tcn.contactid,employeeid,te.username,te.password from t_contactnote as tcn ");
		sql.append("LEFT JOIN t_employee AS te ON tcn.employeeid = te.id ");
		sql.append("where tcn.contactid=?");
		return db.executeQueryForList(sql.toString(), new ContactNoteRowMapper(), contactid);
	}
	
	private class ContactNoteRowMapper implements RowMapper<ContactNote>{

		public ContactNote mapperRow(ResultSet rs) throws SQLException {
			ContactNote cn = new ContactNote();
			cn.setId(rs.getString("id"));
			cn.setContent(rs.getString("content"));
			cn.setCreatetime(rs.getString("createtime"));
			cn.setContactid(rs.getString("contactid"));
			cn.setEmployeeid(rs.getString("employeeid"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setUsername(rs.getString("username"));
			e.setPassword(rs.getString("password"));
			cn.setEmployee(e);
			
			return cn;
		}
	}

	public void save(ContactNote cn) {
		String sql = "insert into t_contactnote(id,content,createtime,contactid,employeeid) value(?,?,?,?,?)";
		db.executeSQL(sql, cn.getId(), cn.getContent(), cn.getCreatetime(), cn.getContactid(), cn.getEmployeeid());
	}

	public void deleteByContactId(String cid) {
		String sql = "delete from t_contactnote where contactid=?";
		db.executeSQL(sql, cid);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_contactnote where contactid in(select id from t_contact where projectid=?)";
		db.executeSQL(sql, pid);
	}
}
