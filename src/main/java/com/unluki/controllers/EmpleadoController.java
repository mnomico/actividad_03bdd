package com.unluki.controllers;

import com.unluki.models.Empleado;
import com.unluki.models.Sucursal;
import com.unluki.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {

    public String createEmpleado(String nombre, int idSucursal) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            Sucursal sucursal = session.find(Sucursal.class, idSucursal);
            if ( sucursal == null ) {
                return "No existe la sucursal con id=" + idSucursal;
            }

            Empleado empleado = new Empleado(nombre, sucursal);
            session.persist(empleado);

            session.getTransaction().commit();
            return "Empleado creado con id=" + empleado.getIdEmpleado();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "Error al intentar crear el empleado.";
    }

    public String eliminarEmpleado(int idEmpleado) {

        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            Empleado empleado = session.find(Empleado.class, idEmpleado);
            if ( empleado != null ) {
                session.remove(empleado);
                session.getTransaction().commit();
                return "Empleado eliminado.";
            }
            return "No se encontró al empleado.";
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return "Error al intentar eliminar el empleado.";
    }

    public String modificarEmpleado(int idEmpleado, String nombre, int idSucursal) {

        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            Empleado empleado = session.find(Empleado.class, idEmpleado);
            Sucursal sucursal = session.find(Sucursal.class, idSucursal);
            if ( empleado != null ) {
                if ( !nombre.isEmpty() ) {
                    empleado.setNombre(nombre);
                }
                if ( sucursal != null ) {
                    empleado.setSucursal(sucursal);
                }
                session.merge(empleado);
                session.getTransaction().commit();
                return "Empleado modificado.";
            }
            return "No se encontró al empleado.";
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return "Error al intentar modificar al empleado.";
    }

    public List<Empleado> consultarEmpleado(int idEmpleado) {

        List<Empleado> empleados = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            NativeQuery<Empleado> query;
            if ( idEmpleado == -1 ) {
                String sql = "SELECT * FROM empleado";
                query = session.createNativeQuery(sql, Empleado.class);
            } else {
                String sql = "SELECT * FROM empleado WHERE id_empleado = :id_emp";
                query = session.createNativeQuery(sql, Empleado.class);
                query.setParameter("id_emp", idEmpleado);
            }
            empleados = query.getResultList();
            session.getTransaction().commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return empleados;
    }

}
