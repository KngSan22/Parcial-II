package Modulos;

public class Camara extends Equipo {

    // Atributos
    private String resolucionMaxima;
    private boolean estabilizador;

    public Camara(String codigo, String marca, String modelo, CondicionEquipo condicion, String resolucionMaxima, boolean estabilizador) {
        super(codigo, marca, modelo, condicion);
        this.resolucionMaxima = resolucionMaxima;
        this.estabilizador = estabilizador;
    }

    @Override
    public double calcularCosto(int horas) {
        double costo = horas * 12000;
        if (estabilizador) {
            costo = costo + 15000;
        }
        return costo;
    }

    @Override
    public String toString() {
        return "Camara{" +
                "codigo='" + getCodigo() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", condicion=" + getCondicion() +
                ", resolucionMaxima='" + resolucionMaxima + '\'' +
                ", estabilizador=" + estabilizador +
                '}';
    }

    public String getResolucionMaxima() {
        return resolucionMaxima;
    }

    public void setResolucionMaxima(String resolucionMaxima) {
        this.resolucionMaxima = resolucionMaxima;
    }

    public boolean isEstabilizador() {
        return estabilizador;
    }

    public void setEstabilizador(boolean estabilizador) {
        this.estabilizador = estabilizador;
    }
}