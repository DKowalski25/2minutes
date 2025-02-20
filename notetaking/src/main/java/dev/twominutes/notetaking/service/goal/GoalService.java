package dev.twominutes.notetaking.service.goal;

import dev.twominutes.notetaking.dto.goal.GoalDto;

import java.util.List;

public interface GoalService{
    List<GoalDto> findByUserId(Long userId);
    List<GoalDto> findByUserAndType(Long userId, String type);
    GoalDto createGoal(GoalDto goal);
    void deleteGoal(Long goalId);
}
