package com.unluki.controllers;

import com.unluki.models.Articulo;
import com.unluki.models.Sucursal;
import com.unluki.models.SucursalArticulo;
import com.unluki.models.SucursalArticuloId;
import com.unluki.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class SucursalArticuloController {

    public String asignarArticuloASucursal(int idSucursal, int idArticulo, int precio, int stock) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();

            Sucursal sucursal = session.find(Sucursal.class, idSucursal);
            Articulo articulo = session.find(Articulo.class, idArticulo);

            if ( sucursal == null ) {
                return "No se encontró la sucursal.";
            }
            if ( articulo == null ) {
                return "No se encontró el artículo.";
            }

            SucursalArticulo sucursalArticulo = new SucursalArticulo(sucursal, articulo, precio, stock);
            session.persist(sucursalArticulo);

            session.merge(articulo);

            session.getTransaction().commit();
            return "Artículo asignado a sucursal con id=(" + idSucursal + "," + idArticulo + ")";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "Error al asignar artículo a sucursal.";
    }

    public String modificarSucursalArticulo(int idSucursal, int idArticulo, int stock, int precio) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();

            SucursalArticuloId id = new SucursalArticuloId(idSucursal, idArticulo);
            SucursalArticulo sucursalArticulo = session.find(SucursalArticulo.class, id);

            if ( sucursalArticulo != null ) {

                if ( stock != -1 ) {
                    sucursalArticulo.setStock(stock);
                }
                if ( precio != -1 ) {
                    sucursalArticulo.setPrecio(precio);
                }
                session.merge(sucursalArticulo);

                session.getTransaction().commit();
                return "Stock actualizado correctamente.";
            }
            return "No se encontró la relación sucursal-artículo.";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "Error al modificar el stock.";
    }

    public String eliminarArticuloDeSucursal(int idSucursal, int idArticulo) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();

            SucursalArticuloId id = new SucursalArticuloId(idSucursal, idArticulo);
            SucursalArticulo sucursalArticulo = session.find(SucursalArticulo.class, id);

            if ( sucursalArticulo != null ) {
                session.remove(sucursalArticulo);

                session.getTransaction().commit();
                return "Artículo eliminado de la sucursal correctamente.";
            }
            return "No se encontró la relación sucursal-artículo.";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "Error al eliminar artículo de sucursal.";
    }

    public List<SucursalArticulo> consultarArticulosPorSucursal(int idSucursal) {
        List<SucursalArticulo> sucursalArticulos = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            String sql = "SELECT * FROM s_a WHERE id_sucursal = :idSucursal";
            NativeQuery<SucursalArticulo> query = session.createNativeQuery(sql, SucursalArticulo.class);
            query.setParameter("idSucursal", idSucursal);
            sucursalArticulos = query.getResultList();
            session.getTransaction().commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return sucursalArticulos;
    }

    public List<SucursalArticulo> consultarSucursalesPorArticulo(int idArticulo) {
        List<SucursalArticulo> sucursalArticulos = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            String sql = "SELECT * FROM s_a WHERE id_articulo = :idArticulo";
            NativeQuery<SucursalArticulo> query = session.createNativeQuery(sql, SucursalArticulo.class);
            query.setParameter("idArticulo", idArticulo);
            sucursalArticulos = query.getResultList();
            session.getTransaction().commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return sucursalArticulos;
    }
}
