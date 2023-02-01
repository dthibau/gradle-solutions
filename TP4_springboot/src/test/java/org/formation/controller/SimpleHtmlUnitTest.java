package org.formation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//@WebMvcTest(secure=false)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleHtmlUnitTest {

	@Autowired
	ApplicationContext context;
	

	// WebClient is auto-configured thanks to HtmlUnit
	@Autowired
	private WebClient webClient;
	
	@Autowired
    private MockMvc mvc;

	@BeforeEach
    public void setup() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
	@Test
	@WithMockUser
	public void testExample() throws Exception {

		HtmlPage page = this.webClient.getPage("/");
		assertThat(page.getBody().getTextContent()).contains("Bienvenue");
	}
	
	@Test
	@WithMockUser
	public void mockMvcTest() throws Exception {
		
		mvc.perform(get("/").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

}
