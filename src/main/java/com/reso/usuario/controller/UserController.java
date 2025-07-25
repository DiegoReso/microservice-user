package com.reso.usuario.controller;

import com.reso.usuario.business.UserService;
import com.reso.usuario.business.dto.AddressDTO;
import com.reso.usuario.business.dto.PhoneDTO;
import com.reso.usuario.business.dto.UserDTO;
import com.reso.usuario.infrascture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.insertUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        UserDTO userByEmail = userService.findUserByEmail(email);
        return ResponseEntity.ok(userByEmail);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestParam("email") String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateDataUser(token, userDTO));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO, @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updateAddress(id,addressDTO));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO, @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updatePhone(id,phoneDTO));
    }


    @PostMapping("/address")
    public ResponseEntity<UserDTO> insertAddress(@RequestBody AddressDTO addressDTO,
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.insertAddress(token,addressDTO));
    }

    @PostMapping("/phone")
    public ResponseEntity<UserDTO> insertPhone(@RequestBody PhoneDTO phoneDTO,
                                                @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.insertPhone(token, phoneDTO));
    }

}

