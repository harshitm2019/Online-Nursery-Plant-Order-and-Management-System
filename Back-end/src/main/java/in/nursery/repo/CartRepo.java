package in.nursery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.Cart;
import jakarta.transaction.Transactional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{

	 // custom query for selecting record where id = ?
	
     @Query("SELECT c from Cart c where c.cid = :cId")
	 public List<Cart> findByCid(Integer cId);
	
     // custom query for selecting record on the basis of plantID and cId
     @Query("SELECT c from Cart c where c.cid = :cId AND c.plantid = :plantId")
     public Cart findQty(Integer cId,Integer plantId);
     
     
     // custom query for deleting the record
     @Transactional
     @Modifying
     @Query("DELETE FROM Cart c where c.plantid = :plantid")
     public void deleteByPlantid(Integer plantid);

     // custom query for deleting the cart of specific customer
     @Transactional
     @Modifying
     @Query("DELETE FROM Cart c where c.cid = :id")
	 public void delete(Integer id); 
     
	
}
