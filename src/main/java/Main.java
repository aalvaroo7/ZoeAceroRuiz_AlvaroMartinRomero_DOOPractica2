import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Arrays;
import java.util.List;

import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.gestor_electronica.GestorElectronica;
import uax.practica2.marca.Marca;
import uax.practica2.movil.Movil;
import uax.practica2.televisor.Televisor;

public class Main {
    private static final List<String> MARCAS_TELEVISOR = Arrays.asList(
            "samsung", "lg", "sony", "panasonic", "philips", "tcl", "hisense", "vizio", "sharp", "thomson",
            "xiaomi", "realme", "insignia", "sceptre", "jvc", "bang & olufsen (b&o)", "loewe", "grundig",
            "toshiba", "haier", "element", "skyworth", "seiki"
    );

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
                        String nombreMarca = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el país de la marca: ");
                        String paisMarca = scanner.nextLine();
                        System.out.print("Ingrese la facturación de la marca (puede incluir caracteres monetarios): ");
                        String facturacionStr = scanner.nextLine();
                        double facturacionMarca = parseFacturacion(facturacionStr);
                        Marca marca = new Marca(nombreMarca, paisMarca, facturacionMarca);
                        gestor.añadirMarca(marca);
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre de la marca del televisor: ");
                        String nombreMarcaTelevisor = scanner.nextLine().toLowerCase();
                        Marca marcaTelevisor = gestor.buscarMarca(nombreMarcaTelevisor);
                        if (marcaTelevisor == null) {
                            System.out.println("Marca no encontrada. Por favor, añada la marca primero.");
                            break;
                        }
                        System.out.print("Ingrese el nombre del televisor: ");
                        String nombreTelevisor = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el precio del televisor (puede incluir caracteres monetarios): ");
                        String precioTelevisorStr = scanner.nextLine();
                        double precioTelevisor = parseFacturacion(precioTelevisorStr);
                        System.out.print("Ingrese el tamaño en pulgadas del televisor: ");
                        int tamanioPulgadas = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        String tipoPantalla;
                        while (true) {
                            System.out.print("Ingrese el tipo de pantalla del televisor (LED, QLED, OLED, QNED): ");
                            tipoPantalla = scanner.nextLine();
                            if (tipoPantalla.equalsIgnoreCase("led") || tipoPantalla.equalsIgnoreCase("qled") ||
                                    tipoPantalla.equalsIgnoreCase("oled") || tipoPantalla.equalsIgnoreCase("qned")) {
                                break;
                            } else {
                                System.out.println("Tipo de pantalla no válido. Debe ser 'LED', 'QLED', 'OLED' o 'QNED'.");
                            }
                        }

                        Televisor televisor = new Televisor(precioTelevisor, marcaTelevisor, nombreTelevisor, tamanioPulgadas, tipoPantalla);
                        gestor.añadirTelevisor(nombreMarcaTelevisor, televisor);
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de la marca del móvil: ");
                        String nombreMarcaMovil = scanner.nextLine().toLowerCase();
                        Marca marcaMovil = gestor.buscarMarca(nombreMarcaMovil);
                        if (marcaMovil == null) {
                            System.out.print("Ingrese el país de la marca: ");
                            String paisMarcaMovil = scanner.nextLine();
                            System.out.print("Ingrese la facturación de la marca (puede incluir caracteres monetarios): ");
                            String facturacionStrMovil = scanner.nextLine();
                            double facturacionMarcaMovil = parseFacturacion(facturacionStrMovil);
                            marcaMovil = new Marca(nombreMarcaMovil, paisMarcaMovil, facturacionMarcaMovil);
                            gestor.añadirMarca(marcaMovil);
                        }
                        System.out.print("Ingrese el nombre del móvil: ");
                        String nombreMovil = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el precio del móvil (puede incluir caracteres monetarios): ");
                        String precioMovilStr = scanner.nextLine();
                        double precioMovil = parseFacturacion(precioMovilStr);
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
                        String nombreMarcaBuscar = scanner.nextLine().toLowerCase();
                        Marca marcaBuscar = gestor.buscarMarca(nombreMarcaBuscar);
                        if (marcaBuscar != null) {
                            System.out.println("Marca encontrada: " + marcaBuscar.getNombre() + " - " + marcaBuscar.getPais() + " - " + marcaBuscar.getFacturacion());
                        } else {
                            System.out.println("Marca no encontrada.");
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el nombre de la marca del televisor: ");
                        String nombreMarcaBuscarTelevisor = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el nombre del televisor: ");
                        String nombreTelevisorBuscar = scanner.nextLine().toLowerCase();
                        System.out.print("Ingrese el tamaño en pulgadas del televisor (0 para ignorar): ");
                        int pulgadasBuscar = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese el tipo de pantalla del televisor (deje en blanco para ignorar): ");
                        String tipoPantallaBuscar = scanner.nextLine();
                        System.out.print("Ingrese el precio mínimo del televisor (0 para ignorar): ");
                        double precioDesdeBuscar = scanner.nextDouble();
                        System.out.print("Ingrese el precio máximo del televisor (0 para ignorar): ");
                        double precioHastaBuscar = scanner.nextDouble();
                        scanner.nextLine(); // Consumir el salto de línea
                        gestor.buscarTelevisor(nombreMarcaBuscarTelevisor, nombreTelevisorBuscar, pulgadasBuscar, tipoPantallaBuscar, precioDesdeBuscar, precioHastaBuscar);
                        break;
                    case 6:
                        System.out.print("Ingrese el nombre del móvil: ");
                        String nombreMovilBuscar = scanner.nextLine().toLowerCase();
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

    private static double parseFacturacion(String facturacionStr) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(facturacionStr.replaceAll("[^\\d,\\.]", ""));
            return number.doubleValue();
        } catch (ParseException e) {
            System.out.println("Error al parsear la facturación. Usando valor por defecto 0.");
            return 0;
}
}
}