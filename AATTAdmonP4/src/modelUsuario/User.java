package modelUsuario;

import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Procesa la cadena del nombre DNIe USUARIOC=ES, SERIALNUMBER=26038239P,
 * SURNAME=CUEVAS, GIVENNAME=JUAN CARLOS, CN="CUEVAS MARTINEZ, JUAN CARLOS
 * (FIRMA)"
 *
 * @author Juan Carlos
 * @author Andres
 * @author Luis
 */
public class User {

    public static final String NAME="GIVENNAME=";
    public static final String DNI="SERIALNUMBER=";
    public static final String CN="CN=\"";
    String name = "";
    String apellidos = "";
    String dni = "";

    public User(){};
    
    public User(String data) {

        name = data.substring(data.indexOf(NAME) + NAME.length());
        name = name.substring(0, name.indexOf(","));
        dni = data.substring(data.indexOf(DNI) + DNI.length());
        dni = dni.substring(0, dni.indexOf(","));
        apellidos = data.substring(data.indexOf(CN) + CN.length());
        apellidos = apellidos.substring(0, apellidos.indexOf(","));
    }
    /**
     * Contrustor para obtener los atributos del usuario a partir del certificado.
     * Certificado impostado: 
            Emisor: C=ES, O=DIRECCION GENERAL DE LA POLICIA, OU=DNIE, CN=AC DNIE 001
            Titular: C=ES, SERIALNUMBER=26499565T, SURNAME=RUIZ, GIVENNAME=ANDRÉS, CN="RUIZ PEÑUELA, ANDRÉS (AUTENTICACIÓN)"
            Numero de serie: 106037972296008694219852038374793453062
     * @param authCert de tipo X509Certificate
     */
    public User (X509Certificate authCert){
        String data = authCert.toString();
        
        name = data.substring(data.indexOf(NAME) + NAME.length());
        name = name.substring(0, name.indexOf(","));
        dni = data.substring(data.indexOf(DNI) + DNI.length());
        dni = dni.substring(0, dni.indexOf(","));
        apellidos = data.substring(data.indexOf(CN) + CN.length());
        apellidos = apellidos.substring(0, apellidos.indexOf(","));
  
    }

    public String getName() {
        return name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    /**
     * Tarea 3.
     * Método que genere los datos para firmar que tenga como entrada la URL destino, e incluya una marca de tiempo.
     * @param url detipo String.
     * @return 
     */
    public String [] datosAFirmar (String url,String data){
        
       
        //Aquí colocas tu objeto tipo Date
        Date date = new Date();
        String timestamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
        
        //Separamos los apellidos
        String ape[] = this.apellidos.split(" "); 
        
        //Aquí obtienes el formato que deseas
        System.out.println("Timestamp: "+ timestamp);
        
        //Obtenemos los datos que se van enviar sin la clave plública
        System.out.println("OJO: "+"timestap="+timestamp+"&nombre="+ this.name + "&apellido1="+ape[0]+"&apellido2="+ape[1]+"&dni=" + this.dni);
        //datos[0] = "nombre"+this.name;
        //Datos que se firman
        String[] datos = {"timestap="+timestamp+"&nombre="+ this.name + "&apellido1="+ape[0]+"&apellido2="+ape[1]+"&dni=" + this.dni,data};
        
        return datos;
    }

}
