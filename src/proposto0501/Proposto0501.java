/*
Utilizando un ficheiro CSV con información sobre libros co formato
título; autor; ano; xénero
encherase unha táboa dunha base de datos.
 */
package proposto0501;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Proposto0501 {

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblioteca", "dwcs", "1234");
            File ficheiro = new File("libros.csv");
            BufferedReader br = new BufferedReader(new FileReader(ficheiro));
            String linea;
            String[] cachos;
            String consulta;
            Statement st = con.createStatement();
            while ((linea = br.readLine()) != null) {
                cachos = linea.split(";");
                consulta = "INSERT INTO libros VALUES (NULL, '"
                        + cachos[0] + "', '" + cachos[1] + "', "
                        + cachos[2] + ", '" + cachos[3] + "')";
                
                st.executeUpdate(consulta);
            }
            
            consulta = "SELECT * FROM libros";
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()) {
                System.out.println(rs.getString("titulo"));
            }

        } catch (SQLException ex) {
            System.out.println("Non foi posible a conexión");
            System.out.println(ex.getLocalizedMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("Non existe o ficheiro");
        } catch (IOException ex) {
            System.out.println("Non foi posible a lectura");
        }
    }


}
