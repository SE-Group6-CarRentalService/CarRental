package at.fhcampuswien.carrental.carrentalservice.service.impl;

import at.fhcampuswien.carrental.carrentalservice.dto.UserDto;
import at.fhcampuswien.carrental.carrentalservice.entity.User;
import at.fhcampuswien.carrental.carrentalservice.exception.BadRequestException;
import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(User::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get().toUserDto();
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public UserDto findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get().toUserDto();
        } else {
            throw new ResourceNotFoundException("User with username " + username + " not found.");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists.");
        }

        User user = User.fromUserDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return user.toUserDto();
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!userDto.getUsername().equals(user.getUsername())
                    && userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw new BadRequestException("Username already exists.");
            }

            user.setUsername(userDto.getUsername());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user = userRepository.save(user);

            return user.toUserDto();
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
    }
}
