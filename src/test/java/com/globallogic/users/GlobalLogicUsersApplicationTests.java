package com.globallogic.users;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spock.lang.Specification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GlobalLogicUsersApplicationTests extends Specification {

	@Autowired (required = false)
	    private WebController webController

	def"when context is loaded then all expected beans are created"()
	{
	        expect: "the WebController is created"
	        webController
	    }

}
