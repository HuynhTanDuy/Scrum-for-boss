package spring.phase2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import spring.phase2.entity.User;
import spring.phase2.repository.OfficeRepository;
import spring.phase2.repository.RoleRepository;
import spring.phase2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.phase2.DTO.UserDTO;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfficeRepository officeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private final static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private final static ModelMapper modelMapper = new ModelMapper();
	
	public List<UserDTO> getAll() {
		Iterable<User> userEntity = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList();
		for (User user : userEntity) {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			
			String office = officeRepository.findById(user.getOffice_id()).get().getName();
			userDTO.setOffice(office);
			
			String role = roleRepository.findById(user.getRole_id()).get().getTitle();
			userDTO.setRole(role);
			
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}
	
	public Optional<User> getById(int id) {
		return userRepository.findById(id);
	}
	
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(User new_user, Integer id) {
		return userRepository.findById(id)
	        .map(user -> {
	    	    new_user.setId(user.getId());
	    	    return userRepository.save(new_user);
	        })
	        .orElseGet(() -> {
	    	    new_user.setId(id);
	    	    return userRepository.save(new_user);
	        });
	}
		
	public void deleteById(Integer id) {
	    userRepository.deleteById(id);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
