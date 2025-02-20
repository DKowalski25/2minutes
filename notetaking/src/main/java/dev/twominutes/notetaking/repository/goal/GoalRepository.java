package dev.twominutes.notetaking.repository.goal;

import dev.twominutes.notetaking.dto.goal.GoalDto;

import java.util.List;
import java.util.Optional;

public interface GoalRepository{
    GoalDto save(GoalDto goalDto);
    Optional<GoalDto> findById(Long id);
    List<GoalDto> findByUserId(Long userId);
    List<GoalDto> findByUserAndType(Long userId, String type);
    void deleteById(Long id);
}
