package hibernate_update.hibernate_update;

import jakarta.persistence.*;

public class UseHibernate 
{
    public static void main( String[] args )
    {
    	try {
    		//add name of the persistence unit when creating an EntityManagerFactory
    		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        
    		EntityManager em = emf.createEntityManager();
        
    		//new ProductosDAO
    		ProductosDAO dao = new ProductosDAO(em);
        
    		//new producto
    		Productos producto = new Productos("MediaMarkt RAM", 500);
        
    		//insert product in database
    		dao.insertProducto(producto);
        
    		//delete product from the database
    		dao.deleteProducto(11);
    		
    		//update product
    		dao.updateProducto(5, "Software guay", 250);
    		
    		//show products in list
    		dao.showProducts();
        
    		em.close();
    		emf.close();
    	}
    	catch(Exception ex) {
    		System.out.println("Error in main class: " +ex);
    		ex.printStackTrace();
    	}
    }
}
