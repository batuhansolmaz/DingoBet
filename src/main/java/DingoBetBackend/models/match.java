package DingoBetBackend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class match {
    @Id
    @Column(name = "match_id")
    private Long id;

    @Column(name = "match_home_team")
    private String homeTeam;

    @Column(name = "match_away_team")
    private String awayTeam;

    @Column(name = "match_home_score")
    private Integer homeScore;

    @Column(name = "match_away_score")
    private Integer awayScore;

    @OneToOne(mappedBy = "match")
    private DingoBetBackend.models.bet bet;

}
