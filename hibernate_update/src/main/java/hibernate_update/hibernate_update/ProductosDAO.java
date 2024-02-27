package hibernate_update.hibernate_update;

import jakarta.persistence.*;
import jakarta.transaction.*;

@Transactional
public class ProductosDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//construct that takes the entity manager
	public ProductosDAO(EntityManager em) {
		this.entityManager = em;
	}
	
	public void insertProducto(Productos producto) {
		entityManager.persist(producto);
		System.out.println("Added new product with name: " + producto.getNombre());
		System.out.println("====================================================");
	}
	
	public void deleteProducto(int id_producto) {
		Productos producto = entityManager.find(Productos.class, id_producto);
		if (producto != null) {
			System.out.println("Deleted product with name: " + producto.getNombre());
			entityManager.remove(producto);
		}
		else System.out.println("Could not find a product with that ID");	
		System.out.println("====================================================");
	}
}
