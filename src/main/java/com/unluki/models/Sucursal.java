package com.unluki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "sucursal",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_sucursal")
        }
)
@Getter
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private int idSucursal;

    @Setter
    @Column(name = "descripcion", length = 40)
    private String descripcion;

    @Setter
    @Column(name = "direccion", length = 40, nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SucursalArticulo> sucursalArticulos = new HashSet<>();

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Empleado> empleados = new HashSet<>();

    public Sucursal(int idSucursal, String descripcion, String direccion) {
        this.idSucursal = idSucursal;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public Sucursal(String descripcion, String direccion) {
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Sucursal [id_sucursal=" + idSucursal +
                ", descripcion=" + descripcion +
                ", direccion=" + direccion + "]";
    }
}
