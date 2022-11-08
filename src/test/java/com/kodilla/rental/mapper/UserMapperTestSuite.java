package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserMapperTestSuite {

    @InjectMocks
    private UserMapper userMapper;

    private User user;
    private UserDto userDto;
    private final List<User> userList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        user = new User(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com", "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,9,22), new HashSet<>());
        userDto = new UserDto(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com", "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,9,22), new HashSet<>());
        userList.add(user);
    }

    @Test
    void mapToUserTest() {
        //When
        User expected = userMapper.mapToUser(userDto);
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
        assertEquals(LocalDate.of(2022,9,22), expected.getSignupDate());
    }

    @Test
    void mapToUserDtoTest() {
        //When
        UserDto expected = userMapper.mapToUserDto(user);
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
        assertEquals(LocalDate.of(2022,9,22), expected.getSignupDate());
    }

    @Test
    void mapToUserDtoListTest() {
        //When
        List<UserDto> expected = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(1, expected.size());
    }
}
