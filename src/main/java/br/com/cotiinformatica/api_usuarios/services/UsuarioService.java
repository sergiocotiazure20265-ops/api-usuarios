package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.components.CryptoComponent;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.entities.Perfil;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
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

    @Autowired //Injeção de dependência
    private CryptoComponent cryptoComponent;

    /*
        Método para criar um usuário
     */
    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) throws Exception {

        //Verificar se o email já está cadastrado no banco de dados
        if(usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException("O email informado já existe. Tente outro.");
        }

        //Criando um novo usuário
        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.sha256(request.senha()));
        usuario.setDataHoraCadastro(LocalDateTime.now());

        //Verificar se o banco de dados possui um perfil cadastrado com o nome 'Operador'
        var perfil = perfilRepository.findByNome("Operador");

        if(perfil == null) { //Se perfil não foi encontrado
            perfil = new Perfil(); //Criando o perfil
            perfil.setNome("Operador");

            //Cadastrando o perfil no banco de dados
            perfilRepository.save(perfil);
        }

        //Associar o usuário ao perfil
        usuario.setPerfil(perfil);

        //Salvando o usuário no banco de dados
        usuarioRepository.save(usuario);

        //Retornar os dados do DTO de resposta
        return new CriarUsuarioResponse(
               "Usuário cadastrado com sucesso.",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                perfil.getNome(),
                LocalDateTime.now()
        );
    }

    /*
        Método para autenticar um usuário
     */
    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) throws Exception {
        //TODO Implementar
        return null;
    }
}
