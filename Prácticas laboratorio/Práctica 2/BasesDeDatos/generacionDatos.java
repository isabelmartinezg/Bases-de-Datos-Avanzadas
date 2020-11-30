import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class generacionDatos
{
    private String[] provincias = {"Alava","Albacete","Alicante","Almeria","Asturias","Avila","Badajoz","Barcelona",
            "Burgos","Cáceres","Cadiz","Cantabria","Castellon","Ciudad Real","Cordoba","La Coruña","Cuenca","Gerona",
            "Granada","Guadalajara","Guipuzcoa","Huelva","Huesca","Baleares","Jaén","Leon","Lerida","Lugo","Madrid",
            "Malaga","Murcia","Navarra","Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia",
            "Sevilla", "Soria", "Tarragona", "Santa Cruz de Tenerife", "Teruel", "Toledo", "Valencia", "Valladolid",
            "Vizcaya","Zamora", "Zaragoza", "Ceuta", "Melilla"};
    private String[] letras = {"A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R",
            "S","T","U","V","W","X","Y","Z"};
    BufferedWriter archivo1 = new BufferedWriter(new FileWriter("datosTienda.txt"));
    BufferedWriter archivo2 = new BufferedWriter(new FileWriter("datosProductos.txt"));
    BufferedWriter archivo3 = new BufferedWriter(new FileWriter("datosTrabajadores.txt"));
    BufferedWriter archivo4 = new BufferedWriter(new FileWriter("datosTickets.txt"));
    BufferedWriter archivo5 = new BufferedWriter(new FileWriter("datosTiendaProductos.txt"));
    BufferedWriter archivo6 = new BufferedWriter(new FileWriter("datosTicketsProductos.txt"));
    BufferedWriter archivo7 = new BufferedWriter(new FileWriter("datosTiendasBorradas.txt"));

    ArrayList<Integer> preciosProductos = new ArrayList<Integer>();
    ArrayList<Integer> preciosTickets = new ArrayList<Integer>();

    public generacionDatos() throws IOException {
    }


    public void generarTiendas() throws IOException
    {

        Random rand = new Random();
        for(int i =0 ; i< 200000;i++)
        {
            archivo1.write(String.valueOf(i)+","+"nombre"+String.valueOf(i)+","+"ciudad"+String.valueOf(i)+","+"barrio"+String.valueOf(i)+","+provincias[rand.nextInt(50)]+"\n");
            System.out.println("Generado tienda: "+i);
        }
        archivo1.close();
    }
    public void generarTiendasBorradas() throws IOException
    {

        Random rand = new Random();
        ArrayList<Integer> tiendas = new ArrayList<Integer>();
        for(int i =0 ; i< 100000;i++)
        {
            int id;
            do {
               id = rand.nextInt(200000);
            }while(tiendas.contains(id));
            archivo7.write(String.valueOf(id)+"\n");
            tiendas.add(id);
        }
        archivo7.close();
    }

    public void generarProductos()throws IOException
    {
        Random rand = new Random();
        for(int i = 0; i< 1000000;i++)
        {
            int precio = rand.nextInt(951)+50;
            preciosProductos.add(precio);
            archivo2.write("codigo"+String.valueOf(i)+","+"nombre"+String.valueOf(i)+","+"tipo"+String.valueOf(i)+","+"descripcion"+String.valueOf(i)+","+String.valueOf(precio)+"\n");
            System.out.println("Generado Producto: "+i);
        }
        archivo2.close();
    }

    public void generarTiendaProductos()throws IOException
    {
        Random rand  = new Random();
        ArrayList<Integer> productosIntroducidos = new ArrayList<Integer>();

        for(int i = 0; i < 200000; i++)
        {
            for(int j = 0; j<100; j++)
            {
                int n;
                do {
                    n = rand.nextInt(1000000);

                }while(productosIntroducidos.contains(n));
                productosIntroducidos.add(n);
                int stock = rand.nextInt(191)+10;
                archivo5.write(String.valueOf(i)+","+"codigo"+String.valueOf(n)+","+String.valueOf(stock)+"\n");
            }
            System.out.println("Generado tienda Prod: "+i);

            productosIntroducidos.clear();
        }
        archivo5.close();
    }

    public void generarTrabajadores()throws IOException
    {
        Random rand = new Random();
        for(int i = 0; i<1000000; i++)
        {
            String dni = generarDNI(i);
            archivo3.write(String.valueOf(i)+","+dni+","+"nombre"+String.valueOf(i)+","+"apellidos"+String.valueOf(i)+","+"puesto"+String.valueOf(i)+","+String.valueOf(rand.nextInt(4001)+1000)+","+String.valueOf(rand.nextInt(200000))+"\n");
            System.out.println("Generado trabajador: "+i);
        }
        archivo3.close();
    }

    public String generarDNI(int i)
    {
        int nCeros = 0;
        int num = i;
        int nCifras = 0;
        Random rand = new Random();
        String dni = "";
        while(i>=1)
        {
            i = i/10;
            nCifras++;
        }
        nCeros = 8-nCifras;

        for(int k = 0;k<nCeros;k++)
        {
            dni+="0";
        }
        dni+=String.valueOf(num);
        dni+=letras[rand.nextInt(letras.length)];

        return dni;
    }

    public void generarTicketsProductos() throws IOException {
        Random rand = new Random();
        ArrayList<Integer> codigosProductosMetidos = new ArrayList<Integer>();
        ArrayList<Integer>cantidadesMetidas = new ArrayList<Integer>();
        for(int i= 0; i< 5000000; i++)
        {
            int nProductos = rand.nextInt(10)+ 1;
            int precioTicket=0;
            do {
                precioTicket=0;
                int cantidad = 0;
                codigosProductosMetidos.clear();
                cantidadesMetidas.clear();
                for(int k = 0; k<nProductos;k++)
                {
                    int codigo;
                    do{
                        codigo = rand.nextInt(1000000);
                    }while(codigosProductosMetidos.contains(codigo));

                    int precio = preciosProductos.get(codigo);
                    cantidad = rand.nextInt(10)+1;
                    codigosProductosMetidos.add(codigo);
                    cantidadesMetidas.add(cantidad);
                    precioTicket+=(precio*cantidad);
                }
            }while(precioTicket > 10000 || precioTicket < 100);

            for(int j = 0; j< nProductos;j++)
            {
                archivo6.write(String.valueOf(i)+","+"codigo"+String.valueOf(codigosProductosMetidos.get(j))+","+String.valueOf(cantidadesMetidas.get(j))+"\n");
            }
            System.out.println("Generado ticketProducto: "+i);
            codigosProductosMetidos.clear();
            preciosTickets.add(precioTicket);
        }
        archivo6.close();
    }

    public void generarTickets() throws IOException {
        Random rand = new Random();

        for(int i = 0; i< 5000000; i++)
        {
            String fecha = generarFecha();
            int codTrabajador = rand.nextInt(1000000);
            archivo4.write(String.valueOf(i)+","+preciosTickets.get(i)+",'"+fecha+"',"+String.valueOf(codTrabajador)+"\n");
            System.out.println("Generado ticket: "+i);
        }
        archivo4.close();
    }

    public String generarFecha()
    {
        Random rand = new Random();

        int dia;
        int mes;

        do {
             dia = rand.nextInt(31)+1;
             mes= rand.nextInt(12)+1;
        }while(!validarFecha(dia,mes));
        return String.valueOf(dia)+"/"+String.valueOf(mes)+"/2019";
    }

    public boolean validarFecha(int dia, int mes)
    {
        if(mes == 2 && dia > 28)
        {
            return false;
        }else if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31)
        {
            return false;
        }else
        {
            return true;
        }
    }

}
