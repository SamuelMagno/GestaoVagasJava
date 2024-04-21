package br.com.samuelmagno.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuelmagno.gestao_vagas.exceptions.UserFoundException;
import br.com.samuelmagno.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.samuelmagno.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;
    
    /**
     * @param candidate
     * @return
     */
    @PostMapping("")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        return candidateRepository.save(candidate);
    }
}
