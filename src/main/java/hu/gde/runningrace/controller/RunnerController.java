package hu.gde.runningrace.controller;

import hu.gde.runningrace.model.RaceEntity;
import hu.gde.runningrace.repository.RunnerRepository;
import hu.gde.runningrace.repository.ScoreRepository;
import hu.gde.runningrace.model.RunnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RunnerController {

    @Autowired
    private RunnerRepository runnerRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/runners")
    public String getAllRunners(Model model) {
        List<RunnerEntity> runners = runnerRepository.findAll();
        model.addAttribute("runners", runners);
        return "runners";
    }

    @GetMapping("/runners/addrunner")
    public String showAddRaceForm(Model model) {
        RunnerEntity runner = new RunnerEntity();
        model.addAttribute("runner", runner);
        return "addrunner";
    }

    @PostMapping("/runners/createrunner")
    public String addRunner(@ModelAttribute RunnerEntity runner, Model model) {
        boolean nameExists = runnerRepository.existsByRunnerName(runner.getRunnerName());
        if (!nameExists) {
            runnerRepository.save(runner);
        } else {
            // handle error when race with the same name already exists
            model.addAttribute("error", "Another runner with the same name already exists");
            return "redirect:/runners/addrunner";
        }
        return "redirect:/runners";
    }
}
