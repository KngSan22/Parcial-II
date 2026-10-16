package Modulos;

public class Operador {
    // Atributos
    private String identificacion, nombreCompleto, certificacion;
    private int aniosExperiencia;

    public Operador(String identificacion, String nombreCompleto, String certificacion, int aniosExperiencia) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.certificacion = certificacion;
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "identificacion='" + identificacion + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", certificacion='" + certificacion + '\'' +
                ", aniosExperiencia=" + aniosExperiencia +
                '}';
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}