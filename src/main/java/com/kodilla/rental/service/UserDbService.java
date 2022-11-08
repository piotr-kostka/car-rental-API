package com.kodilla.rental.service;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.UserDto;
import com.kodilla.rental.exception.alreadyExists.UserAlreadyExistException;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.mapper.UserMapper;
import com.kodilla.rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserDto createUser(final UserDto userDto) throws UserAlreadyExistException {

        List<Long> peselList = getAllUsers().stream()
                .map(UserDto::getPesel)
                .collect(Collectors.toList());

        if (!peselList.contains(userDto.getPesel())) {
            User user = userMapper.mapToUser(userDto);
            user.setToPay(BigDecimal.ZERO);
            user.setSignupDate(LocalDate.now());
            User savedUser = userRepository.save(user);
            return userMapper.mapToUserDto(savedUser);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Transactional
    public UserDto updateUser(final UserDto userDto) throws UserNotFoundException {

        if (!userRepository.existsById(userDto.getUserId())) {
            throw new UserNotFoundException(userDto.getUserId());
        } else {
            User user = userMapper.mapToUser(userDto);
            User savedUser = userRepository.save(user);
            return userMapper.mapToUserDto(savedUser);
        }
    }

    @Transactional
    public void deleteUser(final long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
