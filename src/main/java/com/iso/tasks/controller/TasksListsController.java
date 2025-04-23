package com.iso.tasks.controller;

import com.iso.tasks.model.dto.CreateTasksListDTO;
import com.iso.tasks.model.dto.PublicTaskListDTO;
import com.iso.tasks.service.TasksListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
public class TasksListsController {

    private final TasksListService tasksListService;

    public TasksListsController(TasksListService tasksListService) {
        this.tasksListService = tasksListService;
    }

    @GetMapping("")
    public Iterable<PublicTaskListDTO> getLists(Authentication authentication) {
        return tasksListService.getAllLists(Long.parseLong(authentication.getName()));
    }

    @PostMapping("")
    public PublicTaskListDTO createList(Authentication authentication,
                                        @RequestBody CreateTasksListDTO createTasksListDTO) {
        return tasksListService
                .createList(createTasksListDTO.getTitle(), Long.parseLong(authentication.getName()));
    }

    @PatchMapping("/{listId}")
    public ResponseEntity<PublicTaskListDTO> changeListTitle(Authentication authentication,
                                                             @PathVariable(name = "listId") long listId,
                                                             @RequestBody CreateTasksListDTO createTasksListDTO) {
        return tasksListService
                .changeListTitle(listId, createTasksListDTO.getTitle(), Long.parseLong(authentication.getName()));

    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<PublicTaskListDTO> deleteList(Authentication authentication,
                                                        @PathVariable(name = "listId") long listId) {
        return tasksListService
                .deleteList(Long.parseLong(authentication.getName()), listId);
    }
}
