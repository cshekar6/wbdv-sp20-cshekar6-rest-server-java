package com.example.wbdvsp20cshekar6restserverjava.controllers;

import com.example.wbdvsp20cshekar6restserverjava.models.Widget;
import com.example.wbdvsp20cshekar6restserverjava.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cshekar6
 */
@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

  @Autowired
  WidgetService service;

  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(
          @RequestBody Widget newWidget, @PathVariable("tid") Integer tid) {
    return service.createWidget(tid,newWidget);
  }

  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") Integer tid) {
    return service.findWidgetsForTopic(tid);
  }

  @GetMapping("/api/topics/{tid}/widgets/{wid}/{direction}")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") Integer tid,
                                          @PathVariable("wid") Integer wid,
                                          @PathVariable("direction") int direction) {
    return service.findWidgetsForTopic(tid,wid,direction);
  }

  @PutMapping("/api/widgets/{wid}")
  public int updateWidget(@PathVariable("wid") Integer wid, @RequestBody  Widget widget) {
     return service.updateWidget(wid,widget);
  }

  @DeleteMapping("/api/widgets/{wid}")
  public int deleteWidget(@PathVariable("wid") Integer wid) {
    return service.deleteWidget(wid);
  }

  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets() {
    return service.findAllWidgets();
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid") Integer wid) {
   return service.findWidgetById(wid);
  }




}
