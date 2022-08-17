package com.lotusntp.training.backend;

import com.lotusntp.training.backend.entity.Address;
import com.lotusntp.training.backend.entity.Social;
import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.service.AddressService;
import com.lotusntp.training.backend.service.SocialService;
import com.lotusntp.training.backend.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

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
	@Order(9)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		// check social
		Social social = user.getSocial();

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook,social.getFacebook());

		// check  address
		List<Address> userAddresses = user.getAddresses();

		Assertions.assertFalse(userAddresses.isEmpty());
		Assertions.assertEquals(2,userAddresses.size());


		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	@Order(3)
	@Test
	void testCreateSocial() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		Social userSocial = user.getSocial();
		Assertions.assertNull(userSocial);
		Social social = socialService.create(user,
				SocialTestCreateData.facebook,
				SocialTestCreateData.line,
				SocialTestCreateData.line,
				SocialTestCreateData.tiktok);
		Assertions.assertNotNull(social);

		Assertions.assertEquals(SocialTestCreateData.facebook,social.getFacebook());
	}

	@Order(4)
	@Test
	void testCreateAddress() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		List<Address> userAddresses = user.getAddresses();
		Assertions.assertTrue(userAddresses.isEmpty());

		createAddress(user,
				AddressTestCreateData.line1,
				AddressTestCreateData.line2,
				AddressTestCreateData.zipcode);

		createAddress(user,
				AddressTestCreateData2.line1,
				AddressTestCreateData2.line2,
				AddressTestCreateData2.zipcode);
	}

	private void  createAddress(User user,String line1,String line2,String zipcode){
		Address address = addressService.create(user,
				line1,
				line2,
				zipcode);
		Assertions.assertNotNull(address);
		Assertions.assertEquals(line1,address.getLine1());
		Assertions.assertEquals(line2,address.getLine2());
		Assertions.assertEquals(zipcode,address.getZipcode());
	}

	interface  TestCreateData{
		String email = "lotus@gmaii.comm";
		String password = "13245";
		String name= "lotus";
	}

	interface SocialTestCreateData{
		String facebook = "lotus";
		String line = "lotus.lt";
		String instagram =  "lotus_lt";
		String tiktok = "lotus_tk";
	}

	interface AddressTestCreateData{
		String line1 = "123/45";
		String line2 = "m.2 str";
		String zipcode = "1212510";
	}

	interface AddressTestCreateData2{
		String line1 = "456/45";
		String line2 = "m.10 str";
		String zipcode = "1150";
	}

	interface  TestUpdateData{

		String name= "bigC";
	}

}
