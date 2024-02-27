package hibernate_update.hibernate_update;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.*;
import jakarta.transaction.*;

@Transactional
public class ProductosDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//constructor that takes the entity manager
	public ProductosDAO(EntityManager em) {
		this.entityManager = em;
	}
	
	//method that inserts a new product in the table
	public void insertProducto(Productos producto) {
		entityManager.persist(producto);
		System.out.println("Added new product with name: " + producto.getNombre());
		System.out.println("====================================================");
	}
	
	//method that removes a record from the table based on its ID
	public void deleteProducto(int id_producto) {
		Productos producto = entityManager.find(Productos.class, id_producto);
		if (producto != null) {
			System.out.println("Deleted product with name: " + producto.getNombre());
			entityManager.remove(producto);
		}
		else System.out.println("Could not find a product with that ID");	
		System.out.println("====================================================");
	}
	
	//method that receives an ID of a product and the new name and price to assign
	public void updateProducto(int id_producto, String nombre, double precio) {
		Productos producto = entityManager.find(Productos.class, id_producto);
		if (producto != null) { //updates only if it finds the ID
			System.out.println("Found product with ID: " + id_producto + ", Name: " + producto.getNombre() + ", Price: " + producto.getPrecio());
			System.out.println("====================================================");
			producto.setNombre(nombre); producto.setPrecio(precio);
			System.out.println("Updating to Name: " + nombre + ", Price: " + precio);
			System.out.println("====================================================");
			entityManager.merge(producto);
			System.out.println("Product updated");
		}
		else System.out.println("Could not find a product with that ID");
		System.out.println("====================================================");
	}
	
	//method that shows all products on the table on screen
	public void showProducts() {
		List all_products = entityManager.createQuery("FROM Productos").getResultList();
		Iterator iterator = all_products.iterator();
		System.out.println("Looking for products...");
		System.out.println("====================================================");
		
		while(iterator.hasNext()) { //showing products row by row
			Productos producto = (Productos) iterator.next();
			System.out.println("ID: " + producto.getId() + "\tName: " + producto.getNombre()
			+ "\tPrice: " + producto.getPrecio());
			System.out.println("====================================================");
		}
		System.out.println("Finished searching for products");
		System.out.println("====================================================");
	}
}
