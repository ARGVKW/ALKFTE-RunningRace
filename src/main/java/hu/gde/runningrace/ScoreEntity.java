package hu.gde.runningrace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "score")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long scoreId;
    private long timeMinutes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private RunnerEntity runner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = true)
    private RaceEntity race;

    long getScoreId() {
        return this.scoreId;
    }

    void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    long getTimeMinutes() {
        return this.timeMinutes;
    }

    void setTimeMinutes(long time) {
        this.timeMinutes = time;
    }

    RunnerEntity getRunner() {
        return this.runner;
    }

    void setRunner(RunnerEntity runner) {
        this.runner = runner;
    }

    RaceEntity getRace() {
        return this.race;
    }

    void setRace(RaceEntity race) {
        this.race = race;
    }
}
