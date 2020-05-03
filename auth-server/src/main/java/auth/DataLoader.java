package auth;

import auth.entity.User;
import auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserService userService;

    public void run(ApplicationArguments args) {
        User user = new User();
        user.setName("duy");
        user.setEmail("duy@gmail.com");
        user.setPassword("1");
        userService.create(user);
    }
}
