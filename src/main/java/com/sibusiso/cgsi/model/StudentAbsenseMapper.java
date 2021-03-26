package com.sibusiso.cgsi.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.jdbc.core.RowMapper;

public class StudentAbsenseMapper implements RowMapper<StudentAbsense> {

	@Override
	public StudentAbsense mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return  new StudentAbsense(resultSet.getString("FULL_NAME"),
				resultSet.getString("CLASS_NAME"),
				resultSet.getString("GRADE_NAME"),
				resultSet.getBoolean("IS_PRESENT"),
				DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).
				format(resultSet.getDate("DATE_TIME").toLocalDate()).toString());
	}
}
