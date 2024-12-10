package uax.practica2.articulo_electronico;
import uax.practica2.marca.Marca;

public abstract class ArticuloElectronico implements Comparable<ArticuloElectronico> {
    protected double precio;
    protected Marca marca;
    protected String nombre;

    public ArticuloElectronico(double precio, Marca marca, String nombre) {
        this.precio = precio;
        this.marca = marca;
        this.nombre = nombre;
    }

    // Getters y Setters
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public int compareTo(ArticuloElectronico articulo) {
        int compararMarcas = this.marca.getNombre().compareTo(articulo.getMarca().getNombre());

        if (compararMarcas != 0)
            return compararMarcas;
        else if (this.precio > articulo.getPrecio())
            return 1;
        else if (this.precio < articulo.getPrecio())
            return -1;
        else
            return this.nombre.compareTo(articulo.getNombre());
    }
}

