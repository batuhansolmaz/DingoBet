package DingoBetBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DingoBetBackend.models.score;
import DingoBetBackend.repositories.scoreRepository;

import java.util.List;

@Service
public class scoreService {

    private final scoreRepository scoreRepository;

    @Autowired
    public scoreService(scoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public score getScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    public score saveScore(score score) {
        return scoreRepository.save(score);
    }

    public void deleteScoreById(Long id) {
        scoreRepository.deleteById(id);
    }

    public List<score> getAllScores() {
        return scoreRepository.findAll();
    }

}
