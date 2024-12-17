package hu.gde.runningrace.controller;

import hu.gde.runningrace.repository.RunnerRepository;
import hu.gde.runningrace.model.RunnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/runner")
public class RunnerRestController {
    private final RunnerRepository runnerRepository;

    @Autowired
    public RunnerRestController(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    @GetMapping("")
    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    @GetMapping("/{id}")
    public RunnerEntity getRunner(@PathVariable Long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    @PostMapping("addrunner")
    public ResponseEntity<String> addRunner(@RequestBody RunnerEntity runner) {
        if (runner == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean nameExists = runnerRepository.existsByRunnerName(runner.getRunnerName());
        if (nameExists) {
            return ResponseEntity.badRequest().body("Another runner with the name " + runner.getRunnerName() + " already exists");
        }
        runnerRepository.save(runner);
        return ResponseEntity.ok().build();
    }

}
