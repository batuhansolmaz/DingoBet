
package DingoBetBackend.controllers;
import DingoBetBackend.models.bet;
import DingoBetBackend.models.room;
import DingoBetBackend.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import DingoBetBackend.services.roomService;
import DingoBetBackend.repositories.roomRepository;
import DingoBetBackend.repositories.userRepository;
import DingoBetBackend.services.betService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/room")
public class roomController {
    @Autowired
    private roomService roomService;
    @Autowired
    private roomRepository roomRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private betService betService;

    //TODO add authentications
    @PostMapping("/create")
    public ResponseEntity<String> createRoomWithAdmin(@RequestParam String roomName, @RequestParam Long adminUserId) {
        user adminUser = userRepository.findById(adminUserId).orElse(null);
        if (adminUser == null) {
            return ResponseEntity.badRequest().body("Admin user not found");
        }
        // Create a new room
        room newRoom = new room();
        newRoom.setName(roomName);

        // Associate the admin user with the room's adminUsers set
        newRoom.getAdminUsers().add(adminUser);
        newRoom.getUsers().add(adminUser);

        // Add the room to the admin user's adminRooms set
        adminUser.getAdminRooms().add(newRoom);
        adminUser.getRooms().add(newRoom);

        // Save the room and the admin user
        room savedRoom = roomRepository.save(newRoom);
        userRepository.save(adminUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(savedRoom));
    }

    //TODO add authentications
    @PostMapping("/join/{roomId}")
    public String joinRoom(@PathVariable Long roomId, @RequestBody user user, RedirectAttributes redirectAttributes) {
        room room = roomService.getRoomById(roomId);
        System.out.println("room: " + room);
        if (room != null) {
            System.out.println("user: " + user);
            user newuser = userRepository.findById(user.getId()).orElse(null);
            System.out.println("newuser: " + newuser);
            if (newuser == null) {
                System.out.println("User not found.");
                redirectAttributes.addFlashAttribute("error", "User not found.");
                return "redirect:/rooms";
            }
            System.out.println("user213: " + newuser);
            room.getUsers().add(newuser); // Add the user to the room's user list
            newuser.getRooms().add(room); // Add the room to the user's room list
            roomService.saveRoom(room);
            userRepository.save(newuser);
            redirectAttributes.addFlashAttribute("message", "You have successfully joined the room.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Room not found.");
        }
        return "redirect:/rooms";
    }
}