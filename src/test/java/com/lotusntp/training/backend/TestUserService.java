package com.lotusntp.training.backend;

import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);

		// check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// check equals
		Assertions.assertEquals(TestCreateData.email,user.getEmail());
		boolean isMatched =  userService.matchPassword(TestCreateData.password,user.getPassword());
		Assertions.assertTrue(isMatched);
		Assertions.assertEquals(TestCreateData.name,user.getName());
	}
	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updateName = userService.updateName(user.getId(), TestUpdateData.name);
		Assertions.assertNotNull(updateName);
		Assertions.assertEquals(TestUpdateData.name,updateName.getName());

	}
	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface  TestCreateData{
		String email = "lotus@gmaii.comm";
		String password = "13245";
		String name= "lotus";
	}

	interface  TestUpdateData{

		String name= "bigC";
	}

}
