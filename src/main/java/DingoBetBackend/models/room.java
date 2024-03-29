package DingoBetBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name")
    private String name;

    @ManyToMany(mappedBy = "adminRooms")
    private Set<user> adminUsers = new HashSet<>();

    @ManyToMany(mappedBy = "rooms")
    private Set<user> users = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<bet> bets = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<score> scores = new HashSet<>();

}