import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {


    public static void main(String args[]) throws IOException {
        generacionDatos g = new generacionDatos();

        g.generarTiendas();
        g.generarProductos();
        g.generarTiendaProductos();
        g.generarTrabajadores();
        g.generarTicketsProductos();
        g.generarTickets();
        g.generarTiendasBorradas();
    }
}
