package com.example.wbdvsp20cshekar6restserverjava.repositories;

import com.example.wbdvsp20cshekar6restserverjava.models.Topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author cshekar6
 */
public interface TopicRepository
        extends CrudRepository<Topic, Integer> {

  @Query(value = "SELECT * FROM topics WHERE topics.lesson_id=:lessonId", nativeQuery = true)
  public List<Topic> findTopicsForLesson(@Param("lessonId") String lessonId);

  // UPDATE `cs5610_sp20`.`topics` SET `title` = 'test' WHERE (`id` = '13');
}