package com.globallogic.users.model;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.users.entity.UserEntity;

/**
 * 
 * @author s2526158
 *
 */
public class UserMock {
	/**
	 * 
	 * @return
	 */
	public static User getUser() {
		User user = new User();
		user.setEmail("no@no.cl");
		user.setName("Cesar");
		user.setPassword("Abc123456");

		Phone phone = new Phone();
		phone.setCityCode(1);
		phone.setCountryCode(123);
		phone.setNumber(312312312);

		user.setPhones(Arrays.asList(phone));

		return user;
	}
	
	/**
	 * 
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String getUserJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setEmail("no@no.cl");
		user.setName("Cesar");
		user.setPassword("Abc123456");

		Phone phone = new Phone();
		phone.setCityCode(1);
		phone.setCountryCode(123);
		phone.setNumber(312312312);

		user.setPhones(Arrays.asList(phone));

		return mapper.writeValueAsString(user);
	}

	/**
	 * 
	 * @return
	 */
	public static Optional<UserEntity> getUserEntity() {
		return Optional.of(UserEntity.builder().email("no@no.cl").name("Cesar").password("password").id(1).build());
	}

	/**
	 * 
	 * @return
	 */
	public static Optional<UserEntity> getUserEntity2() {
		return Optional.of(UserEntity.builder().email("si@si.cl").name("Cesar").password("password").id(1).build());
	}

}
