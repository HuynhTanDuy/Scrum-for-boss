package spring.phase2.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.phase2.DTO.UserDTO;
import spring.phase2.entity.User;
import spring.phase2.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	private User user1;
	private User user2;
	private List<User> users;
	
	@Before
	public void setUp() {
		user1 = new User();
		user1.setId(1);
		user1.setName("Duy");
		user1.setEmail("duy@gmail.com");
		user1.setPassword("1");
		
		user2 = new User();
		user1.setId(2);
		user2.setName("Nhut");
		user2.setEmail("Nhut@gmail.com");
		user2.setPassword("2");
		
		users = new ArrayList();
		users.add(user1);
		users.add(user2);
	}
	
	
	@Test
	public void testGetAll() {
		Mockito.when(userRepository.findAll()).thenReturn(users) ;
		List<UserDTO> result = (List<UserDTO>) userService.getAll();
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testGetById() {
		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user1));
		Optional<User> result = userService.getById(2);
		assertNotNull(result);
		assertEquals(result.get(), user1);
	}    

	@Test
	public void testCreate() {
		Mockito.when(userRepository.save(user1)).thenReturn(user1);
		User result = userService.create(user1);
		assertNotNull(result);
		assertEquals(result, user1);
	}

	@Test
	public void testUpdate() {
		Mockito.when(userRepository.save(user1)).thenReturn(user1);
		User result = userService.update(user1, user1.getId());
		assertNotNull(result);
		assertEquals(result, user1);
	}
}
