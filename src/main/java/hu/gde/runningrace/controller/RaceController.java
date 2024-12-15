package hu.gde.runningrace.controller;

import hu.gde.runningrace.repository.RaceRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import hu.gde.runningrace.model.RaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RaceController {
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private ScoreRepository scoreRepository;

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

    @PostMapping("/races/createrace")
    public String addRace(@ModelAttribute RaceEntity race, Model model) {
        boolean nameExists = raceRepository.existsByRaceName(race.getRaceName());
        if (!nameExists) {
            raceRepository.save(race);
        } else {
            // handle error when race with the same name already exists
            model.addAttribute("error", "Another race with the same name already exists");
            return "redirect:/races/addrace";
        }
        return "redirect:/races";
    }
}
