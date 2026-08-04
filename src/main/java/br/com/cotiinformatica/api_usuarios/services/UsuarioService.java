package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.repositories.PerfilRepository;
import br.com.cotiinformatica.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired //Injeção de dependência
    private UsuarioRepository usuarioRepository;

    @Autowired //Injeção de dependência
    private PerfilRepository perfilRepository;

    /*
        Método para criar um usuário
     */
    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) throws Exception {

        //Criando um novo usuário
        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setDataHoraCadastro(LocalDateTime.now());

        //TODO Verificar se o email informado já está cadastrado para outro usuário no banco
        //TODO Senha informada pelo usuário deverá ser uma senha forte
        //TODO Senha do usuário deverá ser criptografada
        //TODO Todo usuário novo será cadastrado com perfil "OPERADOR"

        return null;
    }

    /*
        Método para autenticar um usuário
     */
    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) throws Exception {
        //TODO Implementar
        return null;
    }
}
