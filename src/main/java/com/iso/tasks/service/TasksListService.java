package com.iso.tasks.service;

import com.iso.tasks.model.TasksList;
import com.iso.tasks.model.dto.PublicTaskListDTO;
import com.iso.tasks.repository.TasksListRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksListService {

    private final TasksListRepository tasksListRepository;

    public TasksListService(TasksListRepository tasksListRepository) {
        this.tasksListRepository = tasksListRepository;
    }

    public List<PublicTaskListDTO> getAllLists(long userId) {
        return tasksListRepository.findAllByUserId(userId).stream().map(PublicTaskListDTO::from).toList();
    }

    public PublicTaskListDTO createList(String title, long userId) {
        return PublicTaskListDTO.from(
                tasksListRepository.save(TasksList
                        .builder()
                        .title(title)
                        .userId(userId)
                        .build()));
    }

    public ResponseEntity<PublicTaskListDTO>  changeListTitle(long listId, String title, long userId) {
        Optional<TasksList> tasksListInDb = tasksListRepository.findById(listId);
        if (tasksListInDb.isEmpty() || tasksListInDb.get().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }

        TasksList tasksList = tasksListInDb.get();
        tasksList.setTitle(title);
        tasksListRepository.save(tasksList);
        return new ResponseEntity<>(PublicTaskListDTO.from(tasksList), HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<PublicTaskListDTO> deleteList(long userId, long listId) {

        Optional<TasksList> tasksListInDb = tasksListRepository.findById(listId);
        if (tasksListInDb.isEmpty() || tasksListInDb.get().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }

        tasksListRepository.deleteById(listId);
        return new ResponseEntity<>(PublicTaskListDTO.from(tasksListInDb.get()), HttpStatusCode.valueOf(200));
    }
}
