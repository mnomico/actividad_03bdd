package com.unluki.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(
        name = "articulo",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_articulo")
        }
)
@Getter
@NoArgsConstructor
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private int idArticulo;

    @Column(name = "descripcion", length = 100)
    @Setter
    private String descripcion;

    @Column(name = "stock_total", insertable = false, updatable = false,
            columnDefinition = "INTEGER DEFAULT 0") // read-only
    private Integer stockTotal = 0;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SucursalArticulo> sucursalArticulos = new HashSet<>();

    public Articulo(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Artículo [id_articulo=" + idArticulo +
                ", descripcion=" + descripcion +
                ", stock_total=" + stockTotal + "]";
    }
}
