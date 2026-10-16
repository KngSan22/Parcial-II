package Modulos;

public class Cliente {
    //Atributos (no se pueden modificar despues de registrar al cliente)
    private final String nombreCompleto;
    private final String identificacion;
    private final String telefono;
    private final String correo;

    public Cliente(String nombreCompleto, String identificacion, String telefono, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}