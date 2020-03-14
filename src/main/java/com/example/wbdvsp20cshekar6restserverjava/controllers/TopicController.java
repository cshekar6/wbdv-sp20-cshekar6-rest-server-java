package com.example.wbdvsp20cshekar6restserverjava.controllers;

import com.example.wbdvsp20cshekar6restserverjava.models.Topic;
import com.example.wbdvsp20cshekar6restserverjava.services.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cshekar6
 */

@RestController
@CrossOrigin(origins = "*")
public class TopicController {
  @Autowired
  TopicService topicService;

  @PostMapping("/api/lessons/{lid}/topics")
  public Topic createTopicForLesson(
          @PathVariable("lid") String lessonId,
          @RequestBody Topic newTopic
  ) {
    newTopic.setLessonId(lessonId);
    return topicService.createTopic(newTopic);
  }

  @GetMapping("/api/topics")
  public List<Topic> findAllTopics() {
    return topicService.findAllTopics();
  }

  @GetMapping("/api/lessons/{lessonId}/topics")
  public List<Topic> findTopicsForLesson(
          @PathVariable("lessonId") String lessonId) {
    return topicService.findTopicsForLesson(lessonId);
  }

  @DeleteMapping("/api/topics/{topicID}")
  public int deleteTopicForLesson(@PathVariable("topicID") Integer tid) {
    return topicService.deleteTopic(tid);
  }

  @PutMapping("/api/topics/{topicID}")
  public int updateTopic(@PathVariable("topicID") Integer tid, @RequestBody Topic topic2) {
    return topicService.updateTopic(tid,topic2);
  }

}
