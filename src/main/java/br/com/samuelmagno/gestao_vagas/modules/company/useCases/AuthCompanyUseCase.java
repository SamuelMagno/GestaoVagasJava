package br.com.samuelmagno.gestao_vagas.modules.company.useCases;

import br.com.samuelmagno.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.samuelmagno.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import javax.naming.AuthenticationException;
import java.util.Date;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws Exception{
        var company = this.companyRepository.findByUsernameOrEmail(authCompanyDTO.getUsername(), null)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword())) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return token;
    }
}
