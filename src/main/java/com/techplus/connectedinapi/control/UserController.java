package com.techplus.connectedinapi.control;

import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BasicController {

    private final RoleService roleService;

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/contacts")
    public ResponseEntity<Map<String, Object>> findAllContactsByUser() {
        Set<User> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = getUserLogado().getContacts();

            result.put("success", true);
            result.put("error", null);
            result.put("body", response);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
