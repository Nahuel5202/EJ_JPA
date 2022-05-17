package libreria;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import libreria.Entity.Author;

import libreria.Service.AuthorService;
import libreria.Service.BookService;
import libreria.Service.EditorialService;

/**
 *
 * @author PC
 */
public class Libreria {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("----------------------------------------------------------------------------- \n"
                + "metodos ejecutados a prueba");

        AuthorService aS = new AuthorService();
        EditorialService eS = new EditorialService();
        BookService bS = new BookService();

        System.out.println(eS.buscarEditorialPorNombre("nombre editorial2").getNombre());

        System.out.println(aS.buscarAuthorPorNombre("nombre").getNombre() + "  " + aS.buscarAuthorPorNombre("nombre").getId());

        System.out.println("-----------------------------------------------------------------------------");

        Author au = aS.buscarAuthorPorNombre("n");

        System.out.println(au.getId());
     

        System.out.println("\t \t MENU\n  __________________________________________\n");
                opciones();
    

      
       
     
    }

    public static void opciones() throws Exception{
        
            System.out.println(" 1)Libros \n 2)Autores \n 3)Editoriales \n 4) Salir");
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        String opcion = leer.next();

        System.out.println("---------------------------------");
           switch (opcion) {

            case "1":

                libros();
                break;

            case "2":
                autores();
                break;
            case "3":
                editoriales();
                break;
                case"4":
                    System.out.println("adios");
                    break;

        }
    }
    
    
    public static void libros() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        BookService bS = new BookService();

        System.out.println("\t \t  elija las siguientes opciones \n_______________________________________\n");
        System.out.println(" 1- Crear libro \n 2- modificar libro \n 3- eliminar libro \n 4- buscar libro por titulo \n 5- buscar libro por isbn \n "
                + "6- buscar libro por autor \n 7- busacr libro por editorial \n 8- volver");
        String var = leer.next();

        switch (var) {
            case "1":
                
                System.out.println("ingrese los datos \n-----_______________________-----");
                 System.out.println("Isbn: "); Long isbn = leer.nextLong();
                 System.out.println(" Titulo: "); String titulo = leer.next();
                 System.out.println(" año: "); Integer anio = leer.nextInt();
                 System.out.println(" ejemplares: "); Integer ejemplares = leer.nextInt();
                 System.out.println(" ejemplares prestados: "); Integer ejemplaresPrestados = leer.nextInt();
                 System.out.println(" ejemplares restantes: "); Integer ejemplaresRestantes = leer.nextInt();
                 System.out.println("Alta: "); Boolean alta = leer.nextBoolean();
                 System.out.println("Autor: "); String author = leer.next();
                 System.out.println("Editorial: "); String editorial = leer.next();
                
                bS.crearBook(isbn, titulo, anio, ejemplares, ejemplaresPrestados,ejemplaresRestantes, alta, author, editorial);
                break;
            case "2":
                System.out.println("\t \t ---ingrese el nombre del libro y a continuacion los datos a reeemplazar ---");
                System.out.println("Libro a cambiar: "); String book = leer.next();
                 System.out.println("Isbn: "); Long isbn1 = leer.nextLong();
                 System.out.println(" Titulo: "); String titulo1 = leer.next();
                 System.out.println(" año: "); Integer anio1 = leer.nextInt();
                 System.out.println(" ejemplares: "); Integer ejemplares1 = leer.nextInt();
                 System.out.println(" ejemplares prestados: "); Integer ejemplaresPrestados1 = leer.nextInt();
                 System.out.println(" ejemplares restantes: "); Integer ejemplaresRestantes1 = leer.nextInt();
                 System.out.println("Alta: "); Boolean alta1 = leer.nextBoolean();
                 System.out.println("Autor: "); String author1 = leer.next();
                 System.out.println("Editorial: "); String editorial1 = leer.next();
                
                bS.modificarBook(book ,isbn1, titulo1, anio1, ejemplares1, ejemplaresPrestados1,ejemplaresRestantes1, alta1, author1, editorial1);
                break;
            case "3":
                System.out.println(" \t \t --- ingrese el nombre del libro a eliminar--- \n Titulo: ");
                String tituloElim = leer.next();
                
                bS.eliminarBook(tituloElim);
                break;
            case "4":
                System.out.println("Ingrese el titulo a buscar");
                bS.buscarBookPorTitulo(leer.next());
                break;
            case "5":
                System.out.println("Ingrese el ISBN a buscar");
                bS.buscarBookPorIsbn(leer.nextLong());
            case "6":
                System.out.println("ingrese el autor ");
                bS.BooksporAuthor(leer.next());
                break;
            case "7":
                System.out.println("ingrese el editorial");
                bS.BooksporEditorial(leer.next());
                break;
            case "8":
                System.out.println("____________________________________________");
                opciones();
                break;
        }

    }

    public static void autores() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        AuthorService aS = new AuthorService();

        System.out.println("\t \t  elija las siguientes opciones \n_______________________________________\n");
        System.out.println(" 1- Crear autor \n 2- modificar autor \n 3- eliminar autor \n 4- buscar autor \n 5- volver");

        String var = leer.next();

        switch (var) {
            case "1":
                System.out.println(" ingrese el nombre del autor y si el alta es TRUE o FALSE");
                aS.crearAuthro(leer.next(), leer.nextBoolean());
                break;
            case "2":
                System.out.println("Ingrese el libro a modificar, su nuevo nombre y su alta");
                aS.modificarAuthor(leer.next(), leer.next(), leer.nextBoolean());
                break;
            case "3":
                System.out.println("ingrese el nombre del autor a eliminar");
                aS.eliminarAuthot(leer.next());
                break;
            case "4":
                System.out.println("ingrese el nombre del autor a buscar");
                aS.buscarAuthorPorNombre(leer.next());
            case "5":
                System.out.println("__________________________________________");
                opciones();
                break;

        }

    }

    public static void editoriales() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        EditorialService eS = new EditorialService();
        
        System.out.println("\t \t  elija las siguientes opciones \n_______________________________________\n");
        System.out.println(" 1- Crear editorial \n 2- modificar editorial \n 3- eliminar editorial \n 4- buscar editorial \n 5- volver");
        String var = leer.next();
        
                switch (var) {
            case "1":
                System.out.println("ingrese el nombre de la Editorial y tu alta si es TRUE o FALSE");
                eS.crearEditorial(leer.next(), leer.nextBoolean());
                break;
            case "2":
                System.out.println(" ingrese el Editorial a modificar, su nuevo nombre y su alta");
                eS.modificarEditorial(leer.next(), leer.next(), leer.nextBoolean());
                break;
            case "3":
                System.out.println("ingrese el nombre de la Editorial a eliminar");
                eS.eliminacionEditorial(leer.next());
                break;
            case "4":
                System.out.println("ingrese el nombre de la Editoraila a buscar");
                eS.buscarEditorialPorNombre(leer.next());
            case "5":
                System.out.println("___________________________________________");
                opciones();
                break;

        }

    }
}
