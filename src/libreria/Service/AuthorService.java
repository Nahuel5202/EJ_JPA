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
import libreria.Entity.Author;

/**
 *
 * @author PC
 */
public class AuthorService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();

    public String crearAuthro(String nombre, Boolean alta) throws Exception{

        Author author = new Author();   //creacion de un autor

        author.setId(UUID.randomUUID().toString());
        author.setNombre(nombre);
        author.setAlta(alta);

        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        return "Autor guardado";
    }
    
    public Author modificarAuthor(String author,String nombre, Boolean alta) throws Exception{
        
       Author modiAuthor = buscarAuthorPorNombre(author);
        try {
                
        modiAuthor.setNombre(nombre);
       modiAuthor.setAlta(alta);
        em.getTransaction().begin();
        em.merge(modiAuthor);
        em.getTransaction().commit();
        
        return modiAuthor;
        } catch (Exception e) {
            System.out.println("Error del sistema");
        }
    return null;
    }
    
    
    public String eliminarAuthot(String nombre) throws Exception{
        
        try {
            Author eliminarA = (Author) em.createQuery("SELECT a FROM Author a"
                    + " WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            em.getTransaction().begin();
            em.remove(eliminarA);
            em.getTransaction().commit();
            return "eliminacion con exito";
        } catch (Exception e) {
          return  "Error de sistema";
        }
     
    }
    

    public Author buscarAuthorPorNombre(String nombre) throws Exception {
        try {
            Author buscaAuthor = (Author) em.createQuery("SELECT a FROM Author a"
                    + " WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            return buscaAuthor;
        } catch (NoResultException e) {
            System.out.println("no se encontro autor con ese nombre");
        }
        return null;
    }

}
