package com.frotas.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class FirstProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Authentication authentication = new TestingAuthenticationToken(
				"diogofarias.tivit@bunge.com",
				"123");
		context.setAuthentication(authentication);

		SecurityContextHolder.setContext(context);
	}

	@Test
	void addNewUserTest() throws Exception {
//		mockMvc.perform(post("/user/add")
//				.contentType("application/json"))
//				.andExpect(status().isOk());
	}

}
