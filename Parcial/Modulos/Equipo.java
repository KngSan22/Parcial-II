package Modulos;

public abstract class Equipo {
    // Atributos
    private String codigo, marca, modelo;
    private CondicionEquipo condicion;

    public Equipo(String codigo, String marca, String modelo, CondicionEquipo condicion) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.condicion = condicion;
    }

    public boolean estaDisponible() {
        return condicion == CondicionEquipo.DISPONIBLE;
    }

    public abstract double calcularCosto(int horas);

    @Override
    public String toString() {
        return "Equipo {" +
                " codigo = '" + codigo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", condicion= " + condicion +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public CondicionEquipo getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionEquipo condicion) {
        this.condicion = condicion;
    }
}