package hu.gde.runningrace;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long raceId;
    private String name;
    private String location;
    private double distanceKm;
    private LocalDate date;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoreEntity> scores = new ArrayList<>();


    public RaceEntity() {
    }

    long getRaceId() {
        return raceId;
    }

    void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLocation() {
        return location;
    }

    void setLocation(String location) {
        this.location = location;
    }

    double getDistanceKm() {
        return distanceKm;
    }

    void setDistanceKm(double distance) {
        this.distanceKm = distance;
    }

    LocalDate getDate() {
        return this.date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ScoreEntity> getScores() {
        return scores;
    }
}
