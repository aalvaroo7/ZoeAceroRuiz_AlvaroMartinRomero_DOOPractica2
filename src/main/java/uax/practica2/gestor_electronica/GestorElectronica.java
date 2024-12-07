package uax.practica2.gestor_electronica;
import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.marca.Marca;
import uax.practica2.movil.Movil;
import uax.practica2.televisor.Televisor;

import java.util.*;

public class GestorElectronica {
    private List<Marca> marcas = new ArrayList<>();
    private List<ArticuloElectronico> articulos = new ArrayList<>();

    // Métodos para añadir y buscar marcas/artículos
    public void añadirMarca(Marca marca) {
        marcas.add(marca);
    }

    public void añadirTelevisor(String nombreMarca, Televisor televisor) {
        Marca marca = buscarMarca(nombreMarca);
        if (marca != null) {
            articulos.add(televisor);
        } else {
            System.out.println("Marca no encontrada.");
        }
    }

    public void añadirMovil(String nombreMarca, Movil movil) {
        Marca marca = buscarMarca(nombreMarca);
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
}