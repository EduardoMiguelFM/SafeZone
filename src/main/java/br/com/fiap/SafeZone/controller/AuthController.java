package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.AuthDTO;
import br.com.fiap.SafeZone.dto.LoginResponseDTO;
import br.com.fiap.SafeZone.dto.AuthRegisterDTO;
import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.repository.UsuarioRepository;
import br.com.fiap.SafeZone.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Login e criação de usuários com geração de token JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Login do usuário", description = "Autentica usuário e retorna um token JWT")
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid AuthDTO dados) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }

    @Operation(summary = "Registrar novo usuário", description = "Cadastra um novo usuário com senha criptografada e retorna token JWT")
    @PostMapping("/register")
    public LoginResponseDTO register(@RequestBody @Valid AuthRegisterDTO dados) {
        if (usuarioRepository.findByEmail(dados.login()) != null) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dados.login());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(dados.role());

        usuarioRepository.save(novoUsuario);

        String token = tokenService.generateToken(novoUsuario);
        return new LoginResponseDTO(token);
    }
}
