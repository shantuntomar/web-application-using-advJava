package in.shantun.dao;

import in.shantun.dto.Student;
import java.sql.*;
import in.shantun.util.JdbcUtil;
import java.io.*;

public class StudentDaoImpl implements IStudentDao {
	
	Connection connection = null;

	@Override
	public String save(Student student) {
		String sqlInsertQuery = "insert into member_data(`age` , `name` , `address`) values(? , ? , ?)";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) pstmt = connection.prepareStatement(sqlInsertQuery);
			if(pstmt != null) {
				pstmt.setInt(1, student.getAge());
				pstmt.setString(2 , student.getName());
				pstmt.setString(3, student.getAddr());
			}
			if(pstmt != null) {
				int rowAffected = pstmt.executeUpdate();
				if(rowAffected == 1) status = "SUCCESS";
				else status = "FAIL";
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
			status = "FAIL";
		}
		return status;
	}

	@Override
	public Student findById(Integer sid) {
		String sqlSelectQuery = "select * from member_data where id = ?";
		PreparedStatement pstmt = null;
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) pstmt = connection.prepareStatement(sqlSelectQuery);
			if(pstmt != null) {
				pstmt.setInt(1, sid);
			}
			if(pstmt != null) {
				ResultSet resultSet = pstmt.executeQuery();
				System.out.println("execute");
				if(resultSet.next()) {
					//copy the result set data and transfer it .
					student = new Student();
					student.setId(resultSet.getInt(1));
					student.setAge(resultSet.getInt(2));
					student.setName(resultSet.getString(3));
					student.setAddr(resultSet.getString(4));	
				}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateById(Student student) {
		String sqlUpdateQuery = "update member_data set age=? ,  name=? , address=? where id = ?";
		PreparedStatement pstmt = null;
		String status = null;
		try {
				connection = JdbcUtil.getJdbcConnection();
				if(connection != null) pstmt = connection.prepareStatement(sqlUpdateQuery);
				if(pstmt != null) {
					pstmt.setInt(1, student.getAge());
					pstmt.setString(2, student.getName());
					pstmt.setString(3, student.getAddr());
					pstmt.setInt(4, student.getId());
					
				}
				if(pstmt != null) {
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected == 1) status = "SUCCESS";
					else status = "FAIL";
				}
			}
			catch(SQLException | IOException e) {
				e.printStackTrace();
				status = "FAIL";
			}
			return status;
	}

	@Override
	public String deleteById(Integer sid) {
		String sqlDeleteQuery = "delete from member_data where id = ?";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			Student student = findById(sid);
			if(student != null) 
			{
				connection = JdbcUtil.getJdbcConnection();
				if(connection != null) pstmt = connection.prepareStatement(sqlDeleteQuery);
				if(pstmt != null) pstmt.setInt(1, sid);
				if(pstmt != null) {
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected == 1) status = "SUCCESS";
					else status = "FAIL";
				}
			}
			else status = "RECORD NOT AVAILABLE";
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}
