package uax.practica2.marca;

public class Marca {
    private String nombre;
    private String pais;
    private double facturacion;

    // Constructor, getters y setters
    public Marca(String nombre, String pais, double facturacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.facturacion = facturacion;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public double getFacturacion() { return facturacion; }
    public void setFacturacion(double facturacion) { this.facturacion = facturacion; }
}

