package com.example.wbdvsp20cshekar6restserverjava.repositories;

/**
 * @author cshekar6
 */
import com.example.wbdvsp20cshekar6restserverjava.models.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WidgetRepository
        extends CrudRepository<Widget, Integer> {

  @Query("select widget from Widget widget where widget.topic.id=:tid")
  public List<Widget> findWidgetsForTopic(
          @Param("tid") Integer topicId);

}