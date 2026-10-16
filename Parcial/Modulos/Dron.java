package Modulos;

public class Dron extends Equipo {
    // Atributos
    private int autonomiaMinutos;
    private boolean bateriaAdicional;

    public Dron(String codigo, String marca, String modelo, CondicionEquipo condicion, int autonomiaMinutos, boolean bateriaAdicional) {
        super(codigo, marca, modelo, condicion);
        this.autonomiaMinutos = autonomiaMinutos;
        this.bateriaAdicional = bateriaAdicional;
    }

    @Override
    public double calcularCosto(int horas) {
        double costo = horas * 18000;
        if (bateriaAdicional) {
            costo = costo + 20000;
        }
        return costo;
    }

    @Override
    public String toString() {
        return "Dron{" +
                "codigo='" + getCodigo() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", condicion=" + getCondicion() +
                ", autonomiaMinutos=" + autonomiaMinutos +
                ", bateriaAdicional=" + bateriaAdicional +
                '}';
    }

    public int getAutonomiaMinutos() {
        return autonomiaMinutos;
    }

    public void setAutonomiaMinutos(int autonomiaMinutos) {
        this.autonomiaMinutos = autonomiaMinutos;
    }

    public boolean isBateriaAdicional() {
        return bateriaAdicional;
    }

    public void setBateriaAdicional(boolean bateriaAdicional) {
        this.bateriaAdicional = bateriaAdicional;
    }
}