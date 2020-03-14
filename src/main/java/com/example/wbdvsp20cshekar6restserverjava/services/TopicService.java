package com.example.wbdvsp20cshekar6restserverjava.services;

import com.example.wbdvsp20cshekar6restserverjava.models.Topic;
import com.example.wbdvsp20cshekar6restserverjava.models.Widget;
import com.example.wbdvsp20cshekar6restserverjava.repositories.TopicRepository;
import com.example.wbdvsp20cshekar6restserverjava.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cshekar6
 */
@Service
public class TopicService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  WidgetRepository widgetRepository;

  public List<Topic> findAllTopics() {
    return (List<Topic>)topicRepository.findAll();
  }

  public Topic findTopicById(int tid) {
    return topicRepository.findById(tid).get();
  }

  public int deleteTopic(int tid) {
    Topic topic = topicRepository.findById(tid).get();
    for (int i=0; i<topic.getWidgets().size();i++) {
      widgetRepository.delete(topic.getWidgets().get(i));
    }
    topicRepository.deleteById(tid);
    return 1;
  }

  public int updateTopic(int tid, Topic newTopic) {
    Topic t = topicRepository.findById(tid).get();
    t.setTitle(newTopic.getTitle());
    topicRepository.save(t);
    return 1;
  }

  public Topic createTopic(Topic newTopic) {
    return topicRepository.save(newTopic);
  }

  public Widget createWidgetForTopic(
          Integer tid,
          Widget newWidget) {
    Topic topic = topicRepository.findById(tid).get();
    newWidget.setTopic(topic);
    return widgetRepository.save(newWidget);
  }

  public List<Topic> findTopicsForLesson(String lessonId) {
    return topicRepository.findTopicsForLesson(lessonId);
  }
}
