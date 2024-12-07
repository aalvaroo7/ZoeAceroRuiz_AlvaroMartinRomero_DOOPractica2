import java.util.InputMismatchException;
import java.util.Scanner;
import uax.practica2.gestor_electronica.GestorElectronica;
import uax.practica2.marca.Marca;
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
            System.out.println("3. Listar Marcas");
            System.out.println("4. Listar Artículos");
            System.out.println("5. Salir");
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
                        System.out.print("Ingrese el nombre del televisor: ");
                        String nombreTelevisor = scanner.nextLine();
                        System.out.print("Ingrese el precio del televisor: ");
                        double precioTelevisor = scanner.nextDouble();
                        System.out.print("Ingrese el tamaño en pulgadas del televisor: ");
                        int tamanioPulgadas = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese el tipo de pantalla del televisor: ");
                        String tipoPantalla = scanner.nextLine();
                        Televisor televisor = new Televisor(precioTelevisor, gestor.buscarMarca(nombreMarcaTelevisor), nombreTelevisor, tamanioPulgadas, tipoPantalla);
                        gestor.añadirTelevisor(nombreMarcaTelevisor, televisor);
                        break;
                    case 3:
                        gestor.listarMarcas();
                        break;
                    case 4:
                        gestor.listarArticulos();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (opcion != 5);

        scanner.close();
    }
}
