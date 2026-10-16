package Modulos;

public record DetalleReserva(Equipo equipo, int horas) {

    public double calcularSubtotal() {
        return equipo.calcularCosto(horas);
    }
}