package com.bjpowernode.dao;

import java.util.List;

import com.bjpowernode.beans.Student;

public interface IStudentDao {
	void insertStudent(Student student);
	void deleteStudentById(int id);
	void updateStudent(Student student);
	
	List<Student> selectAllStudents();
	Student selectStudentById(int id);
}
