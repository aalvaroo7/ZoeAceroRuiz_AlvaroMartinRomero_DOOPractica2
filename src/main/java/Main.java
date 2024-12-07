import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.gestor_electronica.GestorElectronica;
import uax.practica2.marca.Marca;
import uax.practica2.movil.Movil;
import uax.practica2.televisor.Televisor;

public class Main {
    public static void main(String[] args) {
        GestorElectronica gestor = new GestorElectronica();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Menú de Gestión de Electrónica:");
            System.out.println("1. Añadir Marca");
            System.out.println("2. Añadir Televisor");
            System.out.println("3. Añadir Móvil");
            System.out.println("4. Buscar Marca");
            System.out.println("5. Buscar Televisor");
            System.out.println("6. Buscar Móvil");
            System.out.println("7. Listar Marcas");
            System.out.println("8. Listar Artículos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre de la marca: ");
                        String nombreMarca = scanner.nextLine();
                        System.out.print("Ingrese el país de la marca: ");
                        String paisMarca = scanner.nextLine();
                        System.out.print("Ingrese la facturación de la marca: ");
                        double facturacionMarca = scanner.nextDouble();
                        scanner.nextLine(); // Consumir el salto de línea
                        Marca marca = new Marca(nombreMarca, paisMarca, facturacionMarca);
                        gestor.añadirMarca(marca);
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre de la marca del televisor: ");
                        String nombreMarcaTelevisor = scanner.nextLine();
                        Marca marcaTelevisor = gestor.buscarMarca(nombreMarcaTelevisor);
                        if (marcaTelevisor == null) {
                            System.out.println("Marca no encontrada.");
                            break;
                        }
                        System.out.print("Ingrese el nombre del televisor: ");
                        String nombreTelevisor = scanner.nextLine();
                        System.out.print("Ingrese el precio del televisor: ");
                        double precioTelevisor = scanner.nextDouble();
                        System.out.print("Ingrese el tamaño en pulgadas del televisor: ");
                        int tamanioPulgadas = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese el tipo de pantalla del televisor: ");
                        String tipoPantalla = scanner.nextLine();
                        Televisor televisor = new Televisor(precioTelevisor, marcaTelevisor, nombreTelevisor, tamanioPulgadas, tipoPantalla);
                        gestor.añadirTelevisor(nombreMarcaTelevisor, televisor);
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de la marca del móvil: ");
                        String nombreMarcaMovil = scanner.nextLine();
                        Marca marcaMovil = gestor.buscarMarca(nombreMarcaMovil);
                        if (marcaMovil == null) {
                            System.out.println("Marca no encontrada.");
                            break;
                        }
                        System.out.print("Ingrese el nombre del móvil: ");
                        String nombreMovil = scanner.nextLine();
                        System.out.print("Ingrese el precio del móvil: ");
                        double precioMovil = scanner.nextDouble();
                        System.out.print("Ingrese el tamaño en GB de la RAM: ");
                        int ramGb = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese el sistema operativo del móvil: ");
                        String sistemaOperativo = scanner.nextLine();
                        Movil movil = new Movil(precioMovil, marcaMovil, nombreMovil, ramGb, sistemaOperativo);
                        gestor.añadirMovil(nombreMarcaMovil, movil);
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre de la marca: ");
                        String nombreMarcaBuscar = scanner.nextLine();
                        Marca marcaBuscar = gestor.buscarMarca(nombreMarcaBuscar);
                        if (marcaBuscar != null) {
                            System.out.println("Marca encontrada: " + marcaBuscar.getNombre() + " - " + marcaBuscar.getPais() + " - " + marcaBuscar.getFacturacion());
                        } else {
                            System.out.println("Marca no encontrada.");
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el nombre del televisor: ");
                        String nombreTelevisorBuscar = scanner.nextLine();
                        Optional<ArticuloElectronico> televisorBuscar = gestor.getArticulos().stream()
                                .filter(articulo -> articulo instanceof Televisor && articulo.getNombre().equalsIgnoreCase(nombreTelevisorBuscar))
                                .findFirst();
                        if (televisorBuscar.isPresent()) {
                            Televisor televisorEncontrado = (Televisor) televisorBuscar.get();
                            System.out.println("Televisor encontrado: " + televisorEncontrado.getNombre() + " - " + televisorEncontrado.getMarca().getNombre() + " - " + televisorEncontrado.getPrecio());
                        } else {
                            System.out.println("Televisor no encontrado.");
                        }
                        break;
                    case 6:
                        System.out.print("Ingrese el nombre del móvil: ");
                        String nombreMovilBuscar = scanner.nextLine();
                        Optional<ArticuloElectronico> movilBuscar = gestor.getArticulos().stream()
                                .filter(articulo -> articulo instanceof Movil && articulo.getNombre().equalsIgnoreCase(nombreMovilBuscar))
                                .findFirst();
                        if (movilBuscar.isPresent()) {
                            Movil movilEncontrado = (Movil) movilBuscar.get();
                            System.out.println("Móvil encontrado: " + movilEncontrado.getNombre() + " - " + movilEncontrado.getMarca().getNombre() + " - " + movilEncontrado.getPrecio());
                        } else {
                            System.out.println("Móvil no encontrado.");
                        }
                        break;
                    case 7:
                        gestor.listarMarcas();
                        break;
                    case 8:
                        gestor.listarArticulos();
                        break;
                    case 9:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (opcion != 9);

        scanner.close();
    }
}