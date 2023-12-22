package ru.ntv.controllers.boss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.auth.NewUser;
import ru.ntv.dto.request.boss.CreateJournalistRequest;
import ru.ntv.dto.response.auth.AuthResponse;
import ru.ntv.dto.response.boss.JournalistListResponse;
import ru.ntv.dto.response.boss.JournalistResponse;
import ru.ntv.exception.NotRightRoleException;
import ru.ntv.service.AuthService;
import ru.ntv.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("journalists")
public class BossJournalistController {

    private final UserService userService;

    private final AuthService authService;

    public BossJournalistController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping(params = "id")
    ResponseEntity<JournalistResponse> getJournalistById(@RequestParam Integer id) {
        final JournalistResponse journalist = userService.getJournalistById(id);

        return ResponseEntity.ok(journalist);
    }

    @PostMapping
    ResponseEntity<JournalistResponse> hireJournalist(@RequestBody CreateJournalistRequest journalist) {
        final JournalistResponse response = userService.createJournalist(journalist);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    ResponseEntity<JournalistListResponse> getAllJournalists() {
        final List<JournalistResponse> journalists = userService.getAllJournalists();

        return ResponseEntity.ok(new JournalistListResponse(journalists));
    }

    @DeleteMapping
    ResponseEntity<?> dismissJournalist(@RequestParam Integer id) throws NotRightRoleException {
        return ResponseEntity.ok(userService.dismissJournalist(id));
    }

    @PostMapping
    ResponseEntity<AuthResponse> createJournalist(@Valid @RequestBody NewUser journalist) {
        return authService.createJournalist(journalist);
    }
}