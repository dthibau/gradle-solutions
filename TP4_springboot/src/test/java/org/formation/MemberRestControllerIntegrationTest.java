package org.formation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MemberRestControllerIntegrationTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	Member firstMember;
    Document doc1;
    Document doc2;
    long id;
    
    
    
	@BeforeEach
    public void setUp() {

		firstMember = new Member();
		firstMember.setEmail("toto@gmail.com");
		firstMember.setPassword("secret");
		firstMember.setAge(18);

 
    }
	
	@Test
	@WithMockUser
    public void canRegister() throws JsonProcessingException {
	
		final ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(firstMember),headers);
			
 		ResponseEntity<Member> responseEntity =
	            restTemplate.postForEntity("/register", requestEntity, Member.class);
	    System.out.println(responseEntity);    
 		Member member = responseEntity.getBody();
	        
    }
	
	@Test
	@WithUserDetails("dthibau@wmmod.com")
    public void getMember() throws JsonProcessingException {
	
			
 		ResponseEntity<Member> responseEntity =
	            restTemplate.getForEntity("/Members/1", Member.class);
	    System.out.println(responseEntity);    
 		Member member = responseEntity.getBody();
	        

    }
}
