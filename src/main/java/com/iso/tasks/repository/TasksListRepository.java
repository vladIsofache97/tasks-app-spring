package com.iso.tasks.repository;

import com.iso.tasks.model.TasksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksListRepository extends CrudRepository<TasksList, Long> {

    @Query("select l from lists l where l.userId = ?1")
    public List<TasksList> getUserLists(long userId);
}
