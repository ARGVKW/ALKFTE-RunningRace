package hu.gde.runningrace.repository;

import hu.gde.runningrace.model.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long > {
}
