
package DingoBetBackend.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private room room;

    private long score;

    private room getRoom() {
        return room;
    }
    private user getUser() {
        return user;
    }
    public List<score> getUsersScoresInRoom(Long roomId) {
        return room.getScores().stream()
                .filter(score -> score.getRoom().getId().equals(roomId))
                .collect(Collectors.toList());
    }

    public List<score> getUserScores(Long userId, Long roomId) {
        return room.getScores().stream()
                .filter(score -> score.getRoom().getId().equals(roomId) && score.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }


}