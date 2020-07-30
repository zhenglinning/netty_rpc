package com.bjpowernode.service;

import java.util.List;

import com.bjpowernode.beans.Student;
import com.bjpowernode.dao.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao dao;
	
	public void setDao(IStudentDao dao) {
		this.dao = dao;
	}

	@Override
	public void addStudent(Student student) {
		dao.insertStudent(student);
	}

	@Override
	public void removeStudentById(int id) {
		dao.deleteStudentById(id);
	}

	@Override
	public void modifyStudent(Student student) {
		dao.updateStudent(student);
	}

	@Override
	public List<Student> findAllStudents() {
		return dao.selectAllStudents();
	}

	@Override
	public Student findStudentById(int id) {
		return dao.selectStudentById(id);
	}

}
