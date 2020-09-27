package com.globallogic.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import com.globallogic.users.controller.UsersController;
import com.globallogic.users.model.UserMock;
import com.globallogic.users.service.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class GlobalLogicUsersControllerTests {

	
	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAdd() throws Exception {
		mockMvc
				.perform(
						post("/api/v1/")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(UserMock.getUserJson())
						
						)
						
				.andExpect(status().isOk());

	}

	@Test
	public void testGetById() throws Exception {
		
		mockMvc
				.perform(
						get("/api/v1/{id}", 1)
						.accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void testGetAll() throws Exception {
		
		mockMvc
				.perform(
						get("/api/v1/")
						.accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void testDelete() throws Exception {
		mockMvc
				.perform(
						delete("/api/v1/{id}", 1)
						.accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void testModifyEmail() throws Exception {
		
		UserService service = Mockito.mock(UserService.class);
		
		Mockito.when(service.update(Mockito.any(), Mockito.anyInt())).thenReturn(UserMock.getUserEntity());
		
		mockMvc
		.perform(
				put("/api/v1/{id}", 1)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(UserMock.getUserJson())
				
				)
				
		.andExpect(status().isNotFound());

	}

}
