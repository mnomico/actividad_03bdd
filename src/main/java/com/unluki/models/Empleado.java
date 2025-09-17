package com.unluki.models;

import jakarta.persistence.*;


@Entity
@Table(
        name = "empleado",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        }
)
public class Empleado {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_empleado")
        private int id_empleado;

        @Column(name = "nombre", length = 200)
        private String nombre;

        @ManyToOne
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
        private Sucursal sucursal;

        public Empleado() {}

        public Empleado(int id_empleado, String nombre, Sucursal sucursal) {
                this.id_empleado = id_empleado;
                this.nombre = nombre;
                this.sucursal = sucursal;
        }

        public int getId_empleado() {
                return this.id_empleado;
        }

        public String getNombre() {
                return this.nombre;
        }

        public void setId_empleado(int id_sucursal) {
                this.id_empleado = id_sucursal;
        }

        public void setNombre(String descripcion) {
                this.nombre = descripcion;
        }

        public Sucursal getSucursal() {return sucursal;}

        public void setSucursal(Sucursal sucursal) {this.sucursal = sucursal;}

        @Override
        public String toString() {
                return "id_empleado=" + id_empleado + " nombre=" + nombre + " id_sucursal=" + sucursal.getId_sucursal();
        }
}
