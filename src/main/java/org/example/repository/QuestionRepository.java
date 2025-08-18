package org.example.repository;


import org.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopicId(Long topicId);

    @Query(value = "SELECT * FROM questions q WHERE q.topic_id = :topicId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandomByTopicId(@Param("topicId") Long topicId);
}
