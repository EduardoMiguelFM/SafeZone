package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.AuthDTO;
import br.com.fiap.SafeZone.dto.LoginResponseDTO;
import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Login com geração de token JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Login de usuário", description = "Autentica e gera token JWT")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        var userPassword = new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getSenha()
        );

        var auth = authenticationManager.authenticate(userPassword);
        var usuario = (Usuario) auth.getPrincipal();
        String token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
