/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import java.awt.Component;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sesion.UserEncryp;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modelo.Permisos;
import modelo.Sesiones;
import modelo.Usuario;

/**
 *
 * @author jaquino
 */
public class Login extends javax.swing.JInternalFrame {
    JMenuItem jMenuItem1;
    JMenu jMenuCuentas;
    /**
     * Creates new form NewJInternalFrame
     */
    public Login(JMenuItem jMenuItem1,JMenu jMenuCuentas) {
        initComponents();
        this.jMenuItem1 = jMenuItem1;
        this.jMenuCuentas = jMenuCuentas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setClosable(true);
        setTitle("Login");
        setVisible(true);

        jLabel1.setText("Usuario:");

        txtUsername.setText("contador");
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel2.setText("Contraseña:");

        txtPassword.setText("123");

        btnLogin.setText("Iniciar sesión");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Sesiones ses = null;
        try {
            ses = sesion.UserValidator.verificarUsuario(txtUsername.getText(), txtPassword.getText());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ses!=null){
            jMenuItem1.setText("Cerrar sesión");
            jMenuCuentas.setEnabled(true);
            Principal.sesion = ses;
            habilitarBotones();
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(this,"Contraseña incorrecta. Pongase en contacto con el administrador");
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * Habilitar botones de los menus luego de la autenticación de usuario
     */
    private  void habilitarBotones(){
        Permisos permisos = Principal.sesion.getUsuario().getRoles().getPermisos();
        
        //Habilitar botones del menu de CUENTAS
        int itemsPrimerMenu = jMenuCuentas.getItemCount();
        for(int i=0;i<itemsPrimerMenu;i++){
            JMenuItem aux = jMenuCuentas.getItem(i);
            switch(aux.getText()){
                case "Catalogo de Cuentas": aux.setEnabled(permisos.isVerCatalogo()); break;
                case "Agregar Cuenta": aux.setEnabled(permisos.isAgregarCuenta()); break;
                case "Partidas": aux.setEnabled(permisos.isIngresarPartida()); break;
                case "Ajustes" : aux.setEnabled(permisos.isIngresarPartida()); break;
            }
        }
        
    }
    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
