package dev.twominutes.notetaking.controller;

import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.models.Goal;
import dev.twominutes.notetaking.service.GoalService;
import dev.twominutes.notetaking.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Goal>> getGoals(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(goalService.findByUser(user));
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Goal>> getGoalsByType(@AuthenticationPrincipal User user, @PathVariable String type) {
        return ResponseEntity.ok(goalService.findByUserAndType(user, type));
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@AuthenticationPrincipal User user, @RequestBody Goal goal) {
        goal.setUser(user);
        return ResponseEntity.ok(goalService.createGoal(goal));
    }

    public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.noContent().build();
    }
}
