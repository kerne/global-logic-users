package com.globallogic.users;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.globallogic.users.entity.UserEntity;
import com.globallogic.users.model.UserMock;
import com.globallogic.users.repository.UserRepository;
import com.globallogic.users.service.UserService;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class GlobalLogicUsersApplicationTests {

	@Mock
	UserRepository repository;

	@InjectMocks
	UserService service;

	@Test
	public void testAdd() {

		Mockito.when(repository.save(Mockito.any())).thenReturn(UserMock.getUserEntity().get());

		UserEntity entity = (UserEntity) service.add(UserMock.getUser());

		Assert.assertEquals(Integer.valueOf(1), entity.getId());

	}

	@Test
	public void testGet() {

		Mockito.when(repository.findById(Mockito.any())).thenReturn(UserMock.getUserEntity());

		Optional<UserEntity> entity = service.get(1);

		Assert.assertNotNull(entity.get());

	}

	@Test
	public void testDelete() {

		Mockito.when(repository.findById(Mockito.any())).thenReturn(UserMock.getUserEntity());

		Mockito.doNothing().when(Mockito.mock(UserRepository.class)).deleteById(Mockito.anyInt());

		Assert.assertTrue(service.delete(1));

	}

	@Test
	public void testModifyEmail() {

		Mockito.when(repository.findById(Mockito.any())).thenReturn(UserMock.getUserEntity());
		Mockito.when(repository.save(Mockito.any())).thenReturn(UserMock.getUserEntity2().get());

		Optional<UserEntity> entity = service.update(UserMock.getUser(), 1);

		Assert.assertNotEquals("no@no.cl", entity.get().getEmail());

	}

}
