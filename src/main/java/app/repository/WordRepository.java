package app.repository;

import app.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

//    public Translator findById();

    // Query / Named Query / Statemtn / Prepared Statement
}
