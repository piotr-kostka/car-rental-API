package com.kodilla.rental.service;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.UserDto;
import com.kodilla.rental.mapper.UserMapper;
import com.kodilla.rental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserDbService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserDto userDto;
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        user = new User(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com", "password", "123456789", BigDecimal.ZERO, false, LocalDate.of(2022,9,22), new HashSet<>());
        userDto = new UserDto(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com", "password", "123456789", BigDecimal.ZERO, false, LocalDate.of(2022,9,22), new HashSet<>());
        userList.add(user);
        userDtoList.add(userDto);
    }

    @Test
    void getAllUsersTest() {
        //Given
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);
        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<UserDto> expectedList = userService.getAllUsers();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getUserId());
        assertEquals("name", expectedList.get(0).getFirstName());
        assertEquals("lastname", expectedList.get(0).getLastName());
        assertEquals(940930123212L, expectedList.get(0).getPesel());
        assertEquals("address", expectedList.get(0).getAddress());
        assertEquals("mail@mail.com", expectedList.get(0).getMail());
        assertEquals("password", expectedList.get(0).getPassword());
        assertEquals("123456789", expectedList.get(0).getCreditCardNo());
        assertEquals(BigDecimal.ZERO, expectedList.get(0).getToPay());
        assertFalse(expectedList.get(0).isBlocked());
        assertEquals(LocalDate.of(2022,9,22), expectedList.get(0).getSignupDate());
    }

    @Test
    void getUserTest() {
        //Given
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userRepository.findById(userDto.getUserId())).thenReturn(Optional.of(user));

        //When
        UserDto expected = userService.getUser(1L);

        //Then
        assertEquals(1L, expected.getUserId());
        assertEquals("name", expected.getFirstName());
        assertEquals("lastname", expected.getLastName());
        assertEquals(940930123212L, expected.getPesel());
        assertEquals("address", expected.getAddress());
        assertEquals("mail@mail.com", expected.getMail());
        assertEquals("password", expected.getPassword());
        assertEquals("123456789", expected.getCreditCardNo());
        assertEquals(BigDecimal.ZERO, expected.getToPay());
        assertFalse(expected.isBlocked());
        assertEquals(LocalDate.of(2022,9,22), expected.getSignupDate());
    }

    @Test
    void createUserTest() {
        //Given
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        User savedUser = userMapper.mapToUser(userDto);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapToUserDto(savedUser)).thenReturn(userDto);

        //When
        UserDto expected = userService.createUser(userDto);

        //Then
        assertEquals(1L, expected.getUserId());
        assertEquals("name", expected.getFirstName());
        assertEquals("lastname", expected.getLastName());
        assertEquals(940930123212L, expected.getPesel());
        assertEquals("address", expected.getAddress());
        assertEquals("mail@mail.com", expected.getMail());
        assertEquals("password", expected.getPassword());
        assertEquals("123456789", expected.getCreditCardNo());
        assertEquals(BigDecimal.ZERO, expected.getToPay());
        assertFalse(expected.isBlocked());
        assertEquals(LocalDate.of(2022,9,22), expected.getSignupDate());
    }

    @Test
    void updateUserTest() {
        //Given
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        User savedUser = userMapper.mapToUser(userDto);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapToUserDto(savedUser)).thenReturn(userDto);
        when(userRepository.existsById(userDto.getUserId())).thenReturn(true);

        //When
        UserDto expected = userService.updateUser(userDto);

        //Then
        assertEquals(1L, expected.getUserId());
        assertEquals("name", expected.getFirstName());
        assertEquals("lastname", expected.getLastName());
        assertEquals(940930123212L, expected.getPesel());
        assertEquals("address", expected.getAddress());
        assertEquals("mail@mail.com", expected.getMail());
        assertEquals("password", expected.getPassword());
        assertEquals("123456789", expected.getCreditCardNo());
        assertEquals(BigDecimal.ZERO, expected.getToPay());
        assertFalse(expected.isBlocked());
        assertEquals(LocalDate.of(2022,9,22), expected.getSignupDate());
    }

    @Test
    void deleteUserTest() {
        //Given
        when(userRepository.findById(userDto.getUserId())).thenReturn(Optional.of(user));

        //When
        userService.deleteUser(1L);

        //Then
        verify(userRepository, times(1)).deleteById(1L);
    }
}
