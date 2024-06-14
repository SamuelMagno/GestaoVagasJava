package br.com.samuelmagno.gestao_vagas.modules.company.useCases;

import br.com.samuelmagno.gestao_vagas.exceptions.UserFoundException;
import br.com.samuelmagno.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.samuelmagno.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws Exception{
        var company = this.companyRepository.findByUsernameOrEmail(authCompanyDTO.getUsername(), null)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword())) {
            throw new Exception("Senha inválida");
        }
    }
}
