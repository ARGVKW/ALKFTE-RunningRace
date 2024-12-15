package hu.gde.runningrace.repository;

import hu.gde.runningrace.model.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<RaceEntity,Long > {
    boolean existsByRaceName(String raceName);
}
