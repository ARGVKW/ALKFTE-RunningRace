package hu.gde.runningrace.model.api;

import hu.gde.runningrace.model.ScoreEntity;

public class RunnerResult {
    private final String runnerName;
    private final Double timeMinutes;

    public RunnerResult(ScoreEntity score) {
        this.runnerName = score.getRunner().getRunnerName();
        this.timeMinutes = score.getTimeMinutes();
    }

    public String getRunnerName() {
        return runnerName;
    }

    public Double getTimeMinutes() {
        return timeMinutes;
    }
}
