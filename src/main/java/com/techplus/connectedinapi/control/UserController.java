package com.techplus.connectedinapi.control;

import com.techplus.connectedinapi.enums.InvitationStatus;
import com.techplus.connectedinapi.model.*;
import com.techplus.connectedinapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BasicController {

    private final UserService userService;
    private final PostService postService;
    private final InvitationService invitationService;
    private final RoleService roleService;
    private final UserContactService userContactService;
    private final UserPostService userPostService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PostService postService, InvitationService invitationService, RoleService roleService, UserContactService userContactService, UserPostService userPostService) {
        this.userService = userService;
        this.postService = postService;
        this.invitationService = invitationService;
        this.roleService = roleService;
        this.userContactService = userContactService;
        this.userPostService = userPostService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Método para listar todos os contatos do usuário logado
     *
     * @return response
     */
    @GetMapping("/contacts")
    public ResponseEntity<Map<String, Object>> findAllContactsByUser() {
        List<User> response;
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

    /**
     * Método para detalhar um usuário
     *
     * @param email
     * @return response
     */
    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> detailContact(@RequestParam String email) {
        User response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = userService.findByEmail(email);
            response.setRoles(new ArrayList<>());
            response.setPassword("");
            response.setContacts(new ArrayList<>());
            response.setPosts(new ArrayList<>());

            List<User> contactsByUser = userService.contactsByUser(getUserLogado().getId());
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

    /**
     * Método para enviar um convite para um usuário
     *
     * @param email
     * @return response
     */
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

    /**
     * Método para listar todos os convites recebidos pelo usuário logado
     *
     * @return response
     */
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

    /**
     * Método para aceitar/rejeitar um convite
     *
     * @param id
     * @param status
     * @return response
     */
    @PutMapping("/invitations/received")
    public ResponseEntity<Map<String, Object>> changeStatusInvitation(@RequestParam Long id,
                                                                      @RequestParam String status) {
        Invitation response;
        final Map<String, Object> result = new HashMap<>();
        try {
            Invitation invitation = invitationService.findById(id).get();
            InvitationStatus _status = InvitationStatus.getStatusByValue(status);
            invitation.setStatus(_status);

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

    /**
     * Método para desfazer amizade
     *
     * @param id
     * @return
     */
    @DeleteMapping("/contacts")
    public ResponseEntity<Map<String, Object>> undoFriendship(@RequestParam Long id) {
        final Map<String, Object> result = new HashMap<>();
        try {
            userService.undoFriendship(getUserLogado().getId(), id);
            userService.undoFriendship(id, getUserLogado().getId());

            result.put("success", true);
            result.put("error", null);
            result.put("body", "Contato removido");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    /**
     * Método para alterar senha
     *
     * @param passwordUpdate
     * @return
     */
    @PutMapping("/password/update")
    public ResponseEntity<Map<String, Object>> updatePassword(@Valid @RequestBody PasswordUpdate passwordUpdate) {
        final Map<String, Object> result = new HashMap<>();
        try {
            String currentPassword = getUserLogado().getPassword();

            if (passwordEncoder.matches(passwordUpdate.getOldPassword(), currentPassword)) {
                userService.updatePassword(getUserLogado().getId(), passwordEncoder.encode(passwordUpdate.getNewPassword()));
            } else {
                result.put("success", false);
                result.put("error", "Senha incorreta");
                result.put("body", null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            result.put("success", true);
            result.put("error", null);
            result.put("body", "Senha alterada");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    /**
     * Método para tornar um usuário administrador
     *
     * @param userUpdate
     * @return
     */
    @PostMapping("/admin/new")
    public ResponseEntity<Map<String, Object>> newUserAdmin(@Valid @RequestBody UserUpdate userUpdate) {
        User response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = userService.findByEmail(userUpdate.getEmail());

            Role role = roleService.findByRole("ROLE_ADMIN");

            response.getRoles().add(role);

            userService.save(response);

            response.setPassword("");
            response.setContacts(new ArrayList<>());
            response.setPosts(new ArrayList<>());

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

    /**
     * Método para bloquear amizade
     *
     * @param id
     * @return
     */
    @PutMapping("/contacts/block")
    public ResponseEntity<Map<String, Object>> blockFriendship(@RequestParam Long id) {
        final Map<String, Object> result = new HashMap<>();
        try {
            userContactService.blockFriendship(id, getUserLogado().getId());

            result.put("success", true);
            result.put("error", null);
            result.put("body", "Contato bloqueado");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    /**
     * Método para criar postagem
     *
     * @param post
     * @return
     */
    @PostMapping("/posts/new")
    public ResponseEntity<Map<String, Object>> newPost(@Valid @RequestBody Post post) {
        Post response;
        final Map<String, Object> result = new HashMap<>();
        try {
            post.setDate(new Date());
            post.setOwner(getUserLogado());

            response = postService.save(post);

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

    /**
     * Método para exibir timeline
     *
     * @return
     */
    @GetMapping("/timeline")
    public ResponseEntity<Map<String, Object>> timeline() {
        List<Post> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = postService.timeline(getUserLogado());

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

    /**
     * Método para buscar usuários pelo nome
     *
     * @param name
     * @return
     */
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> findByName(@RequestParam String name) {
        List<User> response;
        final Map<String, Object> result = new HashMap<>();
        try {
            response = userService.findByName(name, getUserLogado().getId());

            List<User> contactsByUser = userService.contactsByUser(getUserLogado().getId());
            for (User user: response) {
                user.setRoles(new ArrayList<>());
                user.setPassword("");
                user.setContacts(new ArrayList<>());
                user.setPosts(new ArrayList<>());
                if (contactsByUser.contains(user)) {
                    user.setMyFriend(true);
                } else {
                    user.setMyFriend(false);
                }
            }

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

    @DeleteMapping("/posts")
    public ResponseEntity<Map<String, Object>> deletePost(@RequestParam Long id) {
        final Map<String, Object> result = new HashMap<>();
        try {
            Post post = postService.findById(id).get();
            if(post.getOwner().getId().equals(getUserLogado().getId())){
                userPostService.userPostRemove(getUserLogado().getId(), id);
                postService.delete(post);
            } else {
                result.put("success", false);
                result.put("error", "Postagem não pertence ao usuário");
                result.put("body", null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            result.put("success", true);
            result.put("error", null);
            result.put("body", "Postagem removida");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("body", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
