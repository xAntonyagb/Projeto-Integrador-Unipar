package br.unipar.assetinsight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_USUARIO", nullable = false)
    private UUID id;

    @Column(name = "DS_USERNAME", unique = true)
    private String username;

    @Column(name = "DS_PASSWORD")
    private String password;

    @Column(name = "DT_RECORD")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp dtRecord;

    @Column(name = "DT_LOGIN")
    private Timestamp dtLogin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RolesEntity> listRoles;


    public UsuarioEntity(String username, String password, List<RolesEntity> listRoles) {
        this.username = username;
        this.password = password;
        this.listRoles = listRoles;
        this.dtRecord = new Timestamp(System.currentTimeMillis());
    }


    /* Security */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getPermisao().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
}
