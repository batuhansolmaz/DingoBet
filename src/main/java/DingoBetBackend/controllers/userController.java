package DingoBetBackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import DingoBetBackend.services.userService;
import DingoBetBackend.models.user;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "redirect:/rooms";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody user user) {

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
