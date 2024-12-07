package uax.practica2.movil;
import uax.practica2.articulo_electronico.ArticuloElectronico;
import uax.practica2.marca.Marca;

public class Movil extends ArticuloElectronico {
    private int ramGb;
    private String sistemaOperativo;

    public Movil(double precio, Marca marca, String nombre, int ramGb, String sistemaOperativo) {
        super(precio, marca, nombre);
        if (!sistemaOperativo.equalsIgnoreCase("android") && !sistemaOperativo.equalsIgnoreCase("ios")) {
            throw new IllegalArgumentException("Sistema operativo no válido. Debe ser 'Android' o 'iOS'.");
        }
        this.ramGb = ramGb;
        this.sistemaOperativo = sistemaOperativo;
    }

    // Getters y Setters
    public int getRamGb() { return ramGb; }
    public void setRamGb(int ramGb) { this.ramGb = ramGb; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) {
        if (!sistemaOperativo.equalsIgnoreCase("android") && !sistemaOperativo.equalsIgnoreCase("ios")) {
            throw new IllegalArgumentException("Sistema operativo no válido. Debe ser 'Android' o 'iOS'.");
        }
        this.sistemaOperativo = sistemaOperativo;
    }
}