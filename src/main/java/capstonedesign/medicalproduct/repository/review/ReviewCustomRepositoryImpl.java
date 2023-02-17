package capstonedesign.medicalproduct.repository.review;

import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.domain.entity.QMember;
import capstonedesign.medicalproduct.domain.entity.QReview;
import capstonedesign.medicalproduct.dto.review.QReviewDto;
import capstonedesign.medicalproduct.dto.review.ReviewDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ReviewDto> findAllByMemberId(long memberId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewDto> result = jpaQueryFactory
                                .select(new QReviewDto(review.id, item.id, item.name, item.imageSrc, review.reviewDate,
                                        review.title, review.content, review.uploadFileName, review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(member.id.eq(memberId))
                .fetch();

        return result;
    }

    public List<ReviewDto> findAllByItemId(long itemId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewDto> result = jpaQueryFactory
                .select(new QReviewDto(review.id, member.information.name, review.title, review.content, review.reviewDate,
                                        review.uploadFileName,review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(item.id.eq(itemId))
                .fetch();

        return result;
    }
}
