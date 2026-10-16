package Modulos;

import java.util.ArrayList;
import java.util.List;

public class Sede {
    // Atributos
    private String codigo, nombre, direccion;

    // Relaciones
    private List<Equipo> equipos;

    public Sede(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.equipos = new ArrayList<>();
    }

    public boolean registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            return false;
        }
        for (int i = 0; i < equipos.size(); i++) {
            Equipo e = equipos.get(i);
            if (e.getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                return false;
            }
        }
        equipos.add(equipo);
        return true;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}