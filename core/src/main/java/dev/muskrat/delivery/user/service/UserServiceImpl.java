package dev.muskrat.delivery.user.service;

import dev.muskrat.delivery.auth.dao.Role;
import dev.muskrat.delivery.auth.dao.Status;
import dev.muskrat.delivery.auth.repository.RoleRepository;
import dev.muskrat.delivery.cities.dao.CitiesRepository;
import dev.muskrat.delivery.cities.dao.City;
import dev.muskrat.delivery.components.exception.EntityNotFoundException;
import dev.muskrat.delivery.user.converter.JwtAuthorizationToUserConverter;
import dev.muskrat.delivery.user.dao.User;
import dev.muskrat.delivery.user.dto.UserDTO;
import dev.muskrat.delivery.user.dto.UserUpdateDTO;
import dev.muskrat.delivery.user.dto.UserUpdateResponseDTO;
import dev.muskrat.delivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CitiesRepository citiesRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAuthorizationToUserConverter jwtAuthorizationToUserConverter;

    @Override
    public User register(User user) {
        String userRoleName = Role.Name.USER.getName();
        Role.Name roleUser = Role.Name.USER;
        Role role = roleRepository
            .findByName(roleUser.getName())
            .orElseThrow(()-> new EntityNotFoundException("Role with name " + userRoleName + " not found"));

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setUsername(user.getEmail());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        City city = user.getCity();
        Long cityId = city != null ? city.getId() : null;

        return UserDTO.builder()
            .name(user.getName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .cityId(cityId)
            .build();
    }

    @Override
    public UserUpdateResponseDTO update(UserUpdateDTO userUpdateDTO) {
        Long userId = userUpdateDTO.getId();
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty())
            throw new EntityNotFoundException("User with id " + userId + " not found");
        User user = byId.get();

        if (userUpdateDTO.getEmail() != null)
            user.setEmail(userUpdateDTO.getEmail());

        if (userUpdateDTO.getName() != null)
            user.setName(userUpdateDTO.getName());

        if (userUpdateDTO.getPhone() != null)
            user.setPhone(userUpdateDTO.getPhone());

        if (userUpdateDTO.getCityId() != null) {
            Long cityId = userUpdateDTO.getCityId();
            City city = citiesRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException("City with id " + cityId + " not found"));
            user.setCity(city);
        }

        userRepository.save(user);

        return UserUpdateResponseDTO.builder()
            .id(user.getId())
            .build();
    }
}
