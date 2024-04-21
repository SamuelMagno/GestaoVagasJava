package br.com.samuelmagno.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuelmagno.gestao_vagas.exceptions.UserFoundException;
import br.com.samuelmagno.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.samuelmagno.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.samuelmagno.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
    

    @Autowired
    private CreateCandidateUseCase service;
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        
        try {
            var result = service.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }        
    }
}
