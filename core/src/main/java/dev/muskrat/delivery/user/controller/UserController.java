package dev.muskrat.delivery.user.controller;

import dev.muskrat.delivery.user.dto.UserDTO;
import dev.muskrat.delivery.user.dto.UserUpdateDTO;
import dev.muskrat.delivery.user.dto.UserUpdateResponseDTO;
import dev.muskrat.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('PARTNER') or @authorizationServiceImpl.isEquals(authentication, #userUpdateDTO.id)")
    public UserDTO findById(
        @PathVariable Long id
    ) {
        return userService.findById(id);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN') or @authorizationServiceImpl.isEquals(authentication, #userUpdateDTO.id)")
    public UserUpdateResponseDTO update(
        @Valid @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        return userService.update(userUpdateDTO);
    }
}