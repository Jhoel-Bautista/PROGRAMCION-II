package Util;

import Dominio.Ticket;
import Dominio.Repuesto;
import Dominio.Tecnico;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

    /**
     * Clase demostrativa de Expresiones Lambda y Stream API de Java 8+.
     * Creada para evidenciar el uso de programación funcional en el sistema.
     * @author Jhoel
     */
    public class ProcesadorLambdas {

        /**
         * 1. LAMBDA CON PREDICATE (Filtrado condicional)
         * Filtra una lista de tickets para devolver solo los que tienen prioridad alta (Prioridad 1).
         */
        public static List<Ticket> filtrarTicketsCriticos(List<Ticket> listaTickets) {
            // La expresión lambda (t -> t.getPrioridad() == 1) evalúa la condición de forma funcional
            return listaTickets.stream()
                    .filter(t -> t.getPrioridad() == 1)
                    .collect(Collectors.toList());
        }

        /**
         * 2. LAMBDA CON MAP Y FUNCTION (Transformación de datos)
         * Extrae únicamente los nombres de los técnicos a partir de una lista de objetos Técnico.
         */
        public static List<String> obtenerNombresTecnicos(List<Tecnico> listaTecnicos) {
            // La expresión lambda (tec -> tec.getNombre()) transforma cada objeto en un String
            return listaTecnicos.stream()
                    .map(tec -> tec.getNombre())
                    .collect(Collectors.toList());
        }

        /**
         * 3. LAMBDA CON CONSUMER (Iteración y consumo de elementos)
         * Recorre el inventario de repuestos y muestra en consola aquellos con stock crítico.
         */
        public static void evaluarStockCritico(List<Repuesto> listaRepuestos, int umbral) {
            // La expresión lambda (r -> System.out.println(...)) consume cada elemento de la lista
            listaRepuestos.stream()
                    .filter(r -> r.getStockDisponible() < umbral)
                    .forEach(r -> System.out.println("⚠️ Alerta Stock Crítico: " + r.getNombreRepuesto() + " (Quedan: " + r.getStockDisponible() + ")"));
        }

        /**
         * 4. LAMBDA CON COMPARATOR (Ordenamiento funcional)
         * Ordena una lista de repuestos por su costo unitario de forma ascendente.
         */
        public static void ordenarRepuestosPorCosto(List<Repuesto> listaRepuestos) {
            // La expresión lambda (r1, r2) -> Double.compare(...) define el criterio de ordenamiento
            listaRepuestos.sort((r1, r2) -> Double.compare(r1.getCostoUnitario(), r2.getCostoUnitario()));
        }
    }

