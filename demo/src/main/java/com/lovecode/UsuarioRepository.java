package com.lovecode;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface UsuarioRepository extends JpaRepository<usuario, Long> {
    usuario findByEmail(String email);
}
    