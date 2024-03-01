package com.springjdbc.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springjdbc.springjdbc.model.Student;

public class StudentDAO {
	
	
	JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addStudent(Student student) {
		String query = "insert into student(student_id,student_name,student_city) values(?,?,?)";
		this.jdbcTemplate.update(query,student.getId(),student.getstudentName(),student.getstudentCity());
		System.out.println("Student Added!");
	}
	
	public void updateStudent(Student student) {
		String query = "update student set student_name=?,student_city=? where student_id=?";
		this.jdbcTemplate.update(query,student.getstudentName(),student.getstudentCity(),student.getId());
		System.out.println("Student Updated!");
	}
	
	public void deleteStudent(Integer studentId) {
		String query = "delete from student where student_id=?";
		this.jdbcTemplate.update(query,studentId);
		System.out.println("Student Deleted!");
	}
	
	public Student getStudent(Integer studentId) {
		
		String query = "select * from student where student_id=?";
		Student student =  this.jdbcTemplate.queryForObject(query, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setstudentName(rs.getString(2));
				student.setstudentCity(rs.getString(3));
				return student;
			}
			
		}, studentId);
		
		return student;
	}
	
	
	public List<Student> getAllStudents() {
		String query = "select * from student";
		
		List<Student> list = this.jdbcTemplate.query(query, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setstudentName(rs.getString(2));
				student.setstudentCity(rs.getString(3));
				return student;
			
			}
			
		});
		
		return list;
		
	}

	
	
}
