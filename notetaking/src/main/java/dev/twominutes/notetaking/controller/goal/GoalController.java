package dev.twominutes.notetaking.controller.goal;

import dev.twominutes.notetaking.auth.CustomUserDetails;
import dev.twominutes.notetaking.dto.goal.GoalDto;
import dev.twominutes.notetaking.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/goals")
public interface GoalController {
    @GetMapping
    ResponseEntity<List<GoalDto>> getGoals(@AuthenticationPrincipal CustomUserDetails userDetails);

    @GetMapping("/type/{type}")
    ResponseEntity<List<GoalDto>> getGoalsByType(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String type
    );

    @PostMapping
    ResponseEntity<GoalDto> createGoal(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody GoalDto goal
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGoal(@PathVariable Long id);
}
