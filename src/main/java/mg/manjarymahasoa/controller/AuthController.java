package mg.manjarymahasoa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import mg.manjarymahasoa.mapping.*;
import mg.manjarymahasoa.model.Users;
import mg.manjarymahasoa.service.AuthService;
import mg.manjarymahasoa.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final UserService serv ;
	
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/register")
	public Users register(@RequestBody AuthRequest request) {
		return serv.register(request) ;
	}
}
