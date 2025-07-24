package mg.manjarymahasoa.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.manjarymahasoa.controller.UserController;
import mg.manjarymahasoa.mapping.AuthRequest;
import mg.manjarymahasoa.model.Users;
import mg.manjarymahasoa.repository.UserRepo;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
	
	public Users register(AuthRequest request) {		
		Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
		return userRepository.save(user) ;
	}
	
	public List<Users> findAllUser(){
		return userRepository.findAll() ;
	}
}
