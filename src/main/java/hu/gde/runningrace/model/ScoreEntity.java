package hu.gde.runningrace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "score")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double timeMinutes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private RunnerEntity runner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = true)
    private RaceEntity race;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getTimeMinutes() {
        return this.timeMinutes;
    }

    public void setTimeMinutes(Double time) {
        this.timeMinutes = time;
    }

    public RunnerEntity getRunner() {
        return this.runner;
    }

    public void setRunner(RunnerEntity runner) {
        this.runner = runner;
    }

    public RaceEntity getRace() {
        return this.race;
    }

    public void setRace(RaceEntity race) {
        this.race = race;
    }
}
