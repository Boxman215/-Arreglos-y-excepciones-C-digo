public class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia, int nivelImportancia, int nivelConfiabilidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoEvidencia = tipoEvidencia;
        setNivelImportancia(nivelImportancia);
        setNivelConfiabilidad(nivelConfiabilidad);
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipoEvidencia() { return tipoEvidencia; }
    public void setTipoEvidencia(String tipoEvidencia) { this.tipoEvidencia = tipoEvidencia; }

    public int getNivelImportancia() { return nivelImportancia; }
    public void setNivelImportancia(int nivelImportancia) {
        if (nivelImportancia < 1 || nivelImportancia > 10) {
            throw new IllegalArgumentException("El nivel de importancia debe estar entre 1 y 10.");
        }
        this.nivelImportancia = nivelImportancia;
    }

    public int getNivelConfiabilidad() { return nivelConfiabilidad; }
    public void setNivelConfiabilidad(int nivelConfiabilidad) {
        if (nivelConfiabilidad < 0 || nivelConfiabilidad > 100) {
            throw new IllegalArgumentException("El nivel de confiabilidad debe estar entre 0 y 100.");
        }
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Tipo: " + tipoEvidencia + " | Importancia: " + nivelImportancia +
               "/10 | Confiabilidad: " + nivelConfiabilidad + "% | Desc: " + descripcion;
    }
}
