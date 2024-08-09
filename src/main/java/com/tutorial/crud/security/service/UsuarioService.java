package com.tutorial.crud.security.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    public Optional<Usuario> getById(int id){
        return usuarioRepository.findById(id);
    }

    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }


    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    public List<Usuario> getUsuariosByRol(RolNombre rolNombre) {
        return usuarioRepository.findByRoles_RolNombre(rolNombre);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id){
        usuarioRepository.deleteById(id);
    }
}
