package hu.gde.runningrace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Race name is mandatory")
    private String raceName;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @NotNull(message = "Distance is mandatory")
    @Positive(message = "Distance must be positive")
    private double distanceKm;

    @NotNull(message = "Date is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate raceDate;

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

    public LocalDate getRaceDate() {
        return this.raceDate;
    }

    public void setRaceDate(LocalDate date) {
        this.raceDate = date;
    }

    public List<ScoreEntity> getScores() {
        return scores;
    }
}
