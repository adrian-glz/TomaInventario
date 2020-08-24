/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static inventariojava.Configuracionserver.sucursalglobal;
 import static inventariojava.Configuracionserver.sucursalnombre;
//import static inventariojava.Configuracionserver.NOMBRESUCURSAL;
//import static inventariojava.Configuracionserver.TELEFONOSUCURSAL;
//import static inventariojava.Configuracionserver.IMPRESORA;



public class Configuracionserver {

    public static String sucursalglobal = "";
    public static String sucursalnombre = "";


}

class Leer_fichero {

        public static void leerconexion() {
            String texto;
            File ruta = new File("C:\\Program Files\\TomaInventario/config.txt");
            if (ruta.exists()) {
                try {
                    //  System.out.println(System.getProperty("user.name")); 
                    //String user = System.getProperty("user.name");
                    //  File archivo = new File("C:\\Users\\" + user + "\\Desktop\\LEEME.txt");
                    File archivo = new File("C:\\Program Files\\TomaInventario/config.txt");

                    FileReader entrada = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(entrada);
                    ArrayList arreglo = new ArrayList();
                    while ((texto = br.readLine()) != null) {
                    arreglo.add(texto);
                }
                entrada.close();

                sucursalglobal = arreglo.get(0).toString();
                sucursalnombre = arreglo.get(1).toString();
                // /   NOMBRESUCURSAL = arreglo.get(2).toString();
                //   TELEFONOSUCURSAL = arreglo.get(3).toString();
                //   IMPRESORA = arreglo.get(4).toString();

                System.out.println("" + sucursalglobal + sucursalnombre);
            } catch (IOException e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "compruebe que config.TXT EXISTA Y/O tenga los parametros necesarios");
            System.out.println("No se ha encontrado el archivo:en C:\\Program Files\\TomaInventario\\config.txt");
        }
    }
}
