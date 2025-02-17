package com.iceb.library.service;

import com.iceb.library.dto.UserRequestDto;
import com.iceb.library.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto topicRequestDto);
    UserResponseDto getUserById(UUID id);
    List<UserResponseDto> searchUsers(String name, boolean archived);
    UserResponseDto updateUser(UUID id, UserRequestDto topicRequestDto);
    UserResponseDto deleteUser(UUID id);
}
