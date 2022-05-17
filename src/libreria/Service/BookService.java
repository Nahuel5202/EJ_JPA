/*
 *Búsqueda de un libro por ISBN.
10) Búsqueda de un libro por Título.
11) Búsqueda de un libro/s  por nombre de Autor.
12) Búsqueda de un libro/s por nombre de Editorial
 */
package libreria.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.Entity.Author;
import libreria.Entity.Book;
import libreria.Entity.Editorial;

/**
 *
 * @author PC
 */
public class BookService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();
    AuthorService aS = new AuthorService();
    EditorialService eS = new EditorialService();

    public String crearBook(Long isbn, String titulo, Integer anio, Integer ejemplares,
            Integer ejemplaresprestados, Integer ejemplaresrestantes, Boolean alta, String author, String editorial) throws Exception{
        
             Author nuevoAuthor =  aS.buscarAuthorPorNombre(author);
        Editorial nuevoEditorial = eS.buscarEditorialPorNombre(editorial);  
        
        
        try {
       
             Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setIsbn(isbn);
        book.setTitulo(titulo);
        book.setAnio(anio);
        book.setEjemplares(ejemplares);
        book.setEjemplaresPrestados(ejemplaresprestados);
        book.setEjemplaresRestantes(ejemplaresrestantes);
        book.setAlta(alta);
        book.setAutor(nuevoAuthor);
        book.setEditorial(nuevoEditorial);

        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();

        return "Libro guardado";
        } catch (Exception e) {
            return "Error, verifique que los datos sean correctos";
        }
       
    }
    
    public Book modificarBook(String book,Long isbn, String titulo, Integer anio, Integer ejemplares,
            Integer ejemplaresprestados, Integer ejemplaresrestantes, Boolean alta, String author, String editorial)throws Exception{
        
       Book newBook = buscarBookPorTitulo(book);
                Author nuevoAuthor =  aS.buscarAuthorPorNombre(author);
        Editorial nuevoEditorial = eS.buscarEditorialPorNombre(editorial);  
        
        try {
            
      
        
            newBook.setTitulo(titulo);
            newBook.setIsbn(isbn);
            newBook.setAnio(anio);
            newBook.setEjemplares(ejemplares);
            newBook.setEjemplaresPrestados(ejemplaresprestados);
            newBook.setEjemplaresRestantes(ejemplaresrestantes);
            newBook.setAlta(alta);
            newBook.setAutor(nuevoAuthor);
            newBook.setEditorial(nuevoEditorial);
            em.getTransaction().begin();
            em.merge(newBook);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            System.out.println("Error de sistema");
        }
        
        
        return newBook;
    }
    
    public String eliminarBook(String book)throws Exception{
        
              Book  bookSupr = buscarBookPorTitulo(book);
        try {
         
            em.getTransaction().begin();
        em.remove(bookSupr);
        em.getTransaction().commit();
        return "eliminacion exitosa"; 
        } catch (Exception e) {
            return "error";
        }
       
    }
    

    public Book buscarBookPorIsbn(Long isbn) throws Exception {
        try {
            Book buscarBookIsbn = (Book) em.createQuery("SELECT b FROM Book b "
                    + "WHERE b.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            return buscarBookIsbn;
        } catch (NoResultException e) {

            System.out.println("no se encontro libro con ese isbn");
        }
        return null;
    }

    public Book buscarBookPorTitulo(String titulo) throws Exception {
        try {
            Book buscarBookTitulo = (Book) em.createQuery("SELECT b FROM Book b "
                    + "WHERE b.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
            return buscarBookTitulo;
        } catch (NoResultException e) {

            System.out.println("no se encontro libro con ese titulo");
        }
        return null;
    }

    public List<String> BooksporAuthor(String author) throws Exception {

        String a;
        Long b;
        String c;
        String d;
        Author buscarAuthor = aS.buscarAuthorPorNombre(author);
        try {
            Collection<Book> buscarBookAuthor = em.createQuery("SELECT b FROM Book b "
                + "WHERE b.autor = :author ").setParameter("author", buscarAuthor).getResultList();
        List<String> Books = new ArrayList();
        for (Book book : buscarBookAuthor) {
            a = book.getTitulo();
            b = book.getIsbn();
            c = book.getAutor().getNombre();
            d = book.getEditorial().getNombre();

            Books.add("titulo: " + a + " ISBN: " + b + " Autor: " + c + " Editorial: " + d );

        }
        return Books;
        } catch (NoResultException e) {
            System.out.println("no se encontro libros con este autor");
        }
        return null;
    }

    public List<String> BooksporEditorial(String editorial) throws Exception {

        String a;
        Long b;
        String c;
        String d;
       Editorial buscarEditorial = eS.buscarEditorialPorNombre(editorial);
        try {
             Collection<Book> buscarBookEditorial = em.createQuery("SELECT b FROM Book b "
                + "WHERE b.editorial = :editorial ").setParameter("editorial", buscarEditorial).getResultList();
        List<String> Books = new ArrayList();
        for (Book book : buscarBookEditorial) {
            a = book.getTitulo();
            b = book.getIsbn();
            c = book.getAutor().getNombre();
            d = book.getEditorial().getNombre();
            Books.add("titulo: " + a + " ISBN: " + b + " Autor: " + c + " Editorial: " + d);
        }
        return Books;
        } catch (NoResultException e) {
            System.out.println("no se encontro libros con este editorial");
        }
       return null;
    }


}
