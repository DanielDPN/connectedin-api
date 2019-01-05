package com.techplus.connectedinapi.control;

import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.service.PostService;
import com.techplus.connectedinapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BasicController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/contacts")
    public ResponseEntity<Map<String, Object>> findAllContactsByUser() {
        Set<User> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = userService.contactsByUser(getUserLogado().getId());

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

    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> detailContact(@RequestParam String email) {
        User response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = userService.findByEmail(email);
            response.setRoles(new HashSet<>());
            response.setPassword("");
            response.setContacts(userService.contactsByUser(response.getId()));
            response.setPosts(postService.findByOwner(response));

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
