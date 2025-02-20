package dev.twominutes.notetaking.controller.goal;

import dev.twominutes.notetaking.auth.CustomUserDetails;
import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.dto.goal.GoalDto;
import dev.twominutes.notetaking.service.goal.GoalService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalControllerImpl implements GoalController {
    private final GoalService goalService;

    @Override
    public ResponseEntity<List<GoalDto>> getGoals(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(goalService.findByUserId(userDetails.getId()));
    }

    @Override
    public ResponseEntity<List<GoalDto>> getGoalsByType(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                        @PathVariable String type) {
        return ResponseEntity.ok(goalService.findByUserAndType(userDetails.getId(), type));
    }

    @Override
    public ResponseEntity<GoalDto> createGoal(@AuthenticationPrincipal CustomUserDetails userDetails,
                                              @RequestBody GoalDto goal) {
        Long userId = userDetails.getId();
        goal.setUserId(userDetails.getId());
        return ResponseEntity.ok(goalService.createGoal(goal));
    }

    @Override
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
