package Modulos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    // Atributos (codigo, cliente, fecha y turno no se modifican)
    private final String codigo;
    private final Cliente cliente;
    private final LocalDate fecha;
    private final Turno turno;
    private EstadoReserva estado;

    // Relaciones
    private Operador operador;
    private List<DetalleReserva> detalles;

    public Reserva(String codigo, Cliente cliente, LocalDate fecha, Turno turno) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.turno = turno;
        this.estado = EstadoReserva.SOLICITADA;
        this.operador = null;
        this.detalles = new ArrayList<>();
    }

    public boolean agregarEquipo(Equipo equipo, int horas) {
        if (equipo == null || horas <= 0) {
            return false;
        }
        if (estado != EstadoReserva.SOLICITADA) {
            return false;
        }
        if (!equipo.estaDisponible()) {
            return false;
        }
        if (contieneEquipo(equipo)) {
            return false;
        }
        detalles.add(new DetalleReserva(equipo, horas));
        return true;
    }

    public boolean asignarOperador(Operador operador) {
        if (operador == null) {
            return false;
        }
        if (estado == EstadoReserva.CANCELADA || estado == EstadoReserva.FINALIZADA) {
            return false;
        }
        this.operador = operador;
        return true;
    }

    /**
     Metodo para confirmar la reserva verificando todas las condiciones del negocio
     @param confirmadas lista de reservas para validar que los equipos no esten ya confirmados en la misma fecha y turno
     return true si la reserva quedo CONFIRMADA, false si no se cumplen las condiciones
     */
    public boolean confirmarReserva(List<Reserva> confirmadas) {
        // Solo una reserva SOLICITADA puede pasar a CONFIRMADA
        if (estado != EstadoReserva.SOLICITADA) {
            return false;
        }
        // Debe tener al menos un equipo
        if (detalles.isEmpty()) {
            return false;
        }
        // Si tiene un dron debe tener operador asignado
        if (contieneDron() && operador == null) {
            return false;
        }
        for (int i = 0; i < detalles.size(); i++) {
            Equipo e = detalles.get(i).equipo();

            // El equipo debe poder ser utilizado
            if (!e.estaDisponible()) {
                return false;
            }

            // El equipo no puede estar en otra reserva confirmada para la misma fecha y turno
            for (int j = 0; j < confirmadas.size(); j++) {
                Reserva otra = confirmadas.get(j);
                if (otra != this
                        && otra.getEstado() == EstadoReserva.CONFIRMADA
                        && otra.getFecha().equals(fecha)
                        && otra.getTurno() == turno
                        && otra.contieneEquipo(e)) {
                    return false;
                }
            }
        }
        estado = EstadoReserva.CONFIRMADA;
        return true;
    }

    /* Metodo para cambiar el estado de la reserva. La confirmacion se hace con confirmarReserva
      return true si el cambio esta permitido, false en caso contrario */
    public boolean cambiarEstado(EstadoReserva nuevoEstado) {
        if (nuevoEstado == null) {
            return false;
        }
        if (estado == EstadoReserva.SOLICITADA && nuevoEstado == EstadoReserva.CANCELADA) {
            estado = nuevoEstado;
            return true;
        }
        if (estado == EstadoReserva.CONFIRMADA && (nuevoEstado == EstadoReserva.CANCELADA || nuevoEstado == EstadoReserva.FINALIZADA)) {
            estado = nuevoEstado;
            return true;
        }
        return false;
    }

    // Metodo para calcular el valor total de la reserva

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < detalles.size(); i++) {
            total = total + detalles.get(i).calcularSubtotal();
        }
        return total;
    }

    // Metodo para saber si la reserva contiene algun dron

    public boolean contieneDron() {
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).equipo() instanceof Dron) {
                return true;
            }
        }
        return false;
    }

    // Metodo para saber si un equipo ya hace parte de la reserva

    public boolean contieneEquipo(Equipo equipo) {
        for (int i = 0; i < detalles.size(); i++) {
            Equipo e = detalles.get(i).equipo();
            if (e.getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigo='" + codigo + '\'' +
                ", cliente=" + cliente.getNombreCompleto() +
                ", fecha=" + fecha +
                ", turno=" + turno +
                ", estado=" + estado +
                ", operador=" + (operador != null ? operador.getNombreCompleto() : "Sin asignar") +
                ", equipos=" + detalles.size() +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Turno getTurno() {
        return turno;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public Operador getOperador() {
        return operador;
    }

    public List<DetalleReserva> getDetalles() {
        return detalles;
    }
}