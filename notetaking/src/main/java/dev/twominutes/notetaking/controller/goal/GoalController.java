package dev.twominutes.notetaking.controller.goal;

import dev.twominutes.notetaking.security.service.CustomUserDetails;
import dev.twominutes.notetaking.dto.goal.GoalDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Goals Management", description = "API for managing goals")
@RequestMapping("/api/goals")
public interface GoalController {
    @Operation(
            summary = "Get all goals",
            description = "Returns a list of all goals for the authenticated user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieves a list of all goals for the current user"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access"
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<GoalDto>> getGoals(@AuthenticationPrincipal CustomUserDetails userDetails);


    @Operation(
            summary = "Get goals by type",
            description = "Returns a list of goals for the authenticated user with the specified type",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieves a list of goals for the current user with the specified type"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access"
                    )
            }
    )
    @GetMapping("/type/{type}")
    ResponseEntity<List<GoalDto>> getGoalsByType(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String type
    );

    @Operation(
            summary = "Create a new goal",
            description = "Creates a new goal for the authenticated user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Creates a new goal for the current user"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access"
                    )
            }
    )
    @PostMapping
    ResponseEntity<GoalDto> createGoal(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody GoalDto goal
    );

    @Operation(
            summary = "Delete a goal",
            description = "Deletes a goal for the authenticated user",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Deletes a goal for the current user"
                    ),
                    @ApiResponse(
                        responseCode = "200",
                        description = "Goal deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Goal not found"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access"
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGoal(@PathVariable Long id);
}
