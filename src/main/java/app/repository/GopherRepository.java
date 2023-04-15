package app.repository;

import app.entity.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GopherRepository extends JpaRepository<Translator, Long> {
}
