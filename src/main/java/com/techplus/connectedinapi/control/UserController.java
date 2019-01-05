package com.techplus.connectedinapi.control;

import com.techplus.connectedinapi.enums.InvitationStatus;
import com.techplus.connectedinapi.model.Invitation;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.service.InvitationService;
import com.techplus.connectedinapi.service.PostService;
import com.techplus.connectedinapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BasicController {

    private final UserService userService;
    private final PostService postService;
    private final InvitationService invitationService;

    @Autowired
    public UserController(UserService userService, PostService postService, InvitationService invitationService) {
        this.userService = userService;
        this.postService = postService;
        this.invitationService = invitationService;
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
            response.setContacts(new HashSet<>());
            response.setPosts(new HashSet<>());

            Set<User> contactsByUser = userService.contactsByUser(getUserLogado().getId());
            if (contactsByUser.contains(response)) {
                response.setMyFriend(true);
            } else {
                response.setMyFriend(false);
            }

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

    @PostMapping("/invite")
    public ResponseEntity<Map<String, Object>> invite(@RequestParam String email) {
        Invitation response;
        final Map<String, Object> result = new HashMap<>();
        try {
            User receiver = userService.findByEmail(email);
            Invitation invitation = new Invitation(
                    new User(getUserLogado().getId(), getUserLogado().getEmail(), getUserLogado().getName()),
                    new User(receiver.getId(), receiver.getEmail(), receiver.getName()),
                    new Date(),
                    InvitationStatus.PENDING
            );
            response = invitationService.save(invitation);

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

    @GetMapping("/invitations/received")
    public ResponseEntity<Map<String, Object>> invitationsReceived() {
        List<Invitation> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = invitationService.findByReceiver_Id(getUserLogado().getId());

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
