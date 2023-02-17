package capstonedesign.medicalproduct.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateMember(Member member) {
        this.member = member;
        if(member.getCarts() != null) {
            member.getCarts().add(this);
        }
    }

    public void updateItem(Item item) {
        this.item = item;
        if(item.getCarts() != null) {
            item.getCarts().add(this);
        }
    }

    public void enterQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }

    public Cart(int quantity, Member member, Item item) {
        this.quantity = quantity;
        this.member = member;
        this.item = item;
    }

    public static Cart createCart(Member member, Item item, int quantity) {
        Cart cart = new Cart();
        cart.updateMember(member); cart.updateItem(item); cart.enterQuantity(quantity);

        return cart;
    }
}