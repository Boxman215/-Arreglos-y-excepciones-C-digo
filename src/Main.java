import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CasoPolicial casoActual = null;

    public static void main(String[] args) {

        
        // Inicialización básica
        crearNuevoCaso();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = -1;
            try {
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar salto de línea

                switch (opcion) {
                    case 1: crearNuevoCaso(); break;
                    case 2: registrarUbicacion(); break;
                    case 3: consultarUbicaciones(); break;
                    case 4: consultarUnaUbicacion(); break;
                    case 5: modificarUbicacion(); break;
                    case 6: descartarUbicacion(); break;
                    case 7: registrarPista(); break;
                    case 8: consultarPistas(); break;
                    case 9: buscarPista(); break;
                    case 10: modificarPista(); break;
                    case 11: eliminarPista(); break;
                    case 12: mostrarReporte(); break;
                    case 13: salir = true; System.out.println("Finalizando sistema..."); break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[Error de Entrada]: Debe ingresar un número entero.");
                scanner.nextLine(); // Limpiar el buffer de entrada incorrecta
            } catch (Exception e) {
                System.out.println("\n[Error en Operación]: " + e.getMessage());
            } finally {
                if (!salir) {
                    System.out.println("----------------------------------------");
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- AGENCIA DE DETECTIVES - SISTEMA DE CASOS ---");
        System.out.println("Caso Actual: " + casoActual.getNombreCaso() + " (" + casoActual.getCodigoCaso() + ")");
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicación (Arreglo)");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicación");
        System.out.println("5. Modificar ubicación");
        System.out.println("6. Descartar ubicación");
        System.out.println("7. Registrar pista (ArrayList)");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigación");
        System.out.println("13. Salir");
    }

    private static void crearNuevoCaso() {
        System.out.println("\n--- REGISTRO DE NUEVO CASO ---");
        System.out.print("Nombre del caso: ");
        String nombre = scanner.nextLine();
        System.out.print("Código de identificación: ");
        String codigo = scanner.nextLine();
        System.out.print("Detective responsable: ");
        String detective = scanner.nextLine();

        casoActual = new CasoPolicial(nombre, codigo, detective);
        System.out.println("Nuevo caso iniciado correctamente.");
    }

    private static void registrarUbicacion() {
        System.out.println("\n--- REGISTRAR UBICACIÓN ---");
        System.out.print("Ingrese la posición (0 a 4): ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Código: ");
        String cod = scanner.nextLine();
        System.out.print("Nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Dirección/Descripción: ");
        String dir = scanner.nextLine();
        System.out.print("Nivel de riesgo (1-10): ");
        int riesgo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Estado: ");
        String est = scanner.nextLine();

        Ubicacion nueva = new Ubicacion(cod, nom, dir, riesgo, est);
        casoActual.registrarUbicacion(pos, nueva);
        System.out.println("Ubicación registrada exitosamente en la posición " + pos + ".");
    }

    private static void consultarUbicaciones() {
        System.out.println("\n--- UBICACIONES REGISTRADAS ---");
        Ubicacion[] ubs = casoActual.getUbicaciones();
        boolean hayUbicaciones = false;

        for (int i = 0; i < ubs.length; i++) {
            if (ubs[i] != null) {
                System.out.println("Posición [" + i + "]: " + ubs[i]);
                hayUbicaciones = true;
            }
        }
        if (!hayUbicaciones) {
            System.out.println("No hay ubicaciones registradas en el caso.");
        }
    }

    private static void consultarUnaUbicacion() {
        System.out.print("\nIngrese la posición a consultar (0 a 4): ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        Ubicacion ub = casoActual.obtenerUbicacion(pos);
        if (ub == null) {
            System.out.println("La posición [" + pos + "] se encuentra VACÍA (null).");
        } else {
            System.out.println("Posición [" + pos + "]: " + ub);
        }
    }

    private static void modificarUbicacion() {
        System.out.print("\nIngrese la posición de la ubicación a modificar (0 a 4): ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        Ubicacion ub = casoActual.obtenerUbicacion(pos);
        if (ub == null) {
            System.out.println("No se puede modificar. La posición está vacía.");
            return;
        }

        System.out.print("Nuevo nivel de riesgo (1-10): ");
        int nuevoRiesgo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo estado: ");
        String nuevoEstado = scanner.nextLine();

        ub.setNivelRiesgo(nuevoRiesgo);
        ub.setEstado(nuevoEstado);
        System.out.println("Ubicación actualizada correctamente.");
    }

    private static void descartarUbicacion() {
        System.out.print("\nIngrese la posición de la ubicación a descartar (0 a 4): ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        casoActual.descartarUbicacion(pos);
        System.out.println("Ubicación descartada. La posición [" + pos + "] ahora está libre (null).");
    }

    private static void registrarPista() {
        System.out.println("\n--- REGISTRAR PISTA ---");
        System.out.print("Código: ");
        String cod = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Tipo de evidencia: ");
        String tipo = scanner.nextLine();
        System.out.print("Nivel de importancia (1-10): ");
        int imp = scanner.nextInt();
        System.out.print("Nivel de confiabilidad (0-100): ");
        int conf = scanner.nextInt();
        scanner.nextLine();

        Pista nueva = new Pista(cod, desc, tipo, imp, conf);
        casoActual.registrarPista(nueva);
        System.out.println("Pista registrada exitosamente en el ArrayList.");
    }

    private static void consultarPistas() {
        System.out.println("\n--- LISTA DE PISTAS REGISTRADAS ---");
        if (casoActual.getPistas().isEmpty()) {
            System.out.println("No se han registrado pistas en la investigación.");
            return;
        }
        for (Pista p : casoActual.getPistas()) {
            System.out.println(p);
        }
    }

    private static void buscarPista() {
        System.out.print("\nIngrese el código de la pista a buscar: ");
        String cod = scanner.nextLine();

        Pista p = casoActual.buscarPista(cod);
        if (p == null) {
            System.out.println("No se encontró ninguna pista con el código: " + cod);
        } else {
            System.out.println("Pista encontrada: " + p);
        }
    }

    private static void modificarPista() {
        System.out.print("\nIngrese el código de la pista a modificar: ");
        String cod = scanner.nextLine();

        Pista p = casoActual.buscarPista(cod);
        if (p == null) {
            System.out.println("No se encontró la pista solicitada.");
            return;
        }

        System.out.print("Nueva descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Nuevo tipo de evidencia: ");
        String tipo = scanner.nextLine();
        System.out.print("Nuevo nivel de importancia (1-10): ");
        int imp = scanner.nextInt();
        System.out.print("Nuevo nivel de confiabilidad (0-100): ");
        int conf = scanner.nextInt();
        scanner.nextLine();

        p.setDescripcion(desc);
        p.setTipoEvidencia(tipo);
        p.setNivelImportancia(imp);
        p.setNivelConfiabilidad(conf);
        System.out.println("Pista modificada correctamente.");
    }

    private static void eliminarPista() {
        System.out.print("\nIngrese el código de la pista a eliminar: ");
        String cod = scanner.nextLine();

        boolean eliminada = casoActual.eliminarPista(cod);
        if (eliminada) {
            System.out.println("Pista eliminada correctamente del ArrayList.");
        } else {
            System.out.println("No se pudo eliminar: Código no encontrado.");
        }
    }

    private static void mostrarReporte() {
        System.out.println("\n=== REPORTE GENERAL DE INVESTIGACIÓN ===");
        System.out.println("Caso: " + casoActual.getNombreCaso() + " | Detective: " + casoActual.getDetectiveResponsable());

        // Reporte de Ubicaciones
        int regUbicaciones = casoActual.contarUbicacionesRegistradas();
        int disponibles = 5 - regUbicaciones;
        System.out.println("\n--- Ubicaciones ---");
        System.out.println("Ubicaciones registradas: " + regUbicaciones);
        System.out.println("Espacios disponibles: " + disponibles);

        Ubicacion mayorRiesgo = casoActual.obtenerUbicacionMayorRiesgo();
        if (mayorRiesgo != null) {
            System.out.println("Ubicación con mayor riesgo: " + mayorRiesgo.getNombre() + " (Riesgo: " + mayorRiesgo.getNivelRiesgo() + ")");
        } else {
            System.out.println("Ubicación con mayor riesgo: N/A (No hay ubicaciones)");
        }

        // Reporte de Pistas
        System.out.println("\n--- Pistas ---");
        System.out.println("Cantidad de pistas registradas: " + casoActual.getPistas().size());

        if (casoActual.getPistas().isEmpty()) {
            System.out.println("Pista con mayor importancia: N/A");
            System.out.println("Pista con mayor confiabilidad: N/A");
            System.out.println("Promedio nivel de importancia: N/A");
        } else {
            Pista mImp = casoActual.obtenerPistaMayorImportancia();
            Pista mConf = casoActual.obtenerPistaMayorConfiabilidad();
            double promedio = casoActual.calcularPromedioImportancia();

            System.out.println("Pista con mayor importancia: Code " + mImp.getCodigo() + " (Nivel: " + mImp.getNivelImportancia() + ")");
            System.out.println("Pista con mayor confiabilidad: Code " + mConf.getCodigo() + " (Confiabilidad: " + mConf.getNivelConfiabilidad() + "%)");
            System.out.printf("Promedio de importancia de pistas: %.2f\n", promedio);
        }
    }
}