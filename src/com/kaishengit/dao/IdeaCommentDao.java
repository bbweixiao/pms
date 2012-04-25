package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.IdeaComment;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class IdeaCommentDao {

	private DBHelp<IdeaComment> db = new DBHelp<IdeaComment>();
	
	public List<IdeaComment> finICsByIdeaId(String ideaId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select tic.id,tic.content,tic.createtime,employeeid,tic.ideaid,te.username,te.password from t_ideacomment as tic ");
		sql.append("LEFT JOIN t_employee AS te ON tic.employeeid = te.id ");
		sql.append("where tic.ideaid=?");
		return db.executeQueryForList(sql.toString(), new IdeaCommentRowMapper(), ideaId);
	}
	
	private class IdeaCommentRowMapper implements RowMapper<IdeaComment>{

		public IdeaComment mapperRow(ResultSet rs) throws SQLException {
			IdeaComment ic = new IdeaComment();
			ic.setId(rs.getString("id"));
			ic.setContent(rs.getString("content"));
			ic.setCreatetime(rs.getString("createtime"));
			ic.setIdeaid(rs.getString("ideaid"));
			ic.setEmployeeid(rs.getString("employeeid"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setUsername(rs.getString("username"));
			e.setPassword(rs.getString("password"));
			ic.setEmployee(e);
			return ic;
		}
	}

	public void save(IdeaComment ic) {
		String sql = "insert into t_ideacomment(id,content,createtime,employeeid,ideaid) value(?,?,?,?,?)";
		db.executeSQL(sql, ic.getId(), ic.getContent(), ic.getCreatetime(), ic.getEmployeeid(), ic.getIdeaid());
	}

	public void deleteById(String id) {
		String sql = "delete from t_ideacomment where id=?";
		db.executeSQL(sql, id);
	}

	public void deleteByIdeaId(String ideaId) {
		String sql = "delete from t_ideacomment where ideaid=?";
		db.executeSQL(sql, ideaId);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_ideacomment where ideaid in(select id from t_idea where projectid=?)";
		db.executeSQL(sql, pid);
	}
}
