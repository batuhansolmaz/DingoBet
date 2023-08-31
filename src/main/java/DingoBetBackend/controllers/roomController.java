
package DingoBetBackend.controllers;
import DingoBetBackend.models.bet;
import DingoBetBackend.models.room;
import DingoBetBackend.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import DingoBetBackend.services.roomService;
import DingoBetBackend.repositories.roomRepository;
import DingoBetBackend.repositories.userRepository;
import DingoBetBackend.services.betService;
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
    public room createRoomWithAdmin(String roomName, Long adminUserId) {
        room newRoom = new room();
        newRoom.setName(roomName);

        user adminUser = userRepository.findById(adminUserId).orElse(null);
        if (adminUser != null) {
            newRoom.getAdminUsers().add(adminUser);
            return roomRepository.save(newRoom);
        } else {
            throw new IllegalArgumentException("Admin user not found");
        }
    }

    //TODO add authentications
    @PostMapping("/join/{roomId}")
    public String joinRoomForm(@PathVariable Long roomId, @ModelAttribute user user) {
        room room = roomService.getRoomById(roomId);
        if (room != null) {
            room.getUsers().add(user); // Add the user to the room's user list
            roomService.saveRoom(room);
        }
        return "redirect:/rooms";
    }
    @GetMapping("/getBets/{roomId}")
    public ResponseEntity<Set<bet>> getAllBetsInRoom(@PathVariable Long roomId) {
        room room = roomService.getRoomById(roomId);

        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        Set<bet> betsInRoom = roomService.getBetsInRoom(roomId);
        return ResponseEntity.ok(betsInRoom);
    }
}