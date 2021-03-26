package com.sibusiso.cgsi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sibusiso.cgsi.model.StudentAbsense;
import com.sibusiso.cgsi.model.Teacher;
import com.sibusiso.cgsi.repository.StudentAbsenseRepository;

@CrossOrigin()
@RestController
@RequestMapping({ "/studentsabsenses" })
public class StudentAbsenseManagementController {
	private List<StudentAbsense> studentsAbsenses = createList();
	
	@Autowired
	private StudentAbsenseRepository studentAbsenseRepository;

	@GetMapping(produces = "application/json")
	public List<StudentAbsense> firstPage() {
		//this.studentsAbsenses = studentAbsenseRepository.getAllStudentsAbsenses();
		return studentsAbsenses;
	}
	
	@PostMapping(path = { "delete" })
	public StudentAbsense delete(@RequestBody StudentAbsense studentAbsenseToBeDeleted) {
		for (StudentAbsense studentAbsense : studentsAbsenses) {
			if (studentAbsense.getFullName().equals(studentAbsenseToBeDeleted.getFullName())
					 && studentAbsenseRepository.
					 			deleteStudentAbsense(studentAbsenseToBeDeleted.getFullName())) {
				studentAbsenseToBeDeleted = studentAbsense;
				studentsAbsenses.remove(studentAbsenseToBeDeleted);
				return studentAbsense;
			}
		}
		return studentAbsenseToBeDeleted;
	}
	
	@PostMapping(path = { "search/fullname" })
	public List<StudentAbsense> getAllStudentsAbsensesByFullName(@RequestParam String fullName) {
		this.studentsAbsenses = studentAbsenseRepository.getAllStudentsAbsensesByFullName(fullName);
		return this.studentsAbsenses;
	}
	
	@PostMapping(path = { "search/classname" })
	public List<StudentAbsense> getAllStudentsAbsensesByClassName(@RequestParam String className) {
		this.studentsAbsenses = studentAbsenseRepository.getAllStudentsAbsensesByClassName(className);
		return this.studentsAbsenses;
	}
	
	@PostMapping(path = { "search/gradename" })
	public List<StudentAbsense> getAllStudentsAbsensesByGradeName(@RequestParam String gradeName) {
		this.studentsAbsenses = studentAbsenseRepository.getAllStudentsAbsensesByGradeName(gradeName);
		return this.studentsAbsenses;
	}
	
	@PostMapping(path = { "search/datetime" })
	public List<StudentAbsense> getAllStudentsAbsensesByDateTime(@RequestParam String dateTime) {
		this.studentsAbsenses = studentAbsenseRepository.getAllStudentsAbsensesByDateTime(java.sql.Date.valueOf(dateTime));
		return this.studentsAbsenses;
	}

	@PostMapping(path = { "add" })
	public StudentAbsense create(@RequestBody StudentAbsense studentAbsenseToBeAdded) {
		if(studentAbsenseRepository.addStudentAbsense(studentAbsenseToBeAdded)) 
			studentsAbsenses.add(studentAbsenseToBeAdded);
		
		return studentAbsenseToBeAdded;
	}

	private static List<StudentAbsense> createList() {
		List<StudentAbsense> studentsAbsense = new ArrayList<StudentAbsense>();
		
		StudentAbsense student1 = new StudentAbsense();
		student1.setFullName("student1");
		student1.setIsPresent(false);

		StudentAbsense student2 = new StudentAbsense();
		student2.setFullName("student2");
		student2.setIsPresent(true);

		studentsAbsense.add(student1);
		studentsAbsense.add(student2);
		return studentsAbsense;
	}
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Teacher validateLogin() {
		return new Teacher("Teacher successfully logged in.");
	}

}
