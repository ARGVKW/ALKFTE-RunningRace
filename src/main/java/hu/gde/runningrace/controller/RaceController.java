package hu.gde.runningrace.controller;

import hu.gde.runningrace.model.RunnerEntity;
import hu.gde.runningrace.model.ScoreEntity;
import hu.gde.runningrace.repository.RaceRepository;
import hu.gde.runningrace.repository.RunnerRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import hu.gde.runningrace.model.RaceEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RaceController {
    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private RunnerRepository runnerRepository;

    @GetMapping("/races")
    public String getAllRaces(Model model) {
        List<RaceEntity> races = raceRepository.findAll();
        model.addAttribute("races", races);
        return "races";
    }

    @GetMapping("/races/addrace")
    public String showAddRaceForm(Model model) {
        RaceEntity race = new RaceEntity();
        model.addAttribute("race", race);
        return "addrace";
    }

    @PostMapping("/races/addrace")
    public String addRace(@Valid @ModelAttribute("race") RaceEntity race, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "addrace";
        }
        boolean nameExists = raceRepository.existsByRaceName(race.getRaceName());
        if (nameExists) {
            model.addAttribute("error", "Another race with the same name already exists");
            return "addrace";
        }
        raceRepository.save(race);
        return "redirect:/races";
    }

    @PostMapping("/race/{raceId}/{runnerId}/addresult")
    public String addResult(
            @PathVariable Long raceId,
            @PathVariable Long runnerId,
            @Valid @ModelAttribute("score") ScoreEntity score,
            BindingResult binding,
            Model model
    ) {
        if (binding.hasErrors()) {
            return "addresult";
        }
        RaceEntity race = raceRepository.findById(raceId).orElse(null);
        RunnerEntity runner = runnerRepository.findById(runnerId).orElse(null);
        if (race != null && runner != null) {
            score.setRace(race);
            score.setRunner(runner);
            score.setTimeMinutes(score.getTimeMinutes());
            scoreRepository.save(score);
            return "redirect:/races";
        } else if (race == null) {
            model.addAttribute("error", "Race with ID " + raceId + " not found");
            return "addresult";
        }
        model.addAttribute("error", "Runner with ID " + raceId + " not found");
        return "addresult";
    }

    @GetMapping("/race/{id}")
    public String getRaceById(@PathVariable Long id, Model model) {
        RaceEntity race = raceRepository.findById(id).orElse(null);
        List<ScoreEntity> scores = scoreRepository.findAllByRaceId(id).stream().toList();
        double averageTime = scores.stream().mapToDouble(ScoreEntity::getTimeMinutes).average().orElse(0);
        if (race != null) {
            model.addAttribute("race", race);
            model.addAttribute("scores", scores);
            model.addAttribute("averageTime", averageTime);
            return "race";
        } else {
            // handle error when runner is not found
            return "error";
        }
    }
}
