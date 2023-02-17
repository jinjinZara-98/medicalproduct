package capstonedesign.medicalproduct.repository.item;

import capstonedesign.medicalproduct.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemCustomRepository{

}
