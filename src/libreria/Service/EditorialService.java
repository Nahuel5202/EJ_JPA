/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

(consulta, creación, modificación y eliminación).
 */
package libreria.Service;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.Entity.Editorial;

/**
 *
 * @author PC
 */
public class EditorialService {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();
    
    
    public String crearEditorial(String nombre, Boolean alta) throws Exception{
        
          Editorial editorial = new Editorial();  //creacion deun editorial
       
        editorial.setId(UUID.randomUUID().toString());
        editorial.setNombre(nombre);
        editorial.setAlta(alta);
        
        
        em.getTransaction().begin();
        em.persist(editorial);
        em.getTransaction().commit();
         
        return "Editorial gaurdado";
    }
    
    public Editorial modificarEditorial(String editorial, String nombre, Boolean alta) throws Exception{
        
        Editorial edi = buscarEditorialPorNombre(editorial);
        
        try {
            edi.setNombre(nombre);
            edi.setAlta(alta);
            em.getTransaction().begin();
            em.merge(edi);
            em.getTransaction().commit();
            return edi;
        } catch (Exception e) {
            System.out.println("Error de sistema");
        }
        return null;
    }
    
    public String eliminacionEditorial(String nombre)throws Exception{
        try {
             Editorial e = buscarEditorialPorNombre(nombre);
             em.getTransaction().begin();
             em.remove(e);
             em.getTransaction().commit();
             return "eliminacion del Editorial "+ e;
             
             
        } catch (Exception e) {
            return "Error de sistema";
        }
       
        
        
            }
    
  public Editorial buscarEditorialPorNombre(String nombre) throws Exception{
        try {
             Editorial buscaEditorial = (Editorial) em.createQuery("SELECT e FROM Editorial e"
                 + " WHERE e.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
             
         return buscaEditorial;
        } catch (NoResultException e) {
            System.out.println("no se encontro editorial con ese nombre");
        }
        return null;
      
    }
    

    
    
}
