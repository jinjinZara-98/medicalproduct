package capstonedesign.medicalproduct.controller.Integration;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public abstract class ControllerIntegrationTest {

    protected Member member;
    protected Item item;
    protected Cart cart;
    protected Order order;
    protected Review review;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;

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
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        testDB.init();

        member = testDB.findMember();
        item = testDB.findItem();
        cart = testDB.findCart();
        order = testDB.findOrder();
        review = testDB.findReview();
    }
}
