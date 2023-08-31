package DingoBetBackend.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class bet {

    @Id
    @GeneratedValue
    @Column(name = "bet_id")
    private Long id;

    @Column(name = "bet_score")
    private Integer betScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private room room;

    @OneToOne
    @JoinColumn(name = "match_id")
    private match match;

    private Integer homeBet;

    private Integer awayBet;

    public bet() {
        calculateBetScore();
    }

    public void calculateBetScore() {
        if (homeBet != null && awayBet != null && match != null) {

            int actualHomeScore = match.getHomeScore();
            int actualAwayScore = match.getAwayScore();

            // Perform your calculation based on actual scores and homeScore, awayScore
            betScore = calculateBetScoreBasedOnRules(actualHomeScore, actualAwayScore);
        }
    }

    // Calculate betScore based on homeScore and awayScore
    public int calculateBetScoreBasedOnRules(int actualHomeScore, int actualAwayScore) {
        return 10 - Math.abs(homeBet - actualHomeScore) + Math.abs(awayBet - actualAwayScore);
    }

}







