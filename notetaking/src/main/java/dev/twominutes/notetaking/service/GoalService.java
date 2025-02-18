package dev.twominutes.notetaking.service;

import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.models.Goal;
import dev.twominutes.notetaking.repository.GoalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;

    public List<Goal> findByUser(User user) {
        return goalRepository.findByUser(user);
    }

    public List<Goal> findByUserAndType(User user, String type) {
        return goalRepository.findByUserAndType(user, type);
    }

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }
}
