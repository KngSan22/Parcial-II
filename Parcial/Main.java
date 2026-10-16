import Modulos.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    // Metodo para convertir un texto en un Turno
    public static Turno convertirTurno(String texto) {
        String t = texto.toUpperCase();
        if (t.equals("MANANA")) {
            return Turno.MANANA;
        } else if (t.equals("TARDE")) {
            return Turno.TARDE;
        } else if (t.equals("NOCHE")) {
            return Turno.NOCHE;
        }
        return null;
    }

    // Metodo para convertir un texto en un EstadoReserva
    public static EstadoReserva convertirEstado(String texto) {
        String e = texto.toUpperCase();
        if (e.equals("SOLICITADA")) {
            return EstadoReserva.SOLICITADA;
        } else if (e.equals("CONFIRMADA")) {
            return EstadoReserva.CONFIRMADA;
        } else if (e.equals("CANCELADA")) {
            return EstadoReserva.CANCELADA;
        } else if (e.equals("FINALIZADA")) {
            return EstadoReserva.FINALIZADA;
        }
        return null;
    }

    // Metodo para convertir un texto en una CondicionEquipo
    public static CondicionEquipo convertirCondicion(String texto) {
        String c = texto.toUpperCase();
        if (c.equals("DISPONIBLE")) {
            return CondicionEquipo.DISPONIBLE;
        } else if (c.equals("MANTENIMIENTO")) {
            return CondicionEquipo.MANTENIMIENTO;
        } else if (c.equals("FUERA_DE_SERVICIO")) {
            return CondicionEquipo.FUERA_DE_SERVICIO;
        }
        return null;
    }

    // ================= METODOS PARA MOSTRAR LA INFORMACION AL USUARIO =================

    // Metodo que arma el texto con las sedes registradas
    public static String listarSedes(PixelRent pixelRent) {
        List<Sede> sedes = pixelRent.getSedes();
        if (sedes.isEmpty()) {
            return "=== SEDES ===\nNo hay sedes registradas.\n";
        }
        String lista = "=== SEDES DISPONIBLES ===\n";
        for (int i = 0; i < sedes.size(); i++) {
            Sede s = sedes.get(i);
            lista = lista + "Código: " + s.getCodigo() + " | Nombre: " + s.getNombre() + " | Dirección: " + s.getDireccion() + "\n";
        }
        return lista;
    }

    // Metodo que arma el texto con todos los equipos de todas las sedes
    public static String listarEquipos(PixelRent pixelRent) {
        List<Sede> sedes = pixelRent.getSedes();
        String lista = "=== EQUIPOS REGISTRADOS ===\n";
        boolean hayEquipos = false;
        for (int i = 0; i < sedes.size(); i++) {
            Sede s = sedes.get(i);
            List<Equipo> equipos = s.getEquipos();
            for (int j = 0; j < equipos.size(); j++) {
                Equipo e = equipos.get(j);
                lista = lista + "Código: " + e.getCodigo() + " | " + e.getClass().getSimpleName() + " | " + e.getMarca() + " " + e.getModelo()
                        + " | Condición: " + e.getCondicion() + " | Sede: " + s.getCodigo() + "\n";
                hayEquipos = true;
            }
        }
        if (!hayEquipos) {
            return "=== EQUIPOS ===\nNo hay equipos registrados.\n";
        }
        return lista;
    }

    // Metodo que arma el texto con los clientes registrados
    public static String listarClientes(PixelRent pixelRent) {
        List<Cliente> clientes = pixelRent.getClientes();
        if (clientes.isEmpty()) {
            return "=== CLIENTES ===\nNo hay clientes registrados.\n";
        }
        String lista = "=== CLIENTES REGISTRADOS ===\n";
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            lista = lista + "Identificación: " + c.getIdentificacion() + " | Nombre: " + c.getNombreCompleto() + "\n";
        }
        return lista;
    }

    // Metodo que arma el texto con los operadores registrados
    public static String listarOperadores(PixelRent pixelRent) {
        List<Operador> operadores = pixelRent.getOperadores();
        if (operadores.isEmpty()) {
            return "=== OPERADORES ===\nNo hay operadores registrados.\n";
        }
        String lista = "=== OPERADORES REGISTRADOS ===\n";
        for (int i = 0; i < operadores.size(); i++) {
            Operador o = operadores.get(i);
            lista = lista + "Identificación: " + o.getIdentificacion() + " | Nombre: " + o.getNombreCompleto() + " | Certificación: " + o.getCertificacion() + "\n";
        }
        return lista;
    }

    // Metodo que arma el texto con las reservas registradas
    public static String listarReservas(PixelRent pixelRent) {
        List<Reserva> reservas = pixelRent.getReservas();
        if (reservas.isEmpty()) {
            return "=== RESERVAS ===\nNo hay reservas registradas.\n";
        }
        String lista = "=== RESERVAS REGISTRADAS ===\n";
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            lista = lista + "Código: " + r.getCodigo() + " | Cliente: " + r.getCliente().getNombreCompleto() + " | Fecha: " + r.getFecha()
                    + " | Turno: " + r.getTurno() + " | Estado: " + r.getEstado() + "\n";
        }
        return lista;
    }

    public static void main(String[] args) {
        PixelRent pixelRent = new PixelRent();

        // Datos iniciales
        // Sedes
        pixelRent.getSedes().add(new Sede("S01", "Sede Centro", "Cra 14 Armenia Quindio"));
        pixelRent.getSedes().add(new Sede("S02", "Sede Norte", "Av Bolivar Armenia Quindio"));
        // Equipo
        pixelRent.registrarEquipo(pixelRent.buscarSede("S01"), new Camara("E01", "Sony", "A7III", CondicionEquipo.DISPONIBLE, "4K", true));
        pixelRent.registrarEquipo(pixelRent.buscarSede("S01"), new Dron("E02", "DJI", "Mavic 3", CondicionEquipo.DISPONIBLE, 40, true));
        pixelRent.registrarEquipo(pixelRent.buscarSede("S02"), new KitIluminacion("E03", "Godox", "SL60", CondicionEquipo.DISPONIBLE, 4, false));
        // Operadores
        pixelRent.registrarOperador(new Operador("98765", "Ana Maria", "Piloto de dron certificado", 5));
        pixelRent.registrarOperador(new Operador("99021", "Juan Diego", "Camarografo certificado", 7));
        pixelRent.registrarOperador(new Operador("15022", "Carlos Mario", "Tecnico de iluminacion", 2));

        boolean continuar = true;
        while (continuar) {
            String menu = "=== MENÚ PRINCIPAL PIXELRENT ===\n" +
                    "1. Registrar\n" +
                    "2. Asignar\n" +
                    "3. Cambiar\n" +
                    "4. Consultar\n" +
                    "5. Salir\n\n" +
                    "Seleccione una opción:";

            String opcionStr = JOptionPane.showInputDialog(null, menu);
            if (opcionStr == null) {
                break;
            }

            int opcion = Integer.parseInt(opcionStr);

            // 1. REGISTRAR
            if (opcion == 1) {
                String subMenu = "--- REGISTRAR ---\n" +
                        "1. Equipo\n" +
                        "2. Cliente\n" +
                        "3. Operador\n" +
                        "4. Reserva\n" +
                        "Seleccione una opción:";
                String subOpStr = JOptionPane.showInputDialog(null, subMenu);
                if (subOpStr != null) {
                    int subOp = Integer.parseInt(subOpStr);

                    if (subOp == 1) {
                        String tipoMenu = "--- TIPO DE EQUIPO ---\n" +
                                "1. Cámara\n" +
                                "2. Dron\n" +
                                "3. Kit de Iluminación\n" +
                                "Seleccione una opción:";
                        String tipoStr = JOptionPane.showInputDialog(null, tipoMenu);
                        if (tipoStr != null) {
                            int tipo = Integer.parseInt(tipoStr);
                            if (tipo < 1 || tipo > 3) {
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                            } else {
                                // Se muestran las sedes y los equipos ya registrados (para no repetir el código del equipo)
                                String codSede = JOptionPane.showInputDialog(listarSedes(pixelRent) + "\nCódigo de la sede:");
                                String codigo = JOptionPane.showInputDialog(listarEquipos(pixelRent) + "\nCódigo del nuevo equipo (no puede repetirse):");
                                String marca = JOptionPane.showInputDialog("Marca:");
                                String modelo = JOptionPane.showInputDialog("Modelo:");
                                CondicionEquipo condicion = convertirCondicion(JOptionPane.showInputDialog("Condición (DISPONIBLE, MANTENIMIENTO, FUERA_DE_SERVICIO):"));
                                Sede sede = pixelRent.buscarSede(codSede);
                                Equipo equipo = null;

                                if (tipo == 1) {
                                    String resolucion = JOptionPane.showInputDialog("Resolución máxima:");
                                    boolean estabilizador = JOptionPane.showInputDialog("¿Tiene estabilizador? (SI/NO):").equalsIgnoreCase("SI");
                                    equipo = new Camara(codigo, marca, modelo, condicion, resolucion, estabilizador);
                                } else if (tipo == 2) {
                                    int autonomia = Integer.parseInt(JOptionPane.showInputDialog("Autonomía de vuelo (minutos):"));
                                    boolean bateria = JOptionPane.showInputDialog("¿Se solicita batería adicional? (SI/NO):").equalsIgnoreCase("SI");
                                    equipo = new Dron(codigo, marca, modelo, condicion, autonomia, bateria);
                                } else if (tipo == 3) {
                                    int luces = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de luces:"));
                                    boolean control = JOptionPane.showInputDialog("¿Tiene control inalámbrico? (SI/NO):").equalsIgnoreCase("SI");
                                    equipo = new KitIluminacion(codigo, marca, modelo, condicion, luces, control);
                                }

                                if (equipo != null && condicion != null && pixelRent.registrarEquipo(sede, equipo)) {
                                    JOptionPane.showMessageDialog(null, "Equipo registrado con éxito.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: Verifique los datos, la sede o si el código ya existe.");
                                }
                            }
                        }
                    } else if (subOp == 2) {
                        // Se muestran los clientes ya registrados (para no repetir la identificación)
                        String nombre = JOptionPane.showInputDialog(listarClientes(pixelRent) + "\nNombre completo del nuevo cliente:");
                        String identificacion = JOptionPane.showInputDialog("Identificación (no puede repetirse):");
                        String telefono = JOptionPane.showInputDialog("Teléfono:");
                        String correo = JOptionPane.showInputDialog("Correo:");

                        boolean exito = pixelRent.registrarCliente(new Cliente(nombre, identificacion, telefono, correo));
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Cliente registrado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Ya existe.");
                        }
                    } else if (subOp == 3) {
                        // Se muestran los operadores ya registrados (para no repetir la identificación)
                        String identificacion = JOptionPane.showInputDialog(listarOperadores(pixelRent) + "\nIdentificación del nuevo operador (no puede repetirse):");
                        String nombre = JOptionPane.showInputDialog("Nombre completo:");
                        String certificacion = JOptionPane.showInputDialog("Certificación:");
                        int anios = Integer.parseInt(JOptionPane.showInputDialog("Años de experiencia:"));

                        boolean exito = pixelRent.registrarOperador(new Operador(identificacion, nombre, certificacion, anios));
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Operador registrado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Ya existe.");
                        }
                    } else if (subOp == 4) {
                        // Se muestran las reservas existentes (para no repetir el código) y los clientes disponibles
                        String codigo = JOptionPane.showInputDialog(listarReservas(pixelRent) + "\nCódigo único de la nueva reserva:");
                        String idCliente = JOptionPane.showInputDialog(listarClientes(pixelRent) + "\nIdentificación del cliente:");
                        LocalDate fecha = LocalDate.parse(JOptionPane.showInputDialog("Fecha (AAAA-MM-DD):"));
                        Turno turno = convertirTurno(JOptionPane.showInputDialog("Turno (MANANA, TARDE, NOCHE):"));
                        Cliente cliente = pixelRent.buscarCliente(idCliente);

                        if (cliente == null || turno == null) {
                            JOptionPane.showMessageDialog(null, "Error: Cliente no encontrado o turno no válido.");
                        } else {
                            boolean exito = pixelRent.registrarReserva(new Reserva(codigo, cliente, fecha, turno));
                            if (exito) {
                                JOptionPane.showMessageDialog(null, "Reserva registrada como SOLICITADA.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error: Ya existe una reserva con ese código.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                }

                // 2. ASIGNAR
            } else if (opcion == 2) {
                String subMenu = "--- ASIGNAR ---\n" +
                        "1. Agregar Equipo a una Reserva\n" +
                        "2. Asignar Operador a una Reserva\n" +
                        "Seleccione una opción:";
                String subOpStr = JOptionPane.showInputDialog(null, subMenu);
                if (subOpStr != null) {
                    int subOp = Integer.parseInt(subOpStr);

                    if (subOp == 1) {
                        // Se muestran las reservas y los equipos para que el usuario elija
                        String codReserva = JOptionPane.showInputDialog(listarReservas(pixelRent) + "\nCódigo de la reserva:");
                        String codEquipo = JOptionPane.showInputDialog(listarEquipos(pixelRent) + "\nCódigo del equipo:");
                        int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas de utilización:"));
                        Equipo equipo = pixelRent.buscarEquipo(codEquipo);

                        boolean exito = pixelRent.agregarEquipoReserva(codReserva, equipo, horas);
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Equipo agregado a la reserva.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Verifique la reserva, el equipo (repetido o no disponible) o las horas.");
                        }
                    } else if (subOp == 2) {
                        // Se muestran las reservas y los operadores para que el usuario elija
                        String codReserva = JOptionPane.showInputDialog(listarReservas(pixelRent) + "\nCódigo de la reserva:");
                        String idOperador = JOptionPane.showInputDialog(listarOperadores(pixelRent) + "\nIdentificación del operador:");
                        Operador operador = pixelRent.buscarOperador(idOperador);

                        boolean exito = pixelRent.asignarOperadorReserva(codReserva, operador);
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Operador asignado a la reserva.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Verifique la reserva, el operador o el estado de la reserva.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                }

                // 3. CAMBIAR
            } else if (opcion == 3) {
                String subMenu = "--- CAMBIAR ---\n" +
                        "1. Estado de una Reserva\n" +
                        "Seleccione una opción:";
                String subOpStr = JOptionPane.showInputDialog(null, subMenu);
                if (subOpStr != null) {
                    int subOp = Integer.parseInt(subOpStr);

                    if (subOp == 1) {
                        // Se muestran las reservas con su estado actual
                        String codReserva = JOptionPane.showInputDialog(listarReservas(pixelRent) + "\nCódigo de la reserva:");
                        EstadoReserva estado = convertirEstado(JOptionPane.showInputDialog("Nuevo estado (SOLICITADA, CONFIRMADA, CANCELADA, FINALIZADA):"));

                        boolean exito = pixelRent.cambiarEstadoReserva(codReserva, estado);
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Estado de la reserva actualizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Las condiciones del negocio no permiten este cambio de estado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                }

                // 4. CONSULTAR
            } else if (opcion == 4) {
                String subMenu = "--- CONSULTAR ---\n" +
                        "1. Equipos Disponibles (fecha y turno)\n" +
                        "2. Reservas Confirmadas de un Cliente\n" +
                        "3. Valor de una Reserva\n" +
                        "4. Ver Sedes\n" +
                        "5. Ver Clientes\n" +
                        "6. Ver Operadores\n" +
                        "7. Ver Equipos\n" +
                        "8. Ver Reservas\n" +
                        "Seleccione una opción:";
                String subOpStr = JOptionPane.showInputDialog(null, subMenu);
                if (subOpStr != null) {
                    int subOp = Integer.parseInt(subOpStr);

                    if (subOp == 1) {
                        LocalDate fecha = LocalDate.parse(JOptionPane.showInputDialog("Fecha (AAAA-MM-DD):"));
                        Turno turno = convertirTurno(JOptionPane.showInputDialog("Turno (MANANA, TARDE, NOCHE):"));

                        if (turno == null) {
                            JOptionPane.showMessageDialog(null, "Error: Turno no válido.");
                        } else {
                            List<Equipo> disponibles = pixelRent.consultarDisponibles(fecha, turno);
                            if (disponibles.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "No hay equipos disponibles para esa fecha y turno.");
                            } else {
                                String lista = "=== EQUIPOS DISPONIBLES ===\n";
                                for (int i = 0; i < disponibles.size(); i++) {
                                    Equipo e = disponibles.get(i);
                                    lista = lista + "Código: " + e.getCodigo() + " | " + e.getClass().getSimpleName() + " | " + e.getMarca() + " " + e.getModelo() + "\n";
                                }
                                JOptionPane.showMessageDialog(null, lista);
                            }
                        }
                    } else if (subOp == 2) {
                        // Se muestran los clientes para que el usuario elija
                        String identificacion = JOptionPane.showInputDialog(listarClientes(pixelRent) + "\nIdentificación del cliente:");
                        List<Reserva> confirmadas = pixelRent.consultarReservasConfirmadas(identificacion);

                        if (confirmadas.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El cliente no tiene reservas confirmadas.");
                        } else {
                            String lista = "=== RESERVAS CONFIRMADAS ===\n";
                            for (int i = 0; i < confirmadas.size(); i++) {
                                lista = lista + confirmadas.get(i) + "\n";
                            }
                            JOptionPane.showMessageDialog(null, lista);
                        }
                    } else if (subOp == 3) {
                        // Se muestran las reservas para que el usuario elija
                        String codReserva = JOptionPane.showInputDialog(listarReservas(pixelRent) + "\nCódigo de la reserva:");
                        double valor = pixelRent.consultarValorReserva(codReserva);

                        if (valor < 0) {
                            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Valor total de la reserva: $" + valor);
                        }
                    } else if (subOp == 4) {
                        JOptionPane.showMessageDialog(null, listarSedes(pixelRent));
                    } else if (subOp == 5) {
                        JOptionPane.showMessageDialog(null, listarClientes(pixelRent));
                    } else if (subOp == 6) {
                        JOptionPane.showMessageDialog(null, listarOperadores(pixelRent));
                    } else if (subOp == 7) {
                        JOptionPane.showMessageDialog(null, listarEquipos(pixelRent));
                    } else if (subOp == 8) {
                        JOptionPane.showMessageDialog(null, listarReservas(pixelRent));
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                }

                // 5. SALIR
            } else if (opcion == 5) {
                continuar = false;
                JOptionPane.showMessageDialog(null, "Saliendo del sistema PixelRent.");
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}