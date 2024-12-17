package hu.gde.runningrace.model.api;

import hu.gde.runningrace.model.RunnerEntity;
import hu.gde.runningrace.model.ScoreEntity;

public class RunnerBase {
    private long id;
    private final String runnerName;
    private int age;
    private String gender;

    public RunnerBase(RunnerEntity runner) {
        this.id = runner.getId();
        this.runnerName = runner.getRunnerName();
        this.age = runner.getAge();
        this.gender = runner.getGender();
    }

    public long getId() {
        return id;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
