package com.techplus.connectedinapi.control;


import com.techplus.connectedinapi.model.RegisterCredentials;
import com.techplus.connectedinapi.model.Role;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.response.RegisterResponse;
import com.techplus.connectedinapi.response.RoleResponse;
import com.techplus.connectedinapi.service.RoleService;
import com.techplus.connectedinapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Método para registrar novo usuário
     * @param u
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterCredentials u) {
        User user = userService.findByEmail(u.getEmail());
        final Map<String, Object> result = new HashMap<>();
        try {
            if (user != null) {
                result.put("success", false);
                result.put("error", "E-mail já cadastrado");
                result.put("body", null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            } else {
                user = new User();
                user.setEmail(u.getEmail());
                user.setName(u.getName());
                user.setPassword(passwordEncoder.encode(u.getPassword()));
                user.setEnabled(true);

                Role role = roleService.findByRole("ROLE_DEFAULT");

                List<Role> roles = new ArrayList<>();
                roles.add(role);

                user.setRoles(roles);
                userService.save(user);

                RegisterResponse response = new RegisterResponse();
                response.setEmail(user.getEmail());
                response.setName(user.getName());

                Set<RoleResponse> rolesResponse = new HashSet<>();
                RoleResponse roleResponse = new RoleResponse();
                roleResponse.setRole(role.getRole());
                rolesResponse.add(roleResponse);

                response.setRoles(rolesResponse);
                response.setEnabled(user.isEnabled());

                result.put("success", true);
                result.put("error", null);
                result.put("body", response);
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
