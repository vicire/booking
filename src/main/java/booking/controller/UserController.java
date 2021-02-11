package booking.controller;

import booking.dto.UserResponseDto;
import booking.model.User;
import booking.service.UserMapper;
import booking.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String injectUser() {
        User[] users = new User[4];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User();
            users[i].setName("Childish");
            users[i].setSurname("Gambino" + i);
            users[i].setEmail("dto@gmail.com");
            userService.add(users[i]);
        }
        return "This is America";
    }

    @GetMapping("/{user_id}")
    public UserResponseDto getUser(@PathVariable("user_id") Long id) {
        return userMapper.toDto(userService.get(id));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
