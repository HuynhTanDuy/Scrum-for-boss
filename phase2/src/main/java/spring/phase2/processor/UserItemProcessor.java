package spring.phase2.processor;

import org.springframework.batch.item.ItemProcessor;

import spring.phase2.entity.User;

public class UserItemProcessor implements ItemProcessor<User, User> {
	@Override
	public User process(User user) throws Exception {
	  return user;
	}
}
