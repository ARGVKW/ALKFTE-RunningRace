package hu.gde.runningrace.controller;

import hu.gde.runningrace.model.RunnerEntity;
import hu.gde.runningrace.model.api.RunnerResult;
import hu.gde.runningrace.model.ScoreEntity;
import hu.gde.runningrace.repository.RaceRepository;
import hu.gde.runningrace.model.RaceEntity;
import hu.gde.runningrace.repository.RunnerRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/race")
public class RaceRestController {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private RunnerRepository runnerRepository;

    @GetMapping("")
    public List<RaceEntity> getAllRaces() {
        return raceRepository.findAll();
    }

    @GetMapping("/{id}")
    public RaceEntity getRace(@PathVariable Long id) {
        return raceRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/average")
    public Double getAverageTime(@PathVariable Long id) {
        List<ScoreEntity> scores = scoreRepository.findAllByRaceId(id).stream().toList();
        return scores.stream().mapToDouble(ScoreEntity::getTimeMinutes).average().orElse(0);
    }

    @GetMapping("/{id}/runners")
    public List<RunnerResult> getRaceRunners(@PathVariable Long id) {
        return scoreRepository.findAllByRaceId(id)
                .stream()
                .sorted(Comparator.comparing(ScoreEntity::getTimeMinutes))
                .map(RunnerResult::new)
                .toList();
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateRace(@RequestBody RaceUpdateRequest payload) {
        RaceEntity race = raceRepository.findById(payload.raceId).orElse(null);
        if (race != null) {
            race.setRaceName(payload.raceName);
            race.setLocation(payload.raceLocation);
            race.setDistanceKm(payload.raceDistance);
            race.setRaceDate(LocalDate.parse(payload.raceDate));
            raceRepository.save(race);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race with ID " + payload.raceId + " not found");
    }
    public static class RaceUpdateRequest {
        public Long raceId;
        public String raceName;
        public String raceLocation;
        public Double raceDistance;
        public String raceDate;
    }

    @PostMapping("/addresult")
    public ResponseEntity<String> addResult(@RequestBody RaceAddResultRequest payload) {
        RaceEntity race = raceRepository.findById(payload.raceId).orElse(null);
        RunnerEntity runner = runnerRepository.findById(payload.runnerId).orElse(null);
        ScoreEntity score = new ScoreEntity();
        if (race != null && runner != null) {
            score.setRace(race);
            score.setRunner(runner);
            score.setTimeMinutes(payload.timeMinutes);
            scoreRepository.save(score);
            return ResponseEntity.ok().build();
        } else if (race == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race with ID " + payload.raceId + " not found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runner with ID " + payload.runnerId + " not found");
    }
    public static class RaceAddResultRequest {
        public Long raceId;
        public Long runnerId;
        public Double timeMinutes;
    }
}
