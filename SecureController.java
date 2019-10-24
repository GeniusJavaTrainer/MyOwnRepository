package com.cognizant.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dao.AddressDao;
import com.cognizant.dao.BookDao;
import com.cognizant.dao.DepartmentDao;
import com.cognizant.dao.LibraryDao;
import com.cognizant.dao.StudentDao;
import com.cognizant.model.Department;
import com.cognizant.model.Employee;
import com.cognizant.model.Library;
import com.cognizant.model.Student;

@RestController
@RequestMapping("/secure")
public class SecureController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private BookDao bookDao;

	@Autowired
	private DepartmentDao deptDao;
	
	@Autowired
	private LibraryDao libraryDao;
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Student save(@RequestBody Student student) {
		addressDao.save(student.getAddress());
		Student stu = studentDao.save(student);
		System.out.println(stu);
		return stu;
	}
	
	@RequestMapping(value = "/student/{rollno}", method = RequestMethod.GET)
	public Student findByRollNo(@PathVariable("rollno") Long rollno) {
		Student stu = studentDao.findByRollNo(rollno);
		System.out.println(stu);
		return stu;
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getStudents() {
		Iterator<Student>itrStudent = studentDao.findAll().iterator();
		
		List<Student>studentList = new ArrayList<>();
		
		while(itrStudent.hasNext()){
			studentList.add(itrStudent.next());
		}
		
		return studentList;
	}
	
	@RequestMapping(value = "/library", method = RequestMethod.POST)
	public Department saveLibrary() {
/*		Library library = new Library();
		library.setId(106);
		library.setName("Fun books library");
		
		Book book1 = new Book();
		book1.setId(123);
		book1.setTitle("Swarg lok ke majedar kisse");
		book1.setLibrary(library);
		
		Book book2 = new Book();
		book2.setId(124);
		book2.setTitle("Affair details of Indra & Urvashi");
		book2.setLibrary(library);
		
		List<Book>bookSet = new ArrayList<>();
		bookSet.add(book1);
		bookSet.add(book2);
		
		library.setBooks(bookSet);
		
		libraryDao.save(library);
		
		return library;*/
		Employee employeeOne = new Employee();
		Employee employeeTwo = new Employee();
		Employee employeeThree = new Employee();
		
		Department department = new Department();
		
		department.setId(2);
		department.setName("Finance");
		
		employeeOne.setId(4);
		employeeOne.setName("Amey");
		employeeOne.setSalary(90000);
		employeeOne.setDepartment(department);
		
		employeeTwo.setId(5);
		employeeTwo.setName("Akshay");
		employeeTwo.setSalary(180000);
		employeeTwo.setDepartment(department);
		
		employeeThree.setId(6);
		employeeThree.setName("Amar");
		employeeThree.setSalary(270000);
		employeeThree.setDepartment(department);
		
		
		Set<Employee>employees = new HashSet<>();
		employees.add(employeeOne);
		employees.add(employeeTwo);
		employees.add(employeeThree);
		
		department.setEmployees(employees);
		deptDao.save(department);
		
		return department;
	}
	
	@RequestMapping(value = "/dept/{id}", method = RequestMethod.GET)
	public Department findById(@PathVariable("id") Integer id) {
		Department dept = deptDao.findById(id);
		
		return dept;
	}
}