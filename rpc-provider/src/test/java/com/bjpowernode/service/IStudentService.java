package com.bjpowernode.service;

import java.util.List;

import com.bjpowernode.beans.Student;

public interface IStudentService {
	void addStudent(Student student);
	void removeStudentById(int id);
	void modifyStudent(Student student);
	
	List<Student> findAllStudents();
	Student findStudentById(int id);
}
