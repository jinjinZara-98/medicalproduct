package capstonedesign.medicalproduct.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageSrc;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int price;

    @Lob @Column(nullable = false)
    private String introduction;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderedItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    public Item(String category, String imageSrc, String introduction, String name, int price) {
        this.category = category;
        this.imageSrc = imageSrc;
        this.introduction = introduction;
        this.name = name;
        this.price = price;
    }
}