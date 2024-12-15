package hu.gde.runningrace.controller;

import hu.gde.runningrace.repository.RaceRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import hu.gde.runningrace.model.RaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}