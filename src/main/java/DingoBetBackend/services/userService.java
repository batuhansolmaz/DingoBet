package DingoBetBackend.services;

import DingoBetBackend.models.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DingoBetBackend.models.user;
import DingoBetBackend.repositories.userRepository;

import java.util.List;

@Service
public class userService {

    private final userRepository userRepository;
    @Autowired private roomService roomService;

    @Autowired
    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public user getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public user saveUser(user user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    public user getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
