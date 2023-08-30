
package DingoBetBackend.models;

import jakarta.persistence.*;

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

    private int score;

    // Other attributes, getters, setters
}