package com.inmozara.crm.usuario.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.tarea.model.Tarea;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private int idUsuario;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ESTADO_USUARIO")
    private EstadoUsuario estadoUsuario;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ROL")
    private Rol rol;
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Cita> citasComoCliente;
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Contrato> contratosComoCliente;
    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Inmueble> inmuebles;
    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(rol.getRol()))
                , new SimpleGrantedAuthority(String.valueOf(estadoUsuario.getEstadoUsuario()))
                , new SimpleGrantedAuthority(String.valueOf(getIdUsuario())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
