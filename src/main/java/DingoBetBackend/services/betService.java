package DingoBetBackend.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import DingoBetBackend.models.bet;
import DingoBetBackend.repositories.betRepository;

import java.util.List;

@Service
public class betService {

    // get interface then call its methods
    private betRepository betRepository;

    @Autowired
    public betService(betRepository betRepository) {
        this.betRepository = betRepository;
    }

    public bet getBetById(Long id) {
        return betRepository.findById(id).orElse(null);
    }

    public bet saveBet(bet bet) {
        return betRepository.save(bet);
    }

    public void deleteBetById(Long id) {
        betRepository.deleteById(id);
    }
    public List<bet> getAllBets() {
        return betRepository.findAll();
    }


}
