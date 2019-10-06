package pl.sda.common.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/users")
public class MvcUserController {

    private UserRepository userRepository;

    public MvcUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users-form";
    }

    @PostMapping
    public String createUser(User user) {
        //  Mimo poprwanego działania saveAll zasabotuje testy przez mocka verify save(user)
        //  userRepository.saveAll(Arrays.asList(user));
        userRepository.save(user);
        return "redirect:/mvc/users";
    }
}
