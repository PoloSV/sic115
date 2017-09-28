package Clases;


import frm.Principal;
import modelo.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaquino
 */
public class NewClass {
    public static void main(String[] args) {
        User user = new User();
        Principal pr = new Principal(user,"Nimbus");
        pr.setVisible(true);
    }
}
