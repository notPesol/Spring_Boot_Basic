package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testEmailPattern() {
		String email = "example@exmail.com";
		String emailPattern = Util.emailPattern;
		assert(Util.patternMatches(emailPattern, email));
	}

	@Test
	void testDateFormat() {
		String date = "2020-12-25";
		assert(Util.validateDateFormat(date));
	}

}
