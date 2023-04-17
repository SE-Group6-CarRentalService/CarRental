package at.fhcampuswien.carrental.carrentalservice.service;

import at.fhcampuswien.carrental.carrentalservice.dto.UserDto;
import at.fhcampuswien.carrental.carrentalservice.exception.BadRequestException;
import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.exception.UnauthorizedException;
import at.fhcampuswien.carrental.carrentalservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User registerUser(UserDto userDto) throws BadRequestException;

    User login(String email, String password) throws UnauthorizedException;

    User findById(Long id) throws ResourceNotFoundException;

    User findByEmail(String email) throws ResourceNotFoundException;

    List<User> findAll();

    User update(Long id, UserDto userDto) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;
}
