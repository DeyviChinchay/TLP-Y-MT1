/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package codigo;

import static codigo.Tokens.ASSIGN;
import static codigo.Tokens.ELSE;
import static codigo.Tokens.END;
import static codigo.Tokens.EOF;
import static codigo.Tokens.EQ;
import static codigo.Tokens.ERROR;
import static codigo.Tokens.IF;
import static codigo.Tokens.Identificador;
import static codigo.Tokens.LPAREN;
import static codigo.Tokens.LT;
import static codigo.Tokens.MINUS;
import static codigo.Tokens.Numero;
import static codigo.Tokens.OVER;
import static codigo.Tokens.PLUS;
import static codigo.Tokens.READ;
import static codigo.Tokens.REPEAT;
import static codigo.Tokens.RPAREN;
import static codigo.Tokens.SEMI;
import static codigo.Tokens.THEN;
import static codigo.Tokens.TIMES;
import static codigo.Tokens.UNTIL;
import static codigo.Tokens.WHITESPACE;
import static codigo.Tokens.WRITE;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Deyvi
 */
public class FrmPrincipal extends javax.swing.JFrame {
    
    public  void Exportar(StringBuilder resultado) {
        /*try {
            JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
            archivo.showSaveDialog(this);

            if (archivo.getSelectedFile() != null) {
                try (FileWriter guardado = new FileWriter(archivo.getSelectedFile())) {
                    guardado.write(resultado);
                    JOptionPane.showMessageDialog(rootPane, "El archivo fue guardado con éxito en la ruta establecida");
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }*/
        
        JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
        archivo.setDialogTitle("Guardar archivo");
        int userSelection = archivo.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = archivo.getSelectedFile();
            try (FileWriter guardado = new FileWriter(fileToSave)) {
                guardado.write(resultado.toString());
                JOptionPane.showMessageDialog(this, "El archivo fue guardado con éxito en la ruta establecida");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }
   
    
              
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    
    
    
        
    
    
    }

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        
        /*
        String line;
        Integer contador1 = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile())); 
            
            while ((line = br.readLine()) != null) {
                contador1++;
                //txtResultado.append(String.format("%5d %s%n" , contador1 , line));
                System.out.printf("%5d %s%n" , contador1 , line);
            }
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        */

        
        StringBuilder resultado = new StringBuilder();
        String line1;
        int contador = 0;
        try {
            /*FileReader fr =  new FileReader(chooser.getSelectedFile());
            BufferedReader lector = new BufferedReader(fr);*/
            
            BufferedReader lector = new BufferedReader(new FileReader(chooser.getSelectedFile()));
     
           
            while((line1 = lector.readLine())!= null){
                
                
                Lexer lexer = new Lexer(new StringReader(line1));
                Tokens tokens;
                
                
                
                while((tokens = lexer.yylex()) != null){
                    contador++;
                    if(tokens == null || tokens == tokens.EOF){
                        resultado.append("FIN");
                        txtResultado.append(String.format("%5d %s%n" , contador , resultado.toString()));
                        
                        return;
                    }
                    
                   
                    switch(tokens){
                    case ERROR:
                       //resultado.append("Simbolo no definido");
                       resultado.append(String.format("%5d %s%n", contador, "Simbolo no definido")).append("\n");
                       break;
                    case THEN:   
                    case Identificador : 
                    case IF: 
                    case ELSE: 
                    case REPEAT:
                    case READ:
                    case WHITESPACE:
                    case EQ: 
                    case PLUS: 
                    case MINUS: 
                    case TIMES:
                    case END:
                    case WRITE:
                    case ASSIGN:
                    case UNTIL:
                    case OVER:
                    case LPAREN:
                    case RPAREN:
                    case SEMI:
                    case Numero:
                    case LT:
                       //resultado.append(tokens).append("\n");
                       txtResultado.append(String.format("%5d %s%n", contador, tokens.toString()));
                       resultado.append(String.format("%5d %s%n", contador, tokens.toString())).append("\n");
                       break;    
                    default :       
                        //resultado.append(tokens).append("\n");
                        txtResultado.append(String.format("%5d %s%n", contador , tokens.toString()));
                        resultado.append(String.format("%5d %s%n", contador, tokens.toString())).append("\n");
                        break;
                  
                    }
                   
                    
                }
                
                               
                }
            
              Exportar(resultado);
        }catch(FileNotFoundException ex){
            System.out.println("El archivo no se encuentra");
        }catch(IOException ex){
            System.out.println("El archivo yylex no se ejecuto correctamente");
        }
        
        
        
        
        
        
        
      
        
        
    }//GEN-LAST:event_btnAnalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
