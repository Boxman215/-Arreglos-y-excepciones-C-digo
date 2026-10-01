public class Ubicacion {
    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado;

    public Ubicacion(String codigo, String nombre, String direccion, int nivelRiesgo, String estado) {
        setNivelRiesgo(nivelRiesgo);
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public int getNivelRiesgo() { return nivelRiesgo; }
    public String getEstado() { return estado; }

    public void setNivelRiesgo(int nivelRiesgo) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new IllegalArgumentException("El nivel de riesgo debe estar entre 1 y 10.");
        }
        this.nivelRiesgo = nivelRiesgo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Dirección: " + direccion +
               " | Riesgo: " + nivelRiesgo + "/10 | Estado: " + estado;
    }
}

