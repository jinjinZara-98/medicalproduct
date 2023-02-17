package capstonedesign.medicalproduct.repository.review;

import capstonedesign.medicalproduct.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
}
