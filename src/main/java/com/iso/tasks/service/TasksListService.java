package com.iso.tasks.service;

import com.iso.tasks.model.TasksList;
import com.iso.tasks.repository.TasksListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksListService {

    private final TasksListRepository tasksListRepository;

    public TasksListService(TasksListRepository tasksListRepository) {
        this.tasksListRepository = tasksListRepository;
    }

    public List<TasksList> getAllLists(long userId) {
        return tasksListRepository.getUserLists(userId);
    }

    public TasksList createList(String title, long userId) {
        return tasksListRepository
                .save(TasksList.builder()
                        .title(title)
                        .userId(userId)
                        .build());
    }

    public Optional<TasksList> changeListTitle(long listId, String title, long userId) {
        Optional<TasksList> listInDb = tasksListRepository.findById(listId);
        if (listInDb.isEmpty() || listInDb.get().getUserId() != userId) {
            return Optional.empty();
        }

        TasksList list = listInDb.get();
        list.setTitle(title);
        tasksListRepository.save(list);
        return Optional.of(list);
    }

    public Optional<TasksList> deleteList(long userId, long listId) {

        Optional<TasksList> listInDb = tasksListRepository.findById(listId);
        if (listInDb.isEmpty() || listInDb.get().getUserId() != userId) {
            return Optional.empty();
        }
         tasksListRepository.deleteById(listId);

        return Optional.of(listInDb.get());
    }
}
