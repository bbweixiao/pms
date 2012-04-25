package com.kaishengit.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Message;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class MessageDao {
	private DBHelp<Message> db = new DBHelp<Message>();
	public void save(Message message) {
		String sql="insert into t_message(id,content,mcreatetime,projectid,employeeid,link) values (?,?,?,?,?,?)";
	db.executeSQL(sql, message.getId(),message.getContent(),message.getMcreatetime(),message.getProjectid(),message.getEmployeeid(),message.getLink());
		
	}
	
	public List<Message> findByProjectId(String projectid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select tm.id,content,mcreatetime,projectid,link,employeeid,te.username,te.password from t_message AS tm  ");
		sql.append("left join t_employee AS te on tm.employeeid=te.id  ");
		sql.append("where tm.projectid=? ORDER BY mcreatetime DESC ");
		return db.executeQueryForList(sql.toString(),new messageRowMapper(),projectid);
		
	}
	private class messageRowMapper implements RowMapper<Message>{

		public Message mapperRow(ResultSet rs) throws SQLException {
			Message m = new Message();
			m.setId(rs.getString("id"));
			m.setContent(rs.getString("content"));
			m.setMcreatetime(rs.getString("mcreatetime"));
			m.setProjectid(rs.getString("projectid"));
			m.setLink(rs.getString("link"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setPassword(rs.getString("password"));
			e.setUsername(rs.getString("username"));
			
			m.setEmployee(e);
			m.setEmployeeid(rs.getString("employeeid"));
			return m;
		}
	}
	public void deleteByProjectId(String pid) {
		String sql = "delete from t_message where projectid=?";
		db.executeSQL(sql, pid);
	}

}
