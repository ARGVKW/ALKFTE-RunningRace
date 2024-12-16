package hu.gde.runningrace.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String runnerName;
    private int age;
    private String gender;

    @OneToMany(mappedBy = "runner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoreEntity> scores = new ArrayList<>();


    public RunnerEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRunnerName() {
        return this.runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ScoreEntity> getScores() {
        return this.scores;
    }
}
