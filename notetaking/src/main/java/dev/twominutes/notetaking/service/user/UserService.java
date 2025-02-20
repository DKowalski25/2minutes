package dev.twominutes.notetaking.service.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.models.User;

public interface UserService {
    User registerUser(UserRequestDto userDto);
}
