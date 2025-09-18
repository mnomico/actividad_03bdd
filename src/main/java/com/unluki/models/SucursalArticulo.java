package com.unluki.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "s_a")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalArticulo {
    
    @EmbeddedId
    private SucursalArticuloId id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idSucursal")
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    private Sucursal sucursal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idArticulo")
    @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo")
    private Articulo articulo;
    
    @Column(name = "precio", nullable = false)
    private int precio;
    
    @Min(0)
    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int stock = 0;
    
    public SucursalArticulo(Sucursal sucursal, Articulo articulo, int precio, int stock) {
        this.sucursal = sucursal;
        this.articulo = articulo;
        this.precio = precio;
        this.stock = stock;
        this.id = new SucursalArticuloId(sucursal.getIdSucursal(), articulo.getIdArticulo());
    }
    
    @Override
    public String toString() {
        return "SucursalArticulo [sucursal=" + sucursal.getIdSucursal() + 
               ", articulo=" + articulo.getIdArticulo() + 
               ", precio=" + precio + 
               ", stock=" + stock + "]";
    }
}
