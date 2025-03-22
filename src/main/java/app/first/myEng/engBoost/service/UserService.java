package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.exception.ResourceNotFound;
import app.first.myEng.engBoost.models.user.Role;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> getUsers(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page - 1, size));
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    @Transactional
    public User create(User user) {
        logger.info("Creating user. username: {}", user.getUsername());
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        var userDB = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFound("User with such id does not exist"));

        logger.info("Updating user. username: {}", user.getUsername());
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setAge(user.getAge());
        userDB.setUsername(user.getUsername());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userDB);
    }

    @Transactional
    public void delete(int id) {
        logger.info("Deleting user. id: {}", id);
        getUserById(id); // для проверки, что пользователь с таким id сущ
        userRepository.deleteById(id);
    }
}
