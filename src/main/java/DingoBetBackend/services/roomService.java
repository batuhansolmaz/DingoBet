package DingoBetBackend.services;

import DingoBetBackend.models.bet;
import DingoBetBackend.models.score;
import DingoBetBackend.models.user;
import com.fasterxml.jackson.databind.type.LogicalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DingoBetBackend.models.room;
import DingoBetBackend.repositories.roomRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

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
    public boolean isUserInRoom(user user, room room) {
        return room.getUsers().contains(user);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public List<room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Set<bet> getBetsInRoom(Long roomId) {
        room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            return room.getBets();
        }
        return null;
    }

    public List<score> getScoresInRoom(Long roomId) {
        room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            return room.getScores().stream()
                    .flatMap(score -> score.getUsersScoresInRoom(roomId).stream()) // Call instance method on each score
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}

