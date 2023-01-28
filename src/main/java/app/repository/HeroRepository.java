package app.repository;

import app.domain.dto.HeroDTO;
import app.domain.entity.ClassEnum;
import app.domain.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> findByName(ClassEnum name);




}
