package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityService {

    public UsuarioEntity getUsuario() {
        return (UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public boolean hasRole(String role) {
        return getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }

    public boolean hasRole(PermissoesEnum role) {
        return hasRole(role.toString());
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
