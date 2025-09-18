package com.unluki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(
        name = "empleado",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        }
)
@Getter
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private int idEmpleado;

    @Column(name = "nombre", length = 40)
    @Setter
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @Setter
    private Sucursal sucursal;

    public Empleado(String nombre, Sucursal sucursal) {
        this.nombre = nombre;
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Empleado [id_empleado=" + idEmpleado + " nombre=" + nombre + " id_sucursal=" + sucursal.getIdSucursal() + "]";
    }
}
