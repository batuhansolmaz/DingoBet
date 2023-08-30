package DingoBetBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.List;


@Getter
@Setter
@Entity
public class user {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @ManyToMany(mappedBy = "users")
    private List<room> rooms;

    @OneToMany(mappedBy = "user")
    private List<bet> bets;

    @OneToMany(mappedBy = "user")
    private List<score> scores;

}
