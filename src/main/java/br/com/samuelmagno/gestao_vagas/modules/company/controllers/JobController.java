package br.com.samuelmagno.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuelmagno.gestao_vagas.modules.company.entities.JobEntity;
import br.com.samuelmagno.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCase useCase;
    @PostMapping("/")
    public ResponseEntity<Object> postMethodName(@Valid @RequestBody JobEntity jobEntity) {
        try {
            var result = this.useCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
    
}
