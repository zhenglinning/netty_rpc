package com.bjpowernode.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjpowernode.beans.Student;
import com.bjpowernode.service.IStudentService;

public class MyTest {
	private IStudentService service;
	
	@Before
	public void before() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object iStudentDao = ac.getBean("studentDao");

		service = (IStudentService) ac.getBean("studentService");
	}

	@Test
	public void test01() {
		Student student = new Student("张三", 23, 93.5);
		service.addStudent(student);
	}
	
	@Test
	public void test02() {
		service.removeStudentById(2);
	}
	
	@Test
	public void test03() {
		Student student = new Student("李四", 24, 94.5);
		student.setId(5);
		service.modifyStudent(student);
	}
	
	@Test
	public void test06() {
		List<Student> students = service.findAllStudents();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void test07() {
		Student student = service.findStudentById(60);
		System.out.println(student);
	}
}






















