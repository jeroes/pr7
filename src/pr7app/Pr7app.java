package pr7app;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ivan
 */
public class Pr7app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, FTPIllegalReplyException, IllegalStateException, FTPException, FileNotFoundException, FTPDataTransferException, FTPAbortedException {
                
            URL url = new URL("http://192.168.122.200/docs/ic10-m04-WindowsServer.pdf");
            url.openConnection();
            InputStream leer = url.openStream();
            FileOutputStream escribir = new FileOutputStream("jesus.pdf");
            byte[] buffer = new byte[153600];
            int bytesRead = 0;
            while ((bytesRead = leer.read(buffer)) > 0) {
                escribir.write(buffer, 0, bytesRead);
            }
            escribir.close();
            leer.close();
            
            System.out.println("Descargando fichero...");
            System.out.println("Fichero descargado correctamente.");
            System.out.println("Subiendo fichero...");
            
            FTPClient clienteFTP = new FTPClient();
            clienteFTP.connect("srv.toca.cat",21);
            clienteFTP.login("fulano", "Platano123");
            clienteFTP.upload(new java.io.File("jesus.pdf"));
            clienteFTP.disconnect(true);
            System.out.println("Fichero subido correctamente.");
    }
        
       
  
}
