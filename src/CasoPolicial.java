import java.util.ArrayList;

public class CasoPolicial {
    private String nombreCaso;
    private String codigoCaso;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public CasoPolicial(String nombreCaso, String codigoCaso, String detectiveResponsable) {
        this.nombreCaso = nombreCaso;
        this.codigoCaso = codigoCaso;
        this.detectiveResponsable = detectiveResponsable;
        this.ubicaciones = new Ubicacion[5]; // Arreglo básico de tamaño fijo
        this.pistas = new ArrayList<>();     // ArrayList dinámico
    }

    public String getNombreCaso() { return nombreCaso; }
    public String getCodigoCaso() { return codigoCaso; }
    public String getDetectiveResponsable() { return detectiveResponsable; }
    public Ubicacion[] getUbicaciones() { return ubicaciones; }
    public ArrayList<Pista> getPistas() { return pistas; }

    // Métodos para Ubicaciones (Arreglo)
    public void registrarUbicacion(int posicion, Ubicacion ub) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("La posición " + posicion + " no existe. Debe ser de 0 a 4.");
        }
        if (ubicaciones[posicion] != null) {
            throw new IllegalArgumentException("La posición " + posicion + " ya se encuentra ocupada.");
        }
        ubicaciones[posicion] = ub;
    }

    public Ubicacion obtenerUbicacion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("La posición " + posicion + " está fuera de los límites (0-4).");
        }
        return ubicaciones[posicion];
    }

    public void descartarUbicacion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("Posición fuera de límites.");
        }
        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException("La posición ya se encuentra vacía.");
        }
        ubicaciones[posicion] = null;
    }

    public int contarUbicacionesRegistradas() {
        int contador = 0;
        for (Ubicacion ub : ubicaciones) {
            if (ub != null) contador++;
        }
        return contador;
    }

    public Ubicacion obtenerUbicacionMayorRiesgo() {
        Ubicacion mayor = null;
        for (Ubicacion ub : ubicaciones) {
            if (ub != null) {
                if (mayor == null || ub.getNivelRiesgo() > mayor.getNivelRiesgo()) {
                    mayor = ub;
                }
            }
        }
        return mayor;
    }

    // Métodos para Pistas (ArrayList)
    public void registrarPista(Pista pista) {
        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una pista registrada con el código: " + pista.getCodigo());
        }
        pistas.add(pista);
    }

    public Pista buscarPista(String codigo) {
        for (Pista p : pistas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPista(String codigo) {
        Pista p = buscarPista(codigo);
        if (p != null) {
            return pistas.remove(p);
        }
        return false;
    }

    public Pista obtenerPistaMayorImportancia() {
        if (pistas.isEmpty()) return null;
        Pista mayor = pistas.get(0);
        for (Pista p : pistas) {
            if (p.getNivelImportancia() > mayor.getNivelImportancia()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public Pista obtenerPistaMayorConfiabilidad() {
        if (pistas.isEmpty()) return null;
        Pista mayor = pistas.get(0);
        for (Pista p : pistas) {
            if (p.getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public double calcularPromedioImportancia() {
        if (pistas.isEmpty()) return 0.0;
        double suma = 0;
        for (Pista p : pistas) {
            suma += p.getNivelImportancia();
        }
        return suma / pistas.size();
    }
}