package capstonedesign.medicalproduct.repository.cart;

import capstonedesign.medicalproduct.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartCustomRepository {

}
