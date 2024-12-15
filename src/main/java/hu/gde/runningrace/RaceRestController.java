package hu.gde.runningrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/race")
public class RaceRestController {
    private final RaceRepository raceRepository;

    @Autowired
    public RaceRestController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @GetMapping("")
    public List<RaceEntity> getAllRaces() {
        return raceRepository.findAll();
    }

    @GetMapping("/{id}")
    public RaceEntity getRace(@PathVariable Long id) {
        return raceRepository.findById(id).orElse(null);
    }
}
