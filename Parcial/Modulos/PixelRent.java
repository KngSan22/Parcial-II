package Modulos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PixelRent {

    //Relaciones
    private List<Sede> sedes;
    private List<Cliente> clientes;
    private List<Operador> operadores;
    private List<Reserva> reservas;

    // Constructor
    public PixelRent() {
        this.sedes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.operadores = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // REGISTROS

    // Metodo para registrar un equipo en una sede (no se permiten dos equipos con el mismo codigo)
    public boolean registrarEquipo(Sede sede, Equipo equipo) {
        if (sede == null || equipo == null) {
            return false;
        }
        if (buscarSede(sede.getCodigo()) == null) {
            return false;
        }
        if (buscarEquipo(equipo.getCodigo()) != null) {
            return false;
        }
        return sede.registrarEquipo(equipo);
    }

    // Metodo para registrar un cliente
    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        if (buscarCliente(cliente.getIdentificacion()) != null) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    // Metodo para registrar un operador
    public boolean registrarOperador(Operador operador) {
        if (operador == null) {
            return false;
        }
        if (buscarOperador(operador.getIdentificacion()) != null) {
            return false;
        }
        operadores.add(operador);
        return true;
    }

    // Metodo para registrar una reserva (no se permiten dos reservas con el mismo codigo)
    public boolean registrarReserva(Reserva reserva) {
        if (reserva == null) {
            return false;
        }
        if (buscarReserva(reserva.getCodigo()) != null) {
            return false;
        }
        if (reserva.getCliente() == null || buscarCliente(reserva.getCliente().getIdentificacion()) == null) {
            return false;
        }
        if (reserva.getEstado() != EstadoReserva.SOLICITADA) {
            return false;
        }
        reservas.add(reserva);
        return true;
    }

    // CONSULTAS

    /** Metodo para consultar los equipos disponibles en una fecha y turno
     @return lista de equipos que se pueden utilizar y no estan en una reserva confirmada en esa fecha y turno */
    public List<Equipo> consultarDisponibles(LocalDate fecha, Turno turno) {
        List<Equipo> disponibles = new ArrayList<>();
        for (int i = 0; i < sedes.size(); i++) {
            List<Equipo> equipos = sedes.get(i).getEquipos();
            for (int j = 0; j < equipos.size(); j++) {
                Equipo e = equipos.get(j);
                if (e.estaDisponible() && !estaOcupado(e, fecha, turno)) {
                    disponibles.add(e);
                }
            }
        }
        return disponibles;
    }

    // Metodo para consultar las reservas confirmadas de un cliente
    public List<Reserva> consultarReservasConfirmadas(String identificacion) {
        List<Reserva> confirmadas = new ArrayList<>();
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getEstado() == EstadoReserva.CONFIRMADA
                    && r.getCliente().getIdentificacion().equalsIgnoreCase(identificacion)) {
                confirmadas.add(r);
            }
        }
        return confirmadas;
    }

    // Metodo para consultar el valor total de una reserva
    public double consultarValorReserva(String codigoReserva) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva == null) {
            return -1;
        }
        return reserva.calcularTotal();
    }

    // GESTION DE RESERVAS

    //Metodo para agregar un equipo a una reserva
    public boolean agregarEquipoReserva(String codigoReserva, Equipo equipo, int horas) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva == null || equipo == null) {
            return false;
        }
        if (buscarEquipo(equipo.getCodigo()) == null) {
            return false;
        }
        return reserva.agregarEquipo(equipo, horas);
    }

    // Metodo para asignar un operador a una reserva
    public boolean asignarOperadorReserva(String codigoReserva, Operador operador) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva == null || operador == null) {
            return false;
        }
        if (buscarOperador(operador.getIdentificacion()) == null) {
            return false;
        }
        return reserva.asignarOperador(operador);
    }

    // Metodo para cambiar el estado de una reserva respetando las condiciones del negocio
    public boolean cambiarEstadoReserva(String codigoReserva, EstadoReserva estado) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva == null || estado == null) {
            return false;
        }
        if (estado == EstadoReserva.CONFIRMADA) {
            return reserva.confirmarReserva(reservas);
        }
        return reserva.cambiarEstado(estado);
    }

    // BUSQUEDAS

    private boolean estaOcupado(Equipo equipo, LocalDate fecha, Turno turno) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getEstado() == EstadoReserva.CONFIRMADA
                    && r.getFecha().equals(fecha)
                    && r.getTurno() == turno
                    && r.contieneEquipo(equipo)) {
                return true;
            }
        }
        return false;
    }

    public Sede buscarSede(String codigo) {
        for (int i = 0; i < sedes.size(); i++) {
            Sede s = sedes.get(i);
            if (s.getCodigo().equalsIgnoreCase(codigo)) {
                return s;
            }
        }
        return null;
    }

    public Equipo buscarEquipo(String codigo) {
        for (int i = 0; i < sedes.size(); i++) {
            List<Equipo> equipos = sedes.get(i).getEquipos();
            for (int j = 0; j < equipos.size(); j++) {
                Equipo e = equipos.get(j);
                if (e.getCodigo().equalsIgnoreCase(codigo)) {
                    return e;
                }
            }
        }
        return null;
    }

    public Cliente buscarCliente(String identificacion) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return c;
            }
        }
        return null;
    }

    public Operador buscarOperador(String identificacion) {
        for (int i = 0; i < operadores.size(); i++) {
            Operador o = operadores.get(i);
            if (o.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return o;
            }
        }
        return null;
    }

    public Reserva buscarReserva(String codigo) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getCodigo().equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "PixelRent{" +
                "sedes=" + sedes.size() +
                ", clientes=" + clientes.size() +
                ", operadores=" + operadores.size() +
                ", reservas=" + reservas.size() +
                '}';
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Operador> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<Operador> operadores) {
        this.operadores = operadores;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}