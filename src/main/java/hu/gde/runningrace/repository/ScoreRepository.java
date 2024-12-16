package hu.gde.runningrace.repository;

import hu.gde.runningrace.model.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long > {
    List<ScoreEntity> findAllByRaceId(Long id);
    List<ScoreEntity> findAllByRunnerId(Long id);
}
