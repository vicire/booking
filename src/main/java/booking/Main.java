package booking;

import booking.config.AppConfig;
import booking.model.User;
import booking.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Bob");
        user.setSurname("Parker");
        user.setEmail("gggg@gmail.com");
        User user1 = new User();
        user1.setName("Alice");
        user1.setSurname("Adams");
        user1.setEmail("hhh@gmail.com");
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = appContext.getBean(UserService.class);
        userService.add(user);
        userService.add(user1);
        userService.listUsers().forEach(System.out::println);
    }
}
