package com.iso.tasks.controller;

import com.iso.tasks.model.TasksList;
import com.iso.tasks.model.dto.TasksListDTO;
import com.iso.tasks.service.TasksListService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class TasksListsController {

    private final TasksListService tasksListService;

    public TasksListsController(TasksListService tasksListService) {
        this.tasksListService = tasksListService;
    }

    @GetMapping("")
    public Iterable<TasksList> getLists(Authentication authentication) {
        return tasksListService.getAllLists(Long.parseLong(authentication.getName()));
    }

    @PostMapping("")
    public TasksList createList(Authentication authentication, @RequestBody TasksListDTO tasksListDTO) {
        return tasksListService.createList(tasksListDTO.getTitle(), Long.parseLong(authentication.getName()));
    }

    @PatchMapping("/{listId}")
    public ResponseEntity<TasksList> changeListTitle(Authentication authentication,
                                          @PathVariable(name = "listId") long listId,
                                          @RequestBody TasksListDTO tasksListDTO) {
        Optional<TasksList> listOptional = tasksListService
                .changeListTitle(listId, tasksListDTO.getTitle(), Long.parseLong(authentication.getName()));

        return listOptional
                .map(tasksList -> new ResponseEntity<>(tasksList, HttpStatusCode.valueOf(200)))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatusCode.valueOf(404)));

    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<TasksList> deleteList(Authentication authentication, @PathVariable(name = "listId") long listId) {
        Optional<TasksList> listOptional = tasksListService
                .deleteList(Long.parseLong(authentication.getName()), listId);

        return listOptional
                .map(tasksList -> new ResponseEntity<>(tasksList, HttpStatusCode.valueOf(200)))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatusCode.valueOf(404)));
    }
}
