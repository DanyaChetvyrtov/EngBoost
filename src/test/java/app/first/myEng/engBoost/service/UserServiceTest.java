package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.exception.ResourceNotFound;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository; // Мок репозитория

    @InjectMocks
    private UserService userService; // Тестируемый сервис

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков
    }

    @Test
    void testGetUserById_UserFound() {
        int userId = 1;
        LocalDateTime now = LocalDateTime.now();
        User mockUser = new User(
                userId,
                "John",
                "Doe",
                "johndoe",
                30,
                "john.doe@example.com",
                now,
                now,
                "password1243"
        );
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("johndoe", result.getUsername());
        assertEquals(30, result.getAge());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
        assertEquals("password1243", result.getPassword());
        verify(userRepository, times(1)).findById(userId); // Проверяем, что метод вызван 1 раз
    }

    @Test
    void testGetUserById_UserNotFound() {
        int userId = 999;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> userService.getUserById(userId));

        verify(userRepository, times(1)).findById(userId); // Проверяем, что метод вызван 1 раз
    }

    @Test
    void testCreateUser() {
        User newUser = new User(
                null, // id будет сгенерирован
                "John",
                "Doe",
                "johndoe",
                30,
                "john.doe@example.com",
                null, // createdAt и updatedAt будут установлены автоматически
                null,
                "password123"
        );

        User savedUser = new User(
                1, // id после сохранения
                "John",
                "Doe",
                "johndoe",
                30,
                "john.doe@example.com",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "password123"
        );

        when(userRepository.save(newUser)).thenReturn(savedUser);

        User result = userService.create(newUser);

        assertNotNull(result);
        assertEquals(1, result.getId()); // Проверяем, что id был присвоен
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("johndoe", result.getUsername());
        assertEquals(30, result.getAge());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("password123", result.getPassword());
        assertNotNull(result.getCreatedAt()); // Проверяем, что время создания установлено
        assertNotNull(result.getUpdatedAt()); // Проверяем, что время обновления установлено

        verify(userRepository, times(1)).save(newUser); // Проверяем, что save был вызван
    }

    @Test
    void testUpdateUser() {
        int userId = 1;
        User existingUser = new User(
                userId,
                "John",
                "Doe",
                "johndoe",
                30,
                "john.doe@example.com",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "password123"
        );

        User updatedUser = new User(
                userId,
                "Jane",
                "Smith",
                "janesmith",
                25,
                "jane.smith@example.com",
                null, // createdAt и updatedAt не должны изменяться
                null,
                "newpassword123"
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.update(updatedUser);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Jane", result.getFirstName()); // Проверяем обновленные данные
        assertEquals("Smith", result.getLastName());
        assertEquals("janesmith", result.getUsername());
        assertEquals(25, result.getAge());
        assertEquals("jane.smith@example.com", result.getEmail());
        assertEquals("newpassword123", result.getPassword());
        assertNotNull(result.getCreatedAt()); // createdAt не должен измениться
        assertNotNull(result.getUpdatedAt()); // updatedAt должен обновиться

        verify(userRepository, times(1)).findById(userId); // Проверяем, что findById был вызван
        verify(userRepository, times(1)).save(existingUser); // Проверяем, что save был вызван
    }

    @Test
    void testDeleteUser() {
        int userId = 1;
        User existingUser = new User(
                userId,
                "John",
                "Doe",
                "johndoe",
                30,
                "john.doe@example.com",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "password123"
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userRepository).deleteById(userId);

        userService.delete(userId);

        verify(userRepository, times(1)).findById(userId); // Проверяем, что findById был вызван
        verify(userRepository, times(1)).deleteById(userId); // Проверяем, что deleteById был вызван
    }

    @Test
    void testDeleteUser_UserNotFound() {
        int userId = 999;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> {
            userService.delete(userId);
        });

        verify(userRepository, times(1)).findById(userId); // Проверяем, что findById был вызван
        verify(userRepository, never()).deleteById(userId); // Проверяем, что deleteById не был вызван
    }
}
