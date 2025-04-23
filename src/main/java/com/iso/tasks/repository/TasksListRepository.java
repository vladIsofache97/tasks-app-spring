package com.iso.tasks.repository;

import com.iso.tasks.model.TasksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksListRepository extends CrudRepository<TasksList, Long> {

    List<TasksList> findAllByUserId(long userId);
}
