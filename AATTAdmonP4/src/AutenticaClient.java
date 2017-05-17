
import aatt.practica04.metodosHTTP.PeticionPost;
import es.gob.jmulticard.jse.provider.DnieProvider;
import modelUsuario.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Clase AutenticaCliente
 * 
 * Objetivo 1:
 * Obtención de certificados del DNI, firmar datos y realizar una petición HTTP
 * de tipo POST.
 * 
 * Objetivo 2:
 * Guardar el certificado de autenticación en un documento con extensión key
 * 
 * @author Andrés Ruiz Peñuela
 * @author Luis Jesús Montes Pérez
 * @author Juan Carlos
 * 
 * @version 0.0.1
 */
public class AutenticaClient extends javax.swing.JFrame {

    public static User user = new User(); //Intancia de User.
    public static String url ="http://localhost:8080/servidorHttp/"; //Localizador Uniforme de Recurso.
    
    public static RSAPublicKey saveCertficiadoRSA = null;
    
    /**
     * Creates new form NewJFrame
     */
    public AutenticaClient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAutentica = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jName = new javax.swing.JLabel();
        jButtonGrabar = new javax.swing.JButton();
        jLabel_apellidos = new javax.swing.JLabel();
        jLabel_dni = new javax.swing.JLabel();
        jApellidos = new javax.swing.JLabel();
        jDNI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        setTitle(bundle.getString("app_title")); // NOI18N

