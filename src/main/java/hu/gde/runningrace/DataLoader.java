package hu.gde.runningrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final RaceRepository raceRepository;
    private final RunnerRepository runnerRepository;

    @Autowired
    public DataLoader(RaceRepository raceRepository, RunnerRepository runnerRepository) {
        this.raceRepository = raceRepository;
        this.runnerRepository = runnerRepository;
    }

    @Override
    public void run(String... args) {

        // Create race entities

        RaceEntity raceEntity1 = new RaceEntity();
        raceEntity1.setName("Budapest Marathon");
        raceEntity1.setDistanceKm(42.195);
        raceEntity1.setDate(LocalDate.of(2024,10,10));

        raceRepository.save(raceEntity1);

        RaceEntity raceEntity2 = new RaceEntity();
        raceEntity2.setName("Vienna Half Marathon");
        raceEntity2.setDistanceKm(21.0975);
        raceEntity2.setDate(LocalDate.of(2024,9,15));

        raceRepository.save(raceEntity2);

/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity1 = new RunnerEntity();
        runnerEntity1.setRunnerName("Tomi");
        runnerEntity1.setGender("male");
        runnerEntity1.setAge(25);

        // Create score entities
        ScoreEntity scoreEntity1 = new ScoreEntity();
        scoreEntity1.setRunner(runnerEntity1);
        scoreEntity1.setRace(raceEntity1);
        scoreEntity1.setTimeMinutes(213);

        ScoreEntity scoreEntity2 = new ScoreEntity();
        scoreEntity2.setRunner(runnerEntity1);
        scoreEntity2.setRace(raceEntity2);
        scoreEntity2.setTimeMinutes(100);

        // Add score entities to the runner entity
        runnerEntity1.getScores().add(scoreEntity1);
        runnerEntity1.getScores().add(scoreEntity2);
        runnerRepository.save(runnerEntity1);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity1);
        raceEntity2.getScores().add(scoreEntity2);
        raceRepository.save(raceEntity1);
        raceRepository.save(raceEntity2);


/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity2 = new RunnerEntity();
        runnerEntity2.setRunnerName("Zsuzsi");
        runnerEntity2.setGender("female");
        runnerEntity2.setAge(23);

        // Create score entities
        ScoreEntity scoreEntity3 = new ScoreEntity();
        scoreEntity3.setRunner(runnerEntity2);
        scoreEntity3.setRace(raceEntity1);
        scoreEntity3.setTimeMinutes(230);

        ScoreEntity scoreEntity4 = new ScoreEntity();
        scoreEntity4.setRunner(runnerEntity2);
        scoreEntity4.setRace(raceEntity2);
        scoreEntity4.setTimeMinutes(105);

        // Add score entities to the runner entity
        runnerEntity2.getScores().add(scoreEntity3);
        runnerEntity2.getScores().add(scoreEntity4);
        runnerRepository.save(runnerEntity2);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity3);
        raceEntity2.getScores().add(scoreEntity4);
        raceRepository.save(raceEntity1);
        raceRepository.save(raceEntity2);


/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity3 = new RunnerEntity();
        runnerEntity3.setRunnerName("Gergő");
        runnerEntity3.setGender("male");
        runnerEntity3.setAge(27);

        // Create score entities
        ScoreEntity scoreEntity5 = new ScoreEntity();
        scoreEntity5.setRunner(runnerEntity3);
        scoreEntity5.setRace(raceEntity1);
        scoreEntity5.setTimeMinutes(200);

        // Add score entities to the runner entity
        runnerEntity3.getScores().add(scoreEntity5);
        runnerRepository.save(runnerEntity3);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity5);
        raceRepository.save(raceEntity1);


/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity4 = new RunnerEntity();
        runnerEntity4.setRunnerName("Emma");
        runnerEntity4.setGender("female");
        runnerEntity4.setAge(22);

        // Create score entities
        ScoreEntity scoreEntity6 = new ScoreEntity();
        scoreEntity6.setRunner(runnerEntity4);
        scoreEntity6.setRace(raceEntity1);
        scoreEntity6.setTimeMinutes(233);

        // Add score entities to the runner entity
        runnerEntity4.getScores().add(scoreEntity6);
        runnerRepository.save(runnerEntity4);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity6);
        raceRepository.save(raceEntity1);


/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity5 = new RunnerEntity();
        runnerEntity5.setRunnerName("Gedeon");
        runnerEntity5.setGender("male");
        runnerEntity5.setAge(67);

        // Create score entities
        ScoreEntity scoreEntity7 = new ScoreEntity();
        scoreEntity7.setRunner(runnerEntity5);
        scoreEntity7.setRace(raceEntity2);
        scoreEntity7.setTimeMinutes(145);

        // Add score entities to the runner entity
        runnerEntity5.getScores().add(scoreEntity7);
        runnerRepository.save(runnerEntity5);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity7);
        raceRepository.save(raceEntity1);


/* ------ Add new runner entity with scores ------------------------------------------ */

        // Create runner entity
        RunnerEntity runnerEntity6 = new RunnerEntity();
        runnerEntity6.setRunnerName("Matild");
        runnerEntity6.setGender("female");
        runnerEntity6.setAge(65);

        // Create score entities
        ScoreEntity scoreEntity8 = new ScoreEntity();
        scoreEntity8.setRunner(runnerEntity6);
        scoreEntity8.setRace(raceEntity2);
        scoreEntity8.setTimeMinutes(163);

        // Add score entities to the runner entity
        runnerEntity6.getScores().add(scoreEntity8);
        runnerRepository.save(runnerEntity6);

        // Add score entities to the race entities
        raceEntity1.getScores().add(scoreEntity8);
        raceRepository.save(raceEntity1);
    }
}
