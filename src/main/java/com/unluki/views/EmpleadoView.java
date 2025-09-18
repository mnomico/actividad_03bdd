package com.unluki.views;

import com.unluki.controllers.EmpleadoController;
import com.unluki.models.Empleado;

import java.util.List;
import java.util.Scanner;

import static com.unluki.utils.InputUtil.leerEntero;

public class EmpleadoView {
    private final Scanner scanner;
    private final EmpleadoController empleadoController;

    public EmpleadoView() {
        this.scanner = new Scanner(System.in);
        this.empleadoController = new EmpleadoController();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- ABM y Consulta de empleado ---");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Eliminar empleado");
            System.out.println("3. Modificar empleado");
            System.out.println("4. Buscar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    eliminarEmpleado();
                    break;
                case 3:
                    modificarEmpleado();
                    break;
                case 4:
                    buscarEmpleado();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while ( opcion != 0 );
    }

    public void agregarEmpleado() {
        System.out.println("\n--- Agregar empleado ---");
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("id_sucursal: ");
        int idSucursal = leerEntero(scanner);
        String result = empleadoController.createEmpleado(nombre, idSucursal);
        System.out.println(result);
    }

    public void eliminarEmpleado() {
        System.out.println("\n--- Eliminar Empleado ---");
        System.out.println("ID del Empleado: ");
        int idEmpleado = leerEntero(scanner);
        String result = empleadoController.eliminarEmpleado(idEmpleado);
        System.out.println(result);
    }

    public void modificarEmpleado() {
        System.out.println("\n--- Modificar Empleado ---");
        System.out.println("ID del Empleado: ");
        int idEmpleado = leerEntero(scanner);
        System.out.println("!!SI NO DESEA MODIFICAR UN CAMPO, SOLO PRESIONE ENTER!!");
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("id_sucursal: ");
        int idSucursal = leerEntero(scanner);
        String result = empleadoController.modificarEmpleado(idEmpleado, nombre, idSucursal);
        System.out.println(result);
    }

    public void buscarEmpleado() {
        System.out.println("\n--- Buscar Empleado ---");
        System.out.println("!!SI DESEA VER TODOS LOS EMPLEADOS, SOLO PRESIONE ENTER!!");
        System.out.println("ID del Empleado: ");
        int idEmpleado = leerEntero(scanner);
        List<Empleado> result = empleadoController.consultarEmpleado(idEmpleado);
        for ( Empleado empleado : result ) {
            System.out.printf("%s%n", empleado.toString());
        }
    }

}
