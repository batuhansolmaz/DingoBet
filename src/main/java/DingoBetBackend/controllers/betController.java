package DingoBetBackend.controllers;

import DingoBetBackend.models.bet;
import DingoBetBackend.models.match;
import DingoBetBackend.models.room;
import DingoBetBackend.models.user;
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
    public ResponseEntity<String> createBet(@RequestBody bet bet, @RequestParam Long roomId , @RequestParam Long userId , @RequestParam Long matchId) {
        room room = roomService.getRoomById(roomId);
        user user = userService.getUserById(userId);
        match match = matchService.getMatchById(matchId);

        // Long authenticatedUserId = authenticationFacade.getAuthenticatedUserId();
        //user user = userService.getUserById(authenticatedUserId);

        if (!roomService.isUserInRoom(user, room)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a member of the room");
        }

        bet.setRoom(room);
        bet.setUser(user);
        bet.setMatch(match);

        betService.saveBet(bet);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bet created successfully");
    }

}
