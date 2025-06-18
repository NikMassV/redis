package edu.mikita.api.rest;

import edu.mikita.api.dto.UserDto;
import edu.mikita.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestControllerV1 {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable String id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody UserDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping("/{id}/visit")
    public ResponseEntity<Void> incrementVisit(@PathVariable String id) {
        userService.incrementVisitUnsafe(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/visit-incr")
    public ResponseEntity<Void> incrementIncrVisit(@PathVariable String id) {
        userService.incrementVisitWithIncr(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/visit-watch")
    public ResponseEntity<Void> incrementVisitWatch(@PathVariable String id) {
        userService.incrementWithWatch(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/visit-lock")
    public ResponseEntity<Void> incrementVisitLock(@PathVariable String id) {
        userService.incrementWithLock(id);
        return ResponseEntity.ok().build();
    }
}
