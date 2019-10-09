package com.bvk.studentapp.test;

import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TestStudentRESTController {
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testGetStudentList() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .get("/studentlists")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)));
	}
	
	@Test
	public void testGetStudent() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .get("/studentlist/{id}",1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	/*@Test
	public void testAddStudentList() throws Exception
	{
	  mockMvc.perform(MockMvcRequestBuilders
			  .post("/studentlist")
		      .content(asJsonString(new Student(3, "Mad Coder")))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	
	@Test
	public void testeditStudent() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
		      .put("/studentlist",asJsonString(new Student(2, "Babdu")))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
		      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Babdu"));
	}*/
	
	@Test
	public void testDeleteStudentList() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .delete("/studentlist/{id}",1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
