package Modulos;

public class KitIluminacion extends Equipo {
    // Atributos
    private int cantidadLuces;
    private boolean controlInalambrico;

    public KitIluminacion(String codigo, String marca, String modelo, CondicionEquipo condicion, int cantidadLuces, boolean controlInalambrico) {
        super(codigo, marca, modelo, condicion);
        this.cantidadLuces = cantidadLuces;
        this.controlInalambrico = controlInalambrico;
    }

    @Override
    public double calcularCosto(int horas) {
        double costo = horas * 10000;
        if (controlInalambrico) {
            costo = costo + 12000;
        }
        return costo;
    }

    @Override
    public String toString() {
        return "KitIluminacion{" +
                "codigo='" + getCodigo() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", condicion=" + getCondicion() +
                ", cantidadLuces=" + cantidadLuces +
                ", controlInalambrico=" + controlInalambrico +
                '}';
    }

    public int getCantidadLuces() {
        return cantidadLuces;
    }

    public void setCantidadLuces(int cantidadLuces) {
        this.cantidadLuces = cantidadLuces;
    }

    public boolean isControlInalambrico() {
        return controlInalambrico;
    }

    public void setControlInalambrico(boolean controlInalambrico) {
        this.controlInalambrico = controlInalambrico;
    }
}