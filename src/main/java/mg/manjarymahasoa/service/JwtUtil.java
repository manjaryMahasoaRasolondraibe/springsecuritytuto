package mg.manjarymahasoa.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {
	private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"; // 256-bit key
    //private static final long EXPIRATION_TIME = 86400000; //24h
    //private static final long EXPIRATION_TIME = 10_000L; //10s
	private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(2);
    
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        log.info("expirationDate: "+expirationDate) ;
        
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        return Jwts.builder()
                .setSubject(username)
                .claim("auth", authorities)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {        	
            Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {    	
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        System.out.println("Expiration: " + claims.getExpiration());
        System.out.println("Issued at: " + claims.getIssuedAt());
        System.out.println("Temps restant (min): " + 
            (claims.getExpiration().getTime() - System.currentTimeMillis()) / 60000);
        
        return claims.getSubject();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
