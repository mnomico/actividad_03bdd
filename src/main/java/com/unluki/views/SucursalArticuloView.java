package com.unluki.views;

import com.unluki.controllers.SucursalArticuloController;
import com.unluki.models.SucursalArticulo;

import java.util.List;
import java.util.Scanner;

import static com.unluki.utils.InputUtil.leerEntero;

public class SucursalArticuloView {
    private final Scanner scanner;
    private final SucursalArticuloController sucursalArticuloController;

    public SucursalArticuloView(Scanner scanner) {
        this.scanner = scanner;
        this.sucursalArticuloController = new SucursalArticuloController();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- ABM y Consulta SUCURSAL-ARTÍCULO ---");
            System.out.println("1. Asignar artículo a sucursal");
            System.out.println("2. Modificar sucursal-artículo");
            System.out.println("3. Eliminar artículo de sucursal");
            System.out.println("4. Ver artículos por sucursal");
            System.out.println("5. Ver sucursales por artículo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1 -> asignarArticuloASucursal();
                case 2 -> modificarSucursalArticulo();
                case 3 -> eliminarArticuloDeSucursal();
                case 4 -> consultarArticulosPorSucursal();
                case 5 -> consultarSucursalesPorArticulo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while ( opcion != 0 );
    }

    public void asignarArticuloASucursal() {
        System.out.println("\n--- Asignar Artículo a Sucursal ---");
        System.out.println("ID de la sucursal: ");
        int idSucursal = leerEntero(scanner);
        System.out.println("ID del artículo: ");
        int idArticulo = leerEntero(scanner);
        System.out.println("Precio: ");
        int precio = leerEntero(scanner);
        System.out.println("Stock inicial: ");
        int stock = leerEntero(scanner);

        String result = sucursalArticuloController.asignarArticuloASucursal(idSucursal, idArticulo, precio, stock);
        System.out.println(result);
    }

    public void modificarSucursalArticulo() {
        System.out.println("\n--- Modificar Sucursal-Articulo ---");
        System.out.println("ID de la sucursal: ");
        int idSucursal = leerEntero(scanner);
        System.out.println("ID del artículo: ");
        int idArticulo = leerEntero(scanner);
        System.out.println("!!SI NO DESEA MODIFICAR UN CAMPO, SOLO PRESIONE ENTER!!");
        System.out.println("Nuevo stock: ");
        int nuevoStock = leerEntero(scanner);
        System.out.println("Nuevo precio: ");
        int nuevoPrecio = leerEntero(scanner);

        String result = sucursalArticuloController.modificarSucursalArticulo(idSucursal, idArticulo, nuevoStock, nuevoPrecio);
        System.out.println(result);
    }

    public void eliminarArticuloDeSucursal() {
        System.out.println("\n--- Eliminar Artículo de Sucursal ---");
        System.out.println("ID de la sucursal: ");
        int idSucursal = leerEntero(scanner);
        System.out.println("ID del artículo: ");
        int idArticulo = leerEntero(scanner);

        String result = sucursalArticuloController.eliminarArticuloDeSucursal(idSucursal, idArticulo);
        System.out.println(result);
    }

    public void consultarArticulosPorSucursal() {
        System.out.println("\n--- Artículos por Sucursal ---");
        System.out.println("ID de la sucursal: ");
        int idSucursal = leerEntero(scanner);

        List<SucursalArticulo> sucursalArticulos = sucursalArticuloController.consultarArticulosPorSucursal(idSucursal);

        if ( sucursalArticulos.isEmpty() ) {
            System.out.println("No se encontraron artículos para esta sucursal.");
        } else {
            System.out.println("\n--- Artículos en la Sucursal ---");
            for ( SucursalArticulo sa : sucursalArticulos ) {
                System.out.println("Artículo ID: " + sa.getArticulo().getIdArticulo() +
                        " | Descripción: " + sa.getArticulo().getDescripcion() +
                        " | Precio: $" + sa.getPrecio() +
                        " | Stock: " + sa.getStock());
            }
        }
    }

    public void consultarSucursalesPorArticulo() {
        System.out.println("\n--- Sucursales por Artículo ---");
        System.out.println("ID del artículo: ");
        int idArticulo = leerEntero(scanner);

        List<SucursalArticulo> sucursalArticulos = sucursalArticuloController.consultarSucursalesPorArticulo(idArticulo);

        if ( sucursalArticulos.isEmpty() ) {
            System.out.println("No se encontraron sucursales para este artículo.");
        } else {
            System.out.println("\n--- Sucursales con el Artículo ---");
            for ( SucursalArticulo sa : sucursalArticulos ) {
                System.out.println("Sucursal ID: " + sa.getSucursal().getIdSucursal() +
                        " | Descripción: " + sa.getSucursal().getDescripcion() +
                        " | Precio: $" + sa.getPrecio() +
                        " | Stock: " + sa.getStock());
            }
        }
    }
}
