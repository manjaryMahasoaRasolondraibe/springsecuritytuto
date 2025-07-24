package mg.manjarymahasoa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.manjarymahasoa.mapping.AuthRequest;
import mg.manjarymahasoa.model.Users;
import mg.manjarymahasoa.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final UserService serv ;
	
	@GetMapping("/users")
	public List<Users> usersAll() {
		return serv.findAllUser() ;
	}
	
}
