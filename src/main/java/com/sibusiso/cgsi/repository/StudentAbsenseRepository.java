package com.sibusiso.cgsi.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sibusiso.cgsi.model.StudentAbsense;
import com.sibusiso.cgsi.model.StudentAbsenseMapper;

@Repository
public class StudentAbsenseRepository {
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT_STUDENT_ABSENSE = "INSERT INTO student_absense(class_id, grade_id, date_time, full_name, is_present) "
																	+ "VALUES(?, ?, ?, ?, ?)";
	
	private final String SQL_DELETE_STUDENT_ABSENSE = "DELETE FROM student_absense WHERE student_absense.full_name = :fullName ";
	
	private final String SQL_GET_ALL = "SELECT student_absense.full_name, class.name AS class_name, grade.name AS grade_name, \r\n" + 
										"    student_absense.is_present, student_absense.date_time\r\n" + 
										"    FROM student_absense, class, grade WHERE student_absense.class_id = class.id AND\r\n" + 
										"       student_absense.grade_id = grade.id";
	
	private final String SQL_GET_BY_FULL_NAME = "SELECT student_absense.full_name, class.name AS class_name, grade.name AS grade_name, \r\n" + 
			"        student_absense.is_present, student_absense.date_time\r\n" + 
			"        FROM student_absense, class, grade WHERE student_absense.class_id = class.id AND\r\n" + 
			"                                                    student_absense.grade_id = grade.id AND\r\n" + 
			"                                                    student_absense.full_name = :fullName ";
	
	private final String SQL_GET_BY_GRADE_NAME = "SELECT student_absense.full_name, class.name AS class_name, grade.name AS grade_name, \r\n" + 
			"        student_absense.is_present, student_absense.date_time\r\n" + 
			"        FROM student_absense, class, grade WHERE student_absense.class_id = class.id AND\r\n" + 
			"                                                    student_absense.grade_id = grade.id AND\r\n" + 
			"                                                    grade.name = :gradeName ";

	private final String SQL_GET_BY_CLASS_NAME = "SELECT student_absense.full_name, class.name AS class_name, grade.name AS grade_name, \r\n" + 
			"        student_absense.is_present, student_absense.date_time\r\n" + 
			"        FROM student_absense, class, grade WHERE student_absense.class_id = class.id AND\r\n" + 
			"                                                    student_absense.grade_id = grade.id AND\r\n" + 
			"                                                    class.name = :className ";
	
	private final String SQL_GET_BY_DATE_TIME = "SELECT student_absense.full_name, class.name AS class_name, grade.name AS grade_name, \r\n" + 
			"        student_absense.is_present, student_absense.date_time\r\n" + 
			"        FROM student_absense, class, grade WHERE student_absense.class_id = class.id AND\r\n" + 
			"                                                    student_absense.grade_id = grade.id AND\r\n" + 
			"                                                    student_absense.date_time = :dateTime ";

	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<StudentAbsense> getAllStudentsAbsenses() {
		return jdbcTemplate.query(SQL_GET_ALL, new StudentAbsenseMapper());
	}
	
	public boolean addStudentAbsense(StudentAbsense studentAbsense) {
		String classNamePk = jdbcTemplate.queryForObject("SELECT id FROM class WHERE class.name = :className",
													new Object[] { studentAbsense.getClassName() },
													String.class);
		String gradeNamePk = jdbcTemplate.queryForObject("SELECT id FROM grade WHERE grade.name = :gradeName",
				new Object[] { studentAbsense.getGradeName() },
				String.class);
		
		return jdbcTemplate.update(SQL_INSERT_STUDENT_ABSENSE,
				classNamePk,
				gradeNamePk,
				studentAbsense.getFullName(),
				java.sql.Date.valueOf(studentAbsense.getDateTime()),
				studentAbsense.getIsPresent()) > 0;
	}
	
	public boolean deleteStudentAbsense(String fullName) {
		return jdbcTemplate.update(SQL_DELETE_STUDENT_ABSENSE,
				 fullName) > 0;
	}
	
	public List<StudentAbsense> getAllStudentsAbsensesByFullName(String fullName) {
		return jdbcTemplate.query(SQL_GET_BY_FULL_NAME, 
				new Object[] { fullName },
				new StudentAbsenseMapper());
	}
	
	public List<StudentAbsense> getAllStudentsAbsensesByClassName(String className) {
		return jdbcTemplate.query(SQL_GET_BY_CLASS_NAME,
				new Object[] { className },
				new StudentAbsenseMapper());
	}
	
	public List<StudentAbsense> getAllStudentsAbsensesByGradeName(String gradeName) {
		return jdbcTemplate.query(SQL_GET_BY_GRADE_NAME, 
				new Object[] { gradeName },
				new StudentAbsenseMapper());
	}
	
	public List<StudentAbsense> getAllStudentsAbsensesByDateTime(java.sql.Date dateTime) {
		return jdbcTemplate.query(SQL_GET_BY_DATE_TIME, 
				new Object[] { dateTime },
				new StudentAbsenseMapper());
	}
}