        jButtonAutentica.setText(bundle.getString("main_button_autenticate")); // NOI18N
        jButtonAutentica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutenticaActionPerformed(evt);
            }
        });

        jLabelName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelName.setText("Nombre:");

        jName.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jName.setText("nombre");
        jName.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jNameAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jButtonGrabar.setText("Graba certificado");
        jButtonGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGrabarActionPerformed(evt);
            }
        });

        jLabel_apellidos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_apellidos.setText("Apellidos:");

        jLabel_dni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_dni.setText("DNI:");

        jApellidos.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jApellidos.setText("apellidos");

        jDNI.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jDNI.setText("dni");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_apellidos)
                            .addComponent(jLabel_dni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDNI)
                            .addComponent(jApellidos)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonGrabar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jName)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAutentica)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_apellidos)
                    .addComponent(jApellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_dni)
                    .addComponent(jDNI))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAutentica)
                    .addComponent(jButtonGrabar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAutenticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutenticaActionPerformed
        try {
            iniKeyStore();
            try {
                //Se pide los datos que se desean firmar
                String data = (String) JOptionPane.showInputDialog(
                            null,"Datos","Firmar",JOptionPane.PLAIN_MESSAGE,
                            null,null,null);
                
                //Realizamos la firma de los datos.
                doAuth(data);
                
                //doAuth("datos");
                
                
            } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
                Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SignatureError | KeyStoreException ex) {
            Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAutenticaActionPerformed

    private void jButtonGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarActionPerformed
      saveCertificate();
    }//GEN-LAST:event_jButtonGrabarActionPerformed

    private void jNameAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jNameAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jNameAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        String dn = "";
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AutenticaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutenticaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutenticaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutenticaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            //Activamos el modo rápido.
            System.setProperty("es.gob.jmulticard.fastmode", "true");

            iniKeyStore(); //Tarea 1 y 2.

            final Enumeration<String> aliases = dniKS.aliases();
            while (aliases.hasMoreElements()) {
                System.out.println(aliases.nextElement());
            }
            
            //Resultado Tarea 1 y 2.
            infoBox("Hola "+user.getName()+", "+user.getApellidos(), "Bienvenido");

        } catch (KeyStoreException ex) {
            Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
            dniKS = null;
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutenticaClient().setVisible(true);
                
                //Mostramos los datos del usuario en la interfaz. 
                //Resultado de la Tarea 2.
                jName.setText(user.getName());
                jApellidos.setText(user.getApellidos());
                jDNI.setText(user.getDni());
                
            }
        });

    }

    public static String alias = "CertFirmaDigital";
    private static Provider dniProvider = null;
    private static KeyStore dniKS = null;
    private static X509Certificate authCert = null;

    /**
     * @param args the command line arguments
     */
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método para inicializar el almacén de claves y certificados (KeyStore),
     * se recomiena inicializarlo en modo rápido
     * 
     * @throws KeyStoreException 
     */
    private static void iniKeyStore() throws KeyStoreException {
        
        if (dniKS == null) {//TODO inicializar el KeyStore 
           
           //Apartado 5.1 Ejemplo de extracción de certificados del titular.
          
           // Se instancia el proveedor , Se activa el modo rápido y se anade
           dniProvider = new DnieProvider();
           System.setProperty("es.gob.jmulticard.fastmode", "true");                   
           Security.addProvider(dniProvider);
           
           
           // Se obtiene el almacen (denominado DNI) y se carga
           dniKS = KeyStore.getInstance("DNI"); //$NON-NLS-1$
           try {
                dniKS.load(null, null);
           } catch (IOException | NoSuchAlgorithmException | CertificateException ex) {
                Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           //Obtenemos el certificado (con clave pública) de firma digital del DNI.
           authCert = (X509Certificate) dniKS.getCertificate("CertAutenticacion");
           
           //Obtenemos los datos del usuario apartir del certificado.
           user = new User(authCert);
        }
    }
    
    /**
     * Tarea 5.
     * Extraer la clave pública del certificado de autenticación
     * 
     */
    public void saveCertificate() {
        try {

            try ( // Se obtiene el motor de firma y se inicializa   
                    FileOutputStream keyDoc = new FileOutputStream("public.key")) {
                    System.out.println("---");
                    System.out.println("saveCertificate()"); 
                    System.out.println("Formato del certificado: "+authCert.toString());
                    System.out.println("---");
                    
                    byte encodedKey[] = saveCertficiadoRSA.getEncoded(); //Codificamos el certificado.
                    String rsakey = saveCertficiadoRSA.getFormat() + " " + saveCertficiadoRSA.getAlgorithm() + saveCertficiadoRSA.toString();                    
                    System.out.println("oprs "+rsakey);
                    keyDoc.write(encodedKey);
                    
            }
        } catch (IOException ex) {
            Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Métdo doAuthh 
     * Devuelve los datos firmados en Base64, y realizamos el envío de datos
     * al servidor
     *
     * @param data de tipo String, corresponde con los datos a firmar
     * 
     * @throws AutenticaClient.SignatureError
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException 
     */   
    private void doAuth(String data) throws SignatureError, NoSuchAlgorithmException, InvalidKeyException {
        
        //Apartado 5.2. Ejemplo de realización de firma electrónica con DNIe
        // Obtenemos el motor de firma y se inicializa
        final Signature signature = Signature.getInstance("SHA-256withRSA"); //$NON-NLS-1$
        try {
            //Realizamos la firma privada.
            signature.initSign((PrivateKey) dniKS.getKey(alias, null));
            
            //Extraemos la clave pública del certificado de autenticación (parte de la tarea 5)
            saveCertficiadoRSA = (RSAPublicKey) dniKS.getCertificate(alias).getPublicKey();
            System.out.println("Certificado de autenticacion: \n"+saveCertficiadoRSA);
            
            //Obtenemos los datos a firmar (Tarea 4)
            String datos[] = user.datosAFirmar(url,data); //Resultado de la Tarea 3.
            
            //Bloque para firmar y enviar los datos.
            try {
                // Firmamos los datos obtenidos de la tarea.
                signature.update(datos[1].getBytes()); //$NON-NLS-1$
            
                try {
                    // Completamos el proceso y obtenemos la firma PKCS#11
                    final byte[] signatureBytes = signature.sign();
                    
                    //Devolvemos el resultado de la firma en Base64.
                    byte[] datosBase64 = Base64.getEncoder().encode(signatureBytes);
                    System.out.println(Arrays.toString(datosBase64));

                    try {
                        //Construimos los parámetros que se van ha enviar
                        String dataSend = datos[0]+"&firma="+URLEncoder.encode(new String(datosBase64), "UTF-8")+"&clavePublica="+URLEncoder.encode(new String(saveCertficiadoRSA.getEncoded()), "UTF-8");
                        System.out.println("Parámetrso ha enviar: \n"+dataSend);
                        
                        //Realizamos el envío
                        PeticionPost peti = new PeticionPost(url,dataSend); //Instancia de la case PeticionPost
                        try {
                            String resultado = peti.autentica(); //Método para realizar petiicón POST
                        } catch (IOException ex) {
                            Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                } catch (SignatureException ex) {
                    Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
                }          
            } catch (SignatureException ex) {
                Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
            }     
        } catch (KeyStoreException | UnrecoverableKeyException ex) {
            Logger.getLogger(AutenticaClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public class SignatureError extends Exception {

        private SignatureError(String message) {
            super(message);
        }

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel jApellidos;
    private javax.swing.JButton jButtonAutentica;
    private static javax.swing.JButton jButtonGrabar;
    private static javax.swing.JLabel jDNI;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabel_apellidos;
    private javax.swing.JLabel jLabel_dni;
    private static javax.swing.JLabel jName;
    // End of variables declaration//GEN-END:variables
}
