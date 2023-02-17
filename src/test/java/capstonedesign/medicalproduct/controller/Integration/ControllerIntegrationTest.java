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

/**
 * 컨트롤러 통합 테스트에 사용
 * 모든 통합 테스트 컨트롤러는 이 클래스 상속받는다
 *
 *
 */
@SpringBootTest
@Transactional
/**
 * MockMvc 를 빈으로 등록해준다.
 * 이 어노테이션을 통해 MockMvc 를 Builder 없이 주입받을 수 있음
 * */

/**
 * 오직 Controller에 대해서만 테스트 검증을 할 때는 @WebMvcTest 를 사용
 * @Service, @Repository 등 JPA도 사용해야 한다면 @SpringBootTest 어노테이션을 사용해야 한다.
 * 하지만 이 어노테이션만 가지고는 MockMvc를 사용할 수 없다. 따라서 어노테이션이 하나 더 필요하다. -> @AutoConfigureMockMvc
 * 전체 테스트보다는 가볍다
 * @WebMvcTest(HomeController.class)
 *
 * 테스트를 위한 MockMvc 유틸리티를 자동으로 구성할 수 있음
 * 스프링 부트가 MockMvc 자동 구성하게 함. 결과적으로 MockMvc 형식의 객체가 스프링 컨텍스트에 추가됨
 * 이게 없으면 MockMvc 스프링 컨테이너에 없으니 밑에 MockMvc 객체 주입받지 못함
 *
 * 웹 애플리케이션에서 컨트롤러를 테스트할 때, 서블릿 컨테이너를 모킹하기 위해서는 @WebMvcTest를 사용하거나 @AutoConfigureMockMvc를 사용하면 된다.
 * 웹 환경에서 컨트롤러를 테스트하려면 반드시 서블릿 컨테이너가 구동되고 DispatcherServlet 객체가 메모리에 올라가야 하지만,
 * 서블릿 컨테이너를 모킹하면 실제 서블릿 컨테이너가 아닌 테스트용 모형 컨테이너를 사용하기 때문에 간단하게 컨트롤러를 테스트할 수 있다.
 *
 * @SpringBootTest 에는 웹 애플리케이션 테스트를 지원하는 webEnvironment 속성이 있다.
 * 이 속성을 생략하면 기본값으로 WebEnvironment.MOCK이 설정되어 있는데, 이 설정에 의해서 서블릿 컨테이너가 모킹된다.
 * @SpringBootTest(webEnvironment=WebEnvironment.MOCK) 설정으로 모킹한 객체를 의존성 주입받으려면
 * @AutoCOnfigureMockMvc를 클래스 위에 추가 해야
 *
 *  @WebMvcTest와 가장 큰 차이점은은 컨트롤러뿐만 아니라 테스트 대상이 아닌 @Service나 @Repository가 붙은 객체들도 모두 메모리에 올린
 *  간단하게 테스트하기 위해서는 @AutoConfigureMockMvc가 아닌 @WebMvcTest를 사용해야
 * @WebMvcTest는 @SpringBootTest와 같이 사용될 수 없다. 왜냐하면 각자 서로의 MockMvc를 모킹하기 때문에 충돌이 발생하기 때문
 * */
@AutoConfigureMockMvc
public abstract class ControllerIntegrationTest {

    protected Member member;
    protected Item item;
    protected Cart cart;
    protected Order order;
    protected Review review;

    @Autowired
    protected WebApplicationContext context;

    /**
     * 웹 API 를 테스트할 때 사용, 스프링 MVC 테스트의 시작점
     * 이 객체를 통해 HTTP GET, POST 등에 대한 API 테스트 가능
     * 톰캣 WAS 사용
     * 엔드포인트 동작에 관한 테스트를 편리하게 구현할 수 있는
     *
     * @WebMvcTest 를 사용할 때는 무조건 MockMvc 사용해야함
     * @WebMvcTest 는 웹과 관련된 빈들만 테스팅에 로딩하여 슬라이싱 테스트하는
     */
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
