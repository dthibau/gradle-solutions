package org.formation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.formation.MVCRestApplication;
import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest(classes=MVCRestApplication.class, webEnvironment=WebEnvironment.NONE)
public class UserDetailServiceTest {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@MockBean
	private MemberRepository mockRepository;
	
	@Test
	public void goodLogin() {
		given(mockRepository.findByEmail("dthibau@wmmod.com")).willReturn(new Member().email("dthibau@wmmod.com").password("secret"));
		assertThat(userDetailsService.loadUserByUsername("dthibau@wmmod.com") != null);
	}
}
