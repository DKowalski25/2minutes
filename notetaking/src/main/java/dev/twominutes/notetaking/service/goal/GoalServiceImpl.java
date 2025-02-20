package dev.twominutes.notetaking.service.goal;

import dev.twominutes.notetaking.dto.goal.GoalDto;
import dev.twominutes.notetaking.repository.goal.GoalRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;

    @Override
    public List<GoalDto> findByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public List<GoalDto> findByUserAndType(Long userId, String type) {
        return goalRepository.findByUserAndType(userId, type);
    }

    @Override
    public GoalDto createGoal(GoalDto goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }
}
