package com.unluki.views;

import com.unluki.controllers.ArticuloController;
import com.unluki.models.Articulo;

import java.util.List;
import java.util.Scanner;

import static com.unluki.utils.InputUtil.leerEntero;

public class ArticuloView {
    private final Scanner scanner;
    private final ArticuloController articuloController;

    public ArticuloView() {
        this.scanner = new Scanner(System.in);
        this.articuloController = new ArticuloController();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- ABM y Consulta de Articulos ---");
            System.out.println("1. Agregar articulo");
            System.out.println("2. Eliminar articulo");
            System.out.println("3. Modificar articulo");
            System.out.println("4. Buscar articulo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    agregarArticulo();
                    break;
                case 2:
                    eliminarArticulo();
                    break;
                case 3:
                    modificarArticulo();
                    break;
                case 4:
                    buscarArticulo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while ( opcion != 0 );
    }

    public void agregarArticulo() {
        System.out.println("\n--- Agregar Articulo ---");
        System.out.println("Descripcion: ");
        String descripcion = scanner.nextLine();
        String result = articuloController.createArticulo(descripcion);
        System.out.println(result);
    }

    public void eliminarArticulo() {
        System.out.println("\n--- Eliminar Articulo ---");
        System.out.println("ID del articulo: ");
        int idArticulo = leerEntero(scanner);
        String result = articuloController.eliminarArticulo(idArticulo);
        System.out.println(result);
    }

    public void modificarArticulo() {
        System.out.println("\n--- Modificar Articulo ---");
        System.out.println("ID del articulo: ");
        int idArticulo = leerEntero(scanner);
        System.out.println("!!SI NO DESEA MODIFICAR UN CAMPO, SOLO PRESIONE ENTER!!");
        System.out.println("Descripcion: ");
        String descripcion = scanner.nextLine();
        String result = articuloController.modificarArticulo(idArticulo, descripcion);
        System.out.println(result);
    }

    public void buscarArticulo() {
        System.out.println("\n--- Buscar Articulo ---");
        System.out.println("!!SI DESEA VER TODOS LOS ARTICULOS, SOLO PRESIONE ENTER!!");
        System.out.println("ID del articulo: ");
        int idArticulo = leerEntero(scanner);
        List<Articulo> result = articuloController.consultarArticulo(idArticulo);
        
        if ( result.isEmpty() ) {
            if ( idArticulo == -1 ) {
                System.out.println("No hay artículos registrados.");
            } else {
                System.out.println("No se encontró el artículo con ID: " + idArticulo);
            }
        } else {
            if ( idArticulo == -1 ) {
                System.out.println("\n--- Todos los Artículos ---");
            } else {
                System.out.println("\n--- Artículo Encontrado ---");
            }
            for ( Articulo articulo : result ) {
                System.out.println(articulo.toString());
            }
        }
    }

}
