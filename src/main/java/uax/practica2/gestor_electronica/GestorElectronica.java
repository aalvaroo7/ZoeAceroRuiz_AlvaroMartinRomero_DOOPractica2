package uax.practica2.gestor_electronica;
import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.marca.Marca;
import uax.practica2.movil.Movil;
import uax.practica2.televisor.Televisor;

import java.util.*;
import java.util.function.Predicate;

public class GestorElectronica {
    private List<Marca> marcas = new ArrayList<>();
    private List<ArticuloElectronico> articulos = new ArrayList<>();

    // Métodos para añadir y buscar marcas/artículos
    public void añadirMarca(Marca marca) {
        marcas.add(marca);
    }

    public void añadirTelevisor(String nombreMarca, Televisor televisor) {
        Marca marca = buscarMarca(nombreMarca.toLowerCase());
        if (marca != null) {
            articulos.add(televisor);
        } else {
            System.out.println("Marca no encontrada.");
        }
    }

    public void añadirMovil(String nombreMarca, Movil movil) {
        Marca marca = buscarMarca(nombreMarca.toLowerCase());
        if (marca != null) {
            articulos.add(movil);
        } else {
            System.out.println("Marca no encontrada.");
        }
    }

    public Marca buscarMarca(String nombre) {
        for (Marca m : marcas) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }

    public List<ArticuloElectronico> getArticulos() {
        return articulos;
    }

    public void listarMarcas() {
        marcas.sort(Comparator.comparingDouble(Marca::getFacturacion));
        for (Marca marca : marcas) {
            System.out.println(marca.getNombre() + " - " + marca.getPais() + " - " + marca.getFacturacion());
        }
    }

    public void listarArticulos() {
        articulos.sort(Comparator.comparing(a -> a.getMarca().getNombre()));
        for (ArticuloElectronico articulo : articulos) {
            System.out.println(articulo.getNombre() + " - " + articulo.getMarca().getNombre() + " - " + articulo.getPrecio());
        }
    }

    public void buscarTelevisor(String nombreMarca, String nombreArticulo, int pulgadas, String tipoPantalla, double precioDesde, double precioHasta) {
        List<Predicate<Televisor>> todosPredicados = new ArrayList<>();

        if (!nombreMarca.isEmpty())
            todosPredicados.add(t -> t.getMarca().getNombre().equalsIgnoreCase(nombreMarca));
        if (!nombreArticulo.isEmpty())
            todosPredicados.add(t -> t.getNombre().equalsIgnoreCase(nombreArticulo));
        if (pulgadas != 0)
            todosPredicados.add(t -> t.getTamanioPulgadas() == pulgadas);
        if (tipoPantalla != null && !tipoPantalla.isEmpty())
            todosPredicados.add(t -> t.getTipoPantalla().equalsIgnoreCase(tipoPantalla));
        if (precioDesde != 0.0)
            todosPredicados.add(t -> t.getPrecio() >= precioDesde);
        if (precioHasta != 0.0)
            todosPredicados.add(t -> t.getPrecio() <= precioHasta);

        Predicate<Televisor> compositePredicate = todosPredicados.stream().reduce(t -> true, Predicate::and);

        articulos.stream()
                .filter(a -> a instanceof Televisor)
                .map(a -> (Televisor) a)
                .filter(compositePredicate)
                .forEach(t -> System.out.println(t.toString()));
}
}