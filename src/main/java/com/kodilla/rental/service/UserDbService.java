package com.kodilla.rental.service;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.UserDto;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.mapper.UserMapper;
import com.kodilla.rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUser(final long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public UserDto createUser(final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        user.setSignupDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @Transactional
    public void deleteUser(final long userId) {
        userRepository.deleteById(userId);
    }
}
