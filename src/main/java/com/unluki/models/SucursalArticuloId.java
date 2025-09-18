package com.unluki.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalArticuloId implements Serializable {
    
    @Column(name = "id_sucursal")
    private int idSucursal;
    
    @Column(name = "id_articulo")
    private int idArticulo;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SucursalArticuloId that = (SucursalArticuloId) o;
        return idSucursal == that.idSucursal && idArticulo == that.idArticulo;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idSucursal, idArticulo);
    }
}
