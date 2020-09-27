package com.globallogic.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.globallogic.users.entity.PhoneEntity;
import com.globallogic.users.entity.UserEntity;
import com.globallogic.users.exception.GLogicException;
import com.globallogic.users.model.User;
import com.globallogic.users.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository repository;

	public Object add(User user) throws Exception {

		Optional<UserEntity> userEmail = repository.findByEmail(user.getEmail());
		if (userEmail.isPresent()) {
			throw new GLogicException(HttpStatus.FOUND, "El correo ya registrado");
		}
		log.info("SAVING USER");
		List<PhoneEntity> phones = new ArrayList<PhoneEntity>();
		if (user.getPhones() != null) {
			phones = user.getPhones().stream().map(e -> {
				return PhoneEntity.builder().number(e.getNumber()).cityCode(e.getCityCode())
						.countryCode(e.getCountryCode()).build();
			}).collect(Collectors.toList());

		}

		UserEntity entity = UserEntity.builder().name(user.getName()).email(user.getEmail())
				.password(user.getPassword()).phones(phones).build();

		return repository.save(entity);
	}

	public Optional<UserEntity> get(Integer id) {
		log.info("FINDING USER BY ID ");
		return repository.findById(id);
	}

	public List<UserEntity> getAll() {
		log.info("FINDING ALL USERS");
		return repository.findAll();
	}

	public Optional<UserEntity> update(User user, Integer id) {
		log.info("UPDATING USER BY ID");
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
		log.info("FINDING USER BY ID ");
		Optional<UserEntity> opUser = repository.findById(id);
		if (opUser.isPresent()) {
			log.info("DELETING USER BY ID ");
			repository.deleteById(id);
			return true;
		}
		log.info("CAN'T NOT DELETE USER BY ID ");
		return false;
	}

}
