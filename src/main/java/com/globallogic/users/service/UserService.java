package com.globallogic.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.users.entity.PhoneEntity;
import com.globallogic.users.entity.UserEntity;
import com.globallogic.users.model.User;
import com.globallogic.users.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public Object add(User user) {
		
		List<PhoneEntity> phones = new ArrayList<PhoneEntity>();
		if(user.getPhones() != null) {
			phones = user.getPhones().stream().map(e -> {
				return PhoneEntity.builder()
							.number(e.getNumber())
							.cityCode(e.getCityCode())
							.countryCode(e.getCountryCode())
							.build();
			}).collect(Collectors.toList());
			
		}

		UserEntity entity = UserEntity.builder()
									.name(user.getName())
									.email(user.getEmail())
									.password(user.getPassword())
									.phones(phones)
									.build();

		return repository.save(entity);
	}

	public Optional<UserEntity> get(Integer id) {
		return repository.findById(id);
	}

	public List<UserEntity> getAll() {
		return repository.findAll();
	}

	public Optional<UserEntity> update(User user, Integer id) {

		Optional<UserEntity> opUser = repository.findById(id);

		if (!opUser.isPresent()) {
			return Optional.empty();
		}

		UserEntity entity = opUser.get();
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		List<PhoneEntity> phoneEntities = new ArrayList<PhoneEntity>();
		if (user.getPhones() != null) {
			phoneEntities = user
					.getPhones().stream().map(e -> PhoneEntity.builder().cityCode(e.getCityCode())
							.countryCode(e.getCountryCode()).number(e.getNumber()).build())
					.collect(Collectors.toList());
			
		}
		entity.setPhones(phoneEntities);

		return Optional.of(repository.save(entity));

	}

	public boolean delete(Integer id) {
		Optional<UserEntity> opUser = repository.findById(id);
		if (opUser.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
