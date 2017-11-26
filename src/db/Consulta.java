/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.List;
import modelo.Cuenta;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Edwin Dimas
 */
public class Consulta {

    SessionFactory sessionFactory;
    Session session;
    Transaction tr;

    public Consulta() {

    }

    //Inicializa la conexion y la sesion, siempre hacerlo despues de cerrar.
    public void inicializar() {
        sessionFactory = NewHibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        tr = session.beginTransaction();
    }

    //Guarda un objeto en la base como tupla.
    public void guardar(Object objeto) {
        this.session.save(objeto);
        this.tr.commit();
    }

    //Borra un objeto de la base.
    public void borrar(Object objeto) {
        this.session.delete(objeto);
        this.tr.commit();
    }

    //Actualiza un objeto en la base.
    public void actualizar(Object objeto) {
        this.session.update(objeto);
        this.tr.commit();
    }
        public Object getByID(String cuenta, String id) {
        Query resul = this.session.createQuery("FROM " + cuenta + " WHERE id="+id );
        List c = resul.list();
        return c.get(0);
        }
    
    //Devuelve un objeto del tipo de la clase, lo busca por id.
    public Object obtener(Class clase, Serializable i) {
        Object o = this.session.get(clase, i);
        this.tr.commit();

        return o;
    }

    //Actualiza los datos de un objeto desde la base.
    public void refrescar(Object objeto) {
        this.session.refresh(objeto);
        this.tr.commit();
    }

    //Devuelve una lista de objetos de una tabla. 
    //Recibe como parametro el nombre de la tabla a devolver.
    public List obtener(String parametro) {
        Query resul = this.session.createQuery("FROM " + parametro);
        List lista = resul.list();
        return lista;
    }
    
    public List obtenerYFiltrar(String parametro, String cond1) {
        
        Query resul = this.session.createQuery("FROM " + parametro + " a WHERE a."+cond1);
        
        List lista = resul.list();
        return lista;
    }

    //Cierra la conexion. Importante cada vez que ya no usemos la base.
    public void cerrarConexion() {
        this.session.close();
    }

}
