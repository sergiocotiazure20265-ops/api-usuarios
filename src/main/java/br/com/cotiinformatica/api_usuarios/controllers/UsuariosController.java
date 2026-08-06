package br.com.cotiinformatica.api_usuarios.controllers;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@Valid @RequestBody CriarUsuarioRequest request) {
        try {
            var response = usuarioService.criarUsuario(request);
            return ResponseEntity.status(201).body(response); //HTTP 201 (CREATED)
        }
        catch(EmailJaCadastradoException e) {
            return ResponseEntity.status(409).body(e.getMessage()); //HTTP 409 (CONFLICT)
        }
        catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage()); //HTTP 500 SERVER ERROR
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@Valid @RequestBody AutenticarUsuarioRequest request) {
        return ResponseEntity.ok().build();
    }
}
