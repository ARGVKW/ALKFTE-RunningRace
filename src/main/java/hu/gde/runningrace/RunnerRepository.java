package hu.gde.runningrace;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<RunnerEntity,Long > {
}
