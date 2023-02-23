package capstonedesign.medicalproduct.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @Column(nullable = false)
    String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private String uploadFileName;

    @Column(nullable = false)
    private String storeFileName;

    @Column(nullable = false)
    private LocalDateTime reviewDate;

    public Review(String title, String content, Member member, Item item,
                  String uploadFileName, String storeFileName, LocalDateTime reviewDate) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.item = item;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.reviewDate = reviewDate;
    }

    @Builder
    protected Review(String title, String content, LocalDateTime reviewDate, String uploadFileName, String storeFileName) {
        this.title = title;
        this.content = content;
        this.reviewDate = reviewDate;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void updateMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    public void updateItem(Item item) {
        this.item = item;
        item.getReviews().add(this);
    }

    public static Review createReview(Member member, Item item, String title, String content,
                                      String uploadFileName, String storeFileName) {

        Review review = Review.builder()
                .title(title)
                .content(content)
                .reviewDate(LocalDateTime.now())
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName).build();

        review.updateMember(member); review.updateItem(item);

        return review;
    }
}
