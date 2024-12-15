package hu.gde.runningrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
