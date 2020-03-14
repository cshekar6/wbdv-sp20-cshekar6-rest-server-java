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
public class WidgetService {

  @Autowired
  WidgetRepository widgetRepository;

  @Autowired
  TopicRepository topicRepository;

  public Widget createWidget(Integer topicId, Widget newWidget) {
    Topic topic = topicRepository.findById(topicId).get();
    newWidget.setTopic(topic);
    int size = findAllWidgets().size();
    newWidget.setWidgetOrder(size);
    return widgetRepository.save(newWidget);
  }

  public Widget findWidgetById(Integer wid) {
      return widgetRepository.findById(wid).get();
  }

  public List<Widget> findAllWidgets() {
    return (List<Widget>) widgetRepository.findAll();
  }

  public List<Widget> findWidgetsForTopic(Integer topicId) {
   return widgetRepository.findWidgetsForTopic(topicId);
  }

  public int deleteWidget(Integer wid) {
    Widget t = widgetRepository.findById(wid).get();
    int order = t.getWidgetOrder();
    findAllWidgets().stream().forEach(widget -> {
      if(widget.getWidgetOrder() > order) {
        widget.setWidgetOrder(widget.getWidgetOrder() - 1);
        widgetRepository.save(widget);
      }
    });
    widgetRepository.deleteById(wid);
    return 1;
  }

  public int updateWidget(Integer wid, Widget updatedWidget) {
    Widget t = widgetRepository.findById(wid).get();
    t.setTitle(updatedWidget.getTitle());
    t.setName(updatedWidget.getName());
    t.setSize(updatedWidget.getSize());
    t.setText(updatedWidget.getText());
    t.setType(updatedWidget.getType());
    t.setStyle(updatedWidget.getStyle());
    t.setUrl(updatedWidget.getUrl());
    widgetRepository.save(t);
    return 1;
  }

  public List<Widget> findWidgetsForTopic(Integer tid, Integer wid, int direction) {
    return findWidgetsForTopic(tid);
  }

}
