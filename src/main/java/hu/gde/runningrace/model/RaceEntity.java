package hu.gde.runningrace.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String raceName;
    private String location;
    private double distanceKm;
    private LocalDate date;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoreEntity> scores = new ArrayList<>();


    public RaceEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRaceName() {
        return this.raceName;
    }

    public void setRaceName(String name) {
        this.raceName = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distance) {
        this.distanceKm = distance;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ScoreEntity> getScores() {
        return scores;
    }
}
