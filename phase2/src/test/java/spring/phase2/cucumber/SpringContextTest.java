package spring.phase2.cucumber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.phase2.Phase2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Phase2Application.class)
public class SpringContextTest {
	@Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
