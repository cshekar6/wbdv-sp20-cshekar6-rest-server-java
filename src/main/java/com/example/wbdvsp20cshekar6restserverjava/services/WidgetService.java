package com.example.wbdvsp20cshekar6restserverjava.services;

import com.example.wbdvsp20cshekar6restserverjava.models.Topic;
import com.example.wbdvsp20cshekar6restserverjava.models.Widget;
import com.example.wbdvsp20cshekar6restserverjava.repositories.TopicRepository;
import com.example.wbdvsp20cshekar6restserverjava.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    return widgetRepository.findWidgetsForTopic(topicId).stream()
    .sorted(Comparator.comparing(Widget::getWidgetOrder))
    .collect(Collectors.toList());
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
    List<Widget> widgets = findWidgetsForTopic(tid);
    for(int i=0; i<widgets.size();i++) {
      int currentOrder = widgets.get(i).getWidgetOrder();
      int widgetId = widgets.get(i).getId();
      if(widgetId == wid) {
        if(direction ==1) {
          widgets.get(i).setWidgetOrder(widgets.get(i-1).getWidgetOrder());
          widgets.get(i-1).setWidgetOrder(currentOrder);
          widgetRepository.save(widgets.get(i-1));
        } else if (direction ==2) {
          widgets.get(i).setWidgetOrder(widgets.get(i+1).getWidgetOrder());
          widgets.get(i+1).setWidgetOrder(currentOrder);
          widgetRepository.save(widgets.get(i+1));
        }
        widgetRepository.save(widgets.get(i));
        break;
      }
    }
    return findWidgetsForTopic(tid);
  }

}
