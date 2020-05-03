package spring.phase2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	private spring.phase2.entity.User applicationUser = new spring.phase2.entity.User();
	
	@Autowired
	private UserService userService;
	
    public JwtUserDetailsService() {
    }
    
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        applicationUser = userService.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getName(), applicationUser.getPassword(), emptyList());
    }
}
