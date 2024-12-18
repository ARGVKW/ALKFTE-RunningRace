package hu.gde.runningrace.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hu.gde.runningrace.model.RunnerEntity;
import hu.gde.runningrace.model.api.RunnerResult;
import hu.gde.runningrace.model.ScoreEntity;
import hu.gde.runningrace.repository.RaceRepository;
import hu.gde.runningrace.model.RaceEntity;
import hu.gde.runningrace.repository.RunnerRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<String> updateRace(@Valid @RequestBody RaceUpdateRequest payload, BindingResult binding) {
        Gson gson = new Gson();
        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(binding.getAllErrors()));
        }
        try {
            LocalDate.parse(payload.raceDate);
        } catch (Exception e) {
            String error = gson.fromJson("{ \"error\": \"Invalid date format\"}", JsonObject.class).toString();
            return ResponseEntity.badRequest().body(error);
        }
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
        @NotNull(message = "raceId is mandatory")
        public Long raceId;

        @NotEmpty(message = "raceName is mandatory")
        public String raceName;

        @NotEmpty(message = "raceLocation is mandatory")
        public String raceLocation;

        @NotNull(message = "raceDistance is mandatory")
        @Positive(message = "raceDistance must be positive")
        public Double raceDistance;

        @NotEmpty(message = "raceDate is mandatory")
        public String raceDate;
    }

    @PostMapping("/addresult")
    public ResponseEntity<String> addResult(@Valid @RequestBody RaceAddResultRequest payload, BindingResult binding) {
        if (binding.hasErrors()) {
            Gson gson = new Gson();
            return ResponseEntity.badRequest().body(gson.toJson(binding.getAllErrors()));
        }
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
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runner with ID " + payload.runnerId + " not found");
        }
    }
    public static class RaceAddResultRequest {
        @NotNull(message = "raceId is mandatory")
        public Long raceId;

        @NotNull(message = "runnerId is mandatory")
        public Long runnerId;

        @NotNull(message = "Time is mandatory")
        @Min(value = 1, message = "Time must be greater than 0")
        public Double timeMinutes;
    }
}
