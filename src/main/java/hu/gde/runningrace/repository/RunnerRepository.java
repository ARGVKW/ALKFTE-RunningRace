package hu.gde.runningrace.repository;

import hu.gde.runningrace.model.RunnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<RunnerEntity,Long > {
}
