package com.kaishengit.dao;
import com.kaishengit.entity.Document;
import com.kaishengit.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class DocumentDao {
	private DBHelp<Document> db = new DBHelp<Document>();
	
	public void save(Document doc) {
			String sql = "INSERT INTO t_document(id,title,content,dcreatetime,employeeid,projectid) VALUE (?,?,?,?,?,?)";
			db.executeSQL(sql, doc.getId(),doc.getTitle(),doc.getContent(),doc.getDcreatetime(),doc.getEmployeeid(),doc.getProjectid());
		
		
	}

	public List<Document> findDocumentsByProjectId(String projectid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select td.id,td.title,td.content,td.dcreatetime,employeeid,te.username,te.password,td.projectid  from t_document AS td ");
		sql.append("LEFT JOIN t_employee AS te ON td.employeeid = te.id ");
		sql.append("where td.projectid=?");
		return db.executeQueryForList(sql.toString(),new documentRowMapper(),projectid);
	
	}
	public Document findDocumentById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select td.id,td.title,td.content,td.dcreatetime,employeeid,te.username,te.password,td.projectid  from t_document AS td ");
		sql.append("LEFT JOIN t_employee AS te ON td.employeeid = te.id ");
		sql.append("where td.id=?");
		return  db.executeQueryForObject(sql.toString(),new documentRowMapper(),id);
	
	}
	private class documentRowMapper implements RowMapper<Document>{

		public Document mapperRow(ResultSet rs) throws SQLException {
			Document d = new Document();
			d.setId(rs.getString("id"));
			d.setTitle(rs.getString("title"));
			d.setContent(rs.getString("content"));
			d.setDcreatetime(rs.getString("dcreatetime"));
			d.setProjectid(rs.getString("projectid"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setPassword(rs.getString("password"));
			e.setUsername(rs.getString("username"));
			
			d.setEmployee(e);
			d.setEmployeeid(rs.getString("employeeid"));
			return d;
		}
	}

	public void delete(String id) {
		String sql="delete from t_document where id=?";
		db.executeSQL(sql, id);
		
	}

	public void edit(Document d) {
		
		String sql="update t_document set title=? , content=? where id=?";
		db.executeSQL(sql,d.getTitle(),d.getContent(),d.getId());
		
	}

	public void deleteBuProjectId(String pid) {
		String sql = "delete from t_document where projectid=?";
		db.executeSQL(sql, pid);
	}


}
