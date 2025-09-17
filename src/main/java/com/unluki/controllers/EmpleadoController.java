package com.unluki.controllers;

import com.unluki.models.Empleado;
import com.unluki.models.Sucursal;
import com.unluki.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {

    public String createEmpleado(int id_empleado, String nombre, int id_sucursal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Sucursal sucursal = session.find(Sucursal.class, id_sucursal);
            if (sucursal == null) {
                return "No existe la sucursal con id=" + id_sucursal;
            }

            Empleado empleado = new Empleado(id_empleado, nombre, sucursal);
            session.persist(empleado);

            session.getTransaction().commit();
            return "Empleado creado.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al intentar crear el empleado.";
    }

    public String eliminarEmpleado(int id_empleado) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Empleado empleado = session.find(Empleado.class, id_empleado);
            if (empleado != null) {
                session.remove(empleado);
                session.getTransaction().commit();
                return "Empleado eliminado.";
            }
            return "No se encontró al empleado.";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error al intentar eliminar el empleado.";
    }

    public String modificarEmpleado(int id_empleado, String nombre, int id_sucursal) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Empleado empleado = session.find(Empleado.class, id_empleado);
            Sucursal sucursal = session.find(Sucursal.class, id_sucursal);
            if (empleado != null) {
                if (!nombre.isEmpty()) {
                    empleado.setNombre(nombre);
                }
                if (sucursal != null) {
                    empleado.setSucursal(sucursal);
                }
                session.merge(empleado);
                session.getTransaction().commit();
                return "Empleado modificado.";
            }
            return "No se encontró al empleado.";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error al intentar modificar al empleado.";
    }

    public List<Empleado> consultarEmpleado(int id_empleado) {

        List<Empleado> empleados = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            NativeQuery<Empleado> query;
            if (id_empleado == -1) {
                String sql = "SELECT * FROM empleado";
                query = session.createNativeQuery(sql, Empleado.class);
            } else {
                String sql = "SELECT * FROM empleado WHERE id_empleado = :id_emp";
                query = session.createNativeQuery(sql, Empleado.class);
                query.setParameter("id_emp", id_empleado);
            }
            empleados = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empleados;
    }

}
