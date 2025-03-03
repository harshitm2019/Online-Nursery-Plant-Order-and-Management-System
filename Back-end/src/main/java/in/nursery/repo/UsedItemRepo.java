package in.nursery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nursery.model.UsedItem;

@Repository
public interface UsedItemRepo extends JpaRepository<UsedItem,Long>{

}
