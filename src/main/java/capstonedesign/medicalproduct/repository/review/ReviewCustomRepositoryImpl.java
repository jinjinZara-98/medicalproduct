package capstonedesign.medicalproduct.repository.review;

import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.domain.entity.QMember;
import capstonedesign.medicalproduct.domain.entity.QReview;
import capstonedesign.medicalproduct.dto.review.QReviewedItemDto;
import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ReviewedItemDto> findAllByMemberId(long memberId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewedItemDto> result = jpaQueryFactory
                                .select(new QReviewedItemDto(review.id, item.id, item.name, item.imageSrc, review.reviewDate,
                                        review.title, review.content, review.uploadFileName, review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(member.id.eq(memberId))
                .fetch();

        return result;
    }

    public List<ReviewedItemDto> findAllByItemId(long itemId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewedItemDto> result = jpaQueryFactory
                .select(new QReviewedItemDto(review.id, member.information.name, review.title, review.content, review.reviewDate,
                                        review.uploadFileName,review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(item.id.eq(itemId))
                .fetch();

        return result;
    }
}
