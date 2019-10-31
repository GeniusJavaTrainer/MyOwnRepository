package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CountryRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.dao.StateRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner{
	@Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CartRepository cartRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Collection<Student> studentList = studentRepository.findAllStudents();
		
		for (Student student : studentList) {
			System.out.println(student);
		}
		
		studentList = studentRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
		
		for (Student student : studentList) {
			System.out.println(student);
		}
		System.out.println("Using page:");
		Page<Student>studentPage = studentRepository.findAll(PageRequest.of(0, 3));
		
		studentPage.get().forEach(System.out::println);
		
		System.out.println("Using page native:");
		studentPage = studentRepository.findAll(PageRequest.of(0, 2));
		
		studentPage.get().forEach(System.out::println);
		
		System.out.println("Using JPQL:");
		Student student = studentRepository.findStudentByNameAndPercent("bvk", 100.00f);
		System.out.println(student);
		
		System.out.println("Using JPQL Native:");
		student = studentRepository.findStudentByNameNative("bvk");
		System.out.println(student);
		
		System.out.println("Using Named Parameters:");
		student = studentRepository.findStudentByNameAndPercentNamed("bvk", 100.00f);
		System.out.println(student);
		
		System.out.println("Using Named Parameters Native:");
		student = studentRepository.findStudentByNameAndPercentNamedNative("svk", 99.00f);
		System.out.println(student);
		
		List<String>names = new ArrayList<>();
		names.add("bvk");
		names.add("svk");
		
		System.out.println("Using Named Collection:");
		studentList = studentRepository.findStudentByNameList(names);
		
		for (Student stu : studentList) {
			System.out.println(stu);
		}
		
		int status = studentRepository.updateStudentSetPercentForName("bvk",99.00f);
		
		if(status == 1){
			System.out.println("Record updated.");
		}else{
			System.out.println("Record couldn't be updated.");
		}
		
		status = studentRepository.updateStudentSetPercentForNameNative("bvk",100.00f);
		
		if(status == 1){
			System.out.println("Record updated using native query.");
		}else{
			System.out.println("Record couldn't be updated.");
		}
		
		status = studentRepository.insertStudent("Babdu",89.00f);
		
		if(status == 1){
			System.out.println("Record inserted using native query.");
		}else{
			System.out.println("Record couldn't be inserted.");
		}
/*		Country country = new Country("India","NewDelhi","Rupees");

        State state1  = new State("Hariyana");
        state1.setCountry(country);
        State state2  = new State("Bihar");
        state2.setCountry(country);
        country.getStatt().add(state1);
        country.getStatt().add(state2);

        countryRepository.save(country);
		System.out.println("One to Many works...");*/
		/*Cart cart = new Cart(1L);

        // Create two Items
        Item item1 = new Item("As It is",2000L);
        Item item2 = new Item("BCAA",1200L);


        // Add item references in the cart
        cart.getItems().add(item1);
        cart.getItems().add(item2);

        // Add cart reference in the Item
        item1.getCarts().add(cart);
        item2.getCarts().add(cart);

        cartRepository.save(cart);*/
		
	}
}