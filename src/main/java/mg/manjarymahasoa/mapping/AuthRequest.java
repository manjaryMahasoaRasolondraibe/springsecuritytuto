package mg.manjarymahasoa.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public final class AuthRequest {
	private String username ;
	private String password ;
}
