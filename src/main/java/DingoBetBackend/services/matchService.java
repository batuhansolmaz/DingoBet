package DingoBetBackend.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import DingoBetBackend.models.match;
import DingoBetBackend.repositories.matchRepository;

import java.util.List;

@Service
public class matchService {

    // get interface then call its methods
    private matchRepository matchRepository;

    @Autowired
    public matchService(matchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public match saveBet(match match) {
        return matchRepository.save(match);
    }

    public void deleteBetById(Long id) {
        matchRepository.deleteById(id);
    }
    public List<match> getAllBets() {
        return matchRepository.findAll();
    }


}
