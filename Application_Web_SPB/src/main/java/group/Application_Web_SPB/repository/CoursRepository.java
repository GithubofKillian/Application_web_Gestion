package group.Application_Web_SPB.repository;

import group.Application_Web_SPB.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Long> {
}
