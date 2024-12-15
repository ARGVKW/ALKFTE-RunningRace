package hu.gde.runningrace;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long > {
}
