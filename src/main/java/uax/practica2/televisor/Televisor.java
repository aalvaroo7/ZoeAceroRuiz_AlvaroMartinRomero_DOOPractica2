package uax.practica2.televisor;
import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.marca.Marca;

public class Televisor extends ArticuloElectronico {
    private int tamanioPulgadas;
    private String tipoPantalla;

    public Televisor(double precio, Marca marca, String nombre, int tamanioPulgadas, String tipoPantalla) {
        super(precio, marca, nombre);
        this.tamanioPulgadas = tamanioPulgadas;
        this.tipoPantalla = tipoPantalla;
    }

    // Getters y Setters
    public int getTamanioPulgadas() { return tamanioPulgadas; }
    public void setTamanioPulgadas(int tamanioPulgadas) { this.tamanioPulgadas = tamanioPulgadas; }
    public String getTipoPantalla() { return tipoPantalla; }
    public void setTipoPantalla(String tipoPantalla) { this.tipoPantalla = tipoPantalla; }
}

