package DingoBetBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DingoBetBackend.models.room;
import DingoBetBackend.repositories.roomRepository;

import java.util.List;

@Service
public class roomService {

    private final roomRepository roomRepository;

    @Autowired
    public roomService(roomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public room saveRoom(room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public List<room> getAllRooms() {
        return roomRepository.findAll();
    }
}
