package DingoBetBackend.controllers;

import DingoBetBackend.models.bet;
import DingoBetBackend.models.match;
import DingoBetBackend.models.room;
import DingoBetBackend.models.user;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import DingoBetBackend.services.betService;
import DingoBetBackend.services.roomService;
import DingoBetBackend.services.userService;
import DingoBetBackend.services.matchService;

@Controller
@RequestMapping("/bet")
public class betController {
    @Autowired
    private betService betService;
    @Autowired
    private roomService roomService;
    @Autowired
    private userService userService;
    @Autowired
    private matchService matchService;

    @GetMapping("/{betId}")
    public ResponseEntity<bet> getBetById(@PathVariable Long betId) {
        bet bet = betService.getBetById(betId);
        if (bet != null) {
            return ResponseEntity.ok(bet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("delete/{betId}")
    public ResponseEntity<String> deleteBet(@PathVariable Long betId) {
        betService.deleteBetById(betId);
        return ResponseEntity.ok("Bet deleted successfully");
    }
    @PostMapping("/create")
    public ResponseEntity<String> createBet(@RequestBody bet bet) {

        Long matchId = bet.getMatch().getId();
        match match = matchService.getMatchById(matchId);

        Long roomId = bet.getRoom().getId();
        room room = roomService.getRoomById(roomId);

        Long userId = bet.getUser().getId();
        user user = userService.getUserById(userId);

        bet.setMatch(match);
        bet.setRoom(room);
        bet.setUser(user);
        bet.calculateBetScore();
        bet newBet = betService.saveBet(bet);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBet.toString());
    }

    //TODO check if match started and check if bet is convenient
    @PutMapping("/{betId}/update")
    public ResponseEntity<String> updateBet(@PathVariable Long betId, @RequestBody bet newBet) {
        bet existingBet = betService.getBetById(betId);
        if (existingBet == null) {
            return ResponseEntity.notFound().build();
        }

        existingBet.setHomeBet(newBet.getHomeBet());
        existingBet.setAwayBet(newBet.getAwayBet());

        existingBet.calculateBetScore();

        betService.saveBet(existingBet);

        return ResponseEntity.ok("Bet updated successfully");
    }



}
