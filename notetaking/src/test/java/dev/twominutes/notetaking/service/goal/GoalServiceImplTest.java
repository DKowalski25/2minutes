package dev.twominutes.notetaking.service.goal;

import dev.twominutes.notetaking.dto.goal.GoalDto;
import dev.twominutes.notetaking.repository.goal.GoalRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GoalServiceImplTest {
    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalServiceImpl goalService;

    @Test
    void findByUserIdTest() {
        Long userId = 1L;

        List<GoalDto> expectedGoals = List.of(
                GoalDto.builder()
                        .id(1L)
                        .description("SOSAT' xui")
                        .deadline(LocalDate.of(2025, 3, 1))
                        .type("monthly")
                        .userId(userId)
                        .build()
        );

        when(goalRepository.findByUserId(userId)).thenReturn(expectedGoals);

        List<GoalDto> result = goalService.findByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("SOSAT' xui", result.get(0).getDescription());
        verify(goalRepository, times(1)).findByUserId(userId);
    }

    @Test
    void findByUserId_NotFoundTest() {
        Long userId = 999L;

        when(goalRepository.findByUserId(userId)).thenReturn(List.of());

        List<GoalDto> result = goalService.findByUserId(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(goalRepository, times(1)).findByUserId(userId);
    }

    @Test
    void findByUserAndTypeTest() {
        Long userId = 2L;
        String type = "monthly";

        List<GoalDto> expectedGoals = List.of(
                GoalDto.builder()
                        .id(2L)
                        .description("SIS")
                        .deadline(LocalDate.of(1025, 3, 22))
                        .type(type)
                        .userId(userId)
                        .build()
        );

        when(goalRepository.findByUserAndType(userId, type)).thenReturn(expectedGoals);

        List<GoalDto> result = goalService.findByUserAndType(userId, type);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("SIS", result.get(0).getDescription());
        verify(goalRepository, times(1)).findByUserAndType(userId, type);
    }

    @Test
    void findByUserAndType_NotFoundTest() {
        Long userId = 999L;
        String type = "monthly";

        when(goalRepository.findByUserAndType(userId, type)).thenReturn(List.of());

        List<GoalDto> result = goalService.findByUserAndType(userId, type);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(goalRepository, times(1)).findByUserAndType(userId, type);
    }

    @Test
    void testCreateGoal() {
        GoalDto goal = GoalDto.builder()
                .description("Read a book")
                .deadline(LocalDate.of(2025, 2, 25))
                .type("daily")
                .userId(3L)
                .build();

        GoalDto savedGoal = GoalDto.builder()
                .id(3L)
                .description("Read a book")
                .deadline(LocalDate.of(2025, 2, 25))
                .type("daily")
                .userId(3L)
                .build();

        when(goalRepository.save(goal)).thenReturn(savedGoal);

        GoalDto result = goalService.createGoal(goal);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("Read a book", result.getDescription());
        verify(goalRepository, times(1)).save(goal);
    }

    @Test
    void deletedGoalTest() {
        Long goalId = 4L;

        doNothing().when(goalRepository).deleteById(goalId);

        goalService.deleteGoal(goalId);

        verify(goalRepository, times(1)).deleteById(goalId);
    }
}
