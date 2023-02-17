package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.TestDB;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.order.OrderRepository;
import capstonedesign.medicalproduct.repository.review.ReviewRepository;
import capstonedesign.medicalproduct.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public abstract class ServiceIntegrationTest {

    protected Member member;
    protected Item item;
    protected Cart cart;
    protected Order order;
    protected Review review;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected CartService cartService;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ReviewService reviewService;

    @Autowired
    protected ReviewRepository reviewRepository;

    @Autowired
    protected EntityManager em;

    @Autowired
    protected TestDB testDB;

    @BeforeEach
    void beforeEach() {
        testDB.init();

        member = testDB.findMember();
        item = testDB.findItem();
        cart = testDB.findCart();
        order = testDB.findOrder();
        review = testDB.findReview();
    }
}
