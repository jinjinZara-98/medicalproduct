package capstonedesign.medicalproduct;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.dto.order.OrderRequestDto;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.order.OrderRepository;
import capstonedesign.medicalproduct.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDB {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    Information information;

    Member joinedMember;

    Item savedItem;
    Item savedItem2;

    Cart savedCart;

    Order savedOrder;

    Review savedReview;

    @Transactional
    public void init() {
        initItem();
        initMember();
        initCart();
        initOrder();
        initReview();
    }

    private void initItem() {
        savedItem = itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                "수술시 시술자가 입는 입는 내의입니다. 린넨 소재이므로 세탁 후 재사용이 가능합니다.", "수술가운", 20000));
        savedItem2 = itemRepository.save(new Item("의료기기", "http://www.yolomarket.kr/data/item/T170602/thumb-dr09_1446_250x250.gif",
                "환자의 몸안의 소리를 들어 신체를 체크합니다. 환자의 가슴에 헤드를 대고 귀걸이를 통해 맥박을 듣습니다.", "청진기", 90000));
        itemRepository.save(new Item( "의료기기", "http://www.yolomarket.kr/data/item/G170715/thumb-dr09_788_4_320x320.jpg",
                "환자가 수액을 맞고 있을때 이동을 원하면 링겔대 위에 걸이에 걸고서 이동가능. 환자의 수액제를 걸어놓고 이동을 할수 있게 하는 제품", "링겔대", 75000));
        itemRepository.save(new Item("의료기기", "http://www.yolomarket.kr/data/item/G200202/thumb-scc_320x320.jpg",
                "진료시 또는 수술시 여러 제품을 절단 할때 사용한다. 반드시 정해진 용도로만 사용하며 이외의 용도로 무리하게 사용하지 않도록한다", "외과가위", 80000));
        itemRepository.save(new Item("의료기기", "http://www.yolomarket.kr/data/item/G190201/thumb-67aV64yA6rCA7JyE_320x320.jpg",
                "붕대를 자르기위한 기기. 반드시 정해진 용도로만 사용하며 이외의 용도로 무리하게 사용하지 않도록한다", "붕대가위", 7500));
        itemRepository.save(new Item("의료기기", "http://www.yolomarket.kr/data/item/G181003/thumb-65Oc66CI7Iux7Ys7IWJ_320x320.jpg",
                "진료시 사용되는 핀셋", "드레싱핀셋", 4000));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G190110/thumb-66mU7Iqk64Kg15_320x320.jpg",
                "인체조직의 절단 및 절개 등에 사용하는 수동식 의료용 칼로서 일회용", "메스날 50개", 22000));
        itemRepository.save(new Item("의료소모품", " http://www.yolomarket.kr/data/item/O190701/thumb-f1_320x320.jpg",
                "본 제품은 일회용이므로 재사용을 금지하며, 사용후 즉시 폐기한다.", "일회용주사기 100개", 9500));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G190503/thumb-bd1_320x320.jpg",
                "본 제품은 일회용이므로 재사용을 금지하며, 사용후 즉시 폐기한다.", "일회용주사침 100개", 4000));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G170405/thumb-IMG_3205_320x320.jpg",
                "본 제품은 일회용이므로 재사용을 금지하며, 사용후 즉시 폐기한다.", "일회용주사침 100개", 4000));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G170509/thumb-7IiY7JWh7Jew6rKw7KSEExtensionlineDEHPFREE90cm_320x320.jpg",
                "세트내의 공기는 제거 / 의사의 지시에 따라 사용하십시오 ", "수액연결줄 25개", 7000));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G180708/thumb-1C03179140_L_320x320.jpg",
                "상처나 환부 드레싱용. 상처부위 소독시 수분 및 각종 분비물을 흡수한다. 알콜에 적셔서 환부 소독시에 사용된다. 일회에 한하여 사용", "코튼볼 500g", 5000));
        itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G170713/thumb-1J05458110_L_320x320.jpg",
                "외상 치료용 , 상처나 환부 드레싱용. 일회에 한하여 사용", "절단거즈 100매", 8500));
        itemRepository.save(new Item("의약품", "https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/product/1445831641/B.jpg?355000000",
                "변비, 장내용물 배설하게 해주는 용도. 성인은 농글리세린으로서 1회 15g을 6 ~ 11세는 10g 1 ~ 5세는 5g을 항문에 주입. 6~11세", "그린농글리세린", 5000));
        itemRepository.save(new Item("의료기기", "https://mblogthumb-phinf.pstatic.net/MjAxOTAzMjhfMjE1/MDAxNTUzNzYwNDAzNDMy.BKsdXG1BalumKf4LbVwRdm3UEUqBuULMyh8Lo9WQS5gg.CJADIaohZr6v5cJlhErAZjqbdAbgSK0l3MESWGkiDD0g.JPEG.ranth/D100.jpg?type=w800",
                "거동이 어렵고 불편한 환자가 이용하는 장비", "휠체어", 150000));
        itemRepository.save(new Item("의료기기", "http://www.drmro.com/shop/data/goods/DM4201410400_01.jpg",
                "거동이 어렵고 불편한 환자가 이용하는 장비", "알루미늄 목발", 25000));
        itemRepository.save(new Item("의료소모품", "http://th4.tmon.kr/thumbs/image/1f8/e1b/e14/ab0f5d337_700x700_95_FIT.jpg",
                "미세먼지 차단과 호흡기 질병 감염 예방", "일회용 덴탈 마스크 50개", 35000));
        itemRepository.save(new Item("의료기기", "http://images.coocha.co.kr/upload/2022/09/11sthot/02/thumb4_855161789.jpg",
                "이동용 커튼. 바퀴가 달려있어 자유롭게 이동가능", "칸막이 커튼", 350000));
        itemRepository.save(new Item("의약품", "http://img4.tmon.kr/cdn3/deals/2020/08/24/2764495326/2764495326_front_90439d37db.jpg",
                "피부소독, 수술부위소독, 의룡용 용구 소독 등 여러가지로 사용 가능", "소독용 에탄올", 2000));
        itemRepository.save(new Item("의료기기", "http://m.openmedical.co.kr/web/product/big/201910/13df3670407d29818ff377cbd09df851.jpg",
                "수술시 사용하는 스탠드", "전기 스탠드", 120000));
        itemRepository.save(new Item("피부용품", "https://abib.com/web/product/small/202007/f5016ee9a58f555371adcd46c69519b1.jpg",
                "수분공급과 보new Item(습력을 강화시켜 촉촉한 피부로 유지시켜 줍니다.", "마스크 팩", 2500));
        itemRepository.save(new Item("건강기능식품", "https://www.amc.seoul.kr/asan/imageDown/healthstory/20180320?fileName=%EB%B9%84%ED%83%80%EB%AF%BC.jpg",
                "유해산소로부터 세포를 보호. 결합조직 형성과 기능유지. 철 흡수", "비타민 C", 15000));
    }

    private void initMember() {
        information = new Information("홍길동", "01012341234", "충북 충주시 대소원면 대학로 50",
                "예성생활관", "홍길동", "농협", "3520459732493");
        Member member = new Member("gildong123", passwordEncoder.encode("gildong"), information, "gildong123@naver.com", MemberRole.ROLE_USER);

        joinedMember = memberRepository.save(member);
    }

    private void initCart() {

        savedCart = cartRepository.save(Cart.createCart(joinedMember, savedItem2, 2));
    }

    private void initOrder() {

        OrderItem orderItem = new OrderItem(savedItem, 20000, 1);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        OrderRequestDto orderDto = new OrderRequestDto("홍길동", "01012341234", "충북 충주시 대소원면 대학로 50",
                "예성생활관", "", "홍길동", " 농협,", "3520459732493");

        savedOrder = orderRepository.save(Order.createOrder(joinedMember, orderDto, orderItems));
    }

    private void initReview() {

        savedReview = reviewRepository.save(Review.createReview(joinedMember, savedItem, "좋아요", "잘 받았습니다.",
                "8780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png"));
    }

    @Transactional(readOnly = true)
    public Member findMember() {
        return memberRepository.findByLoginId("gildong123").orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional(readOnly = true)
    public Item findItem() {
        return itemRepository.findById(savedItem.getId()).orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional(readOnly = true)
    public Cart findCart() {
        return cartRepository.findById(savedCart.getId()).orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional(readOnly = true)
    public Order findOrder() {
        return orderRepository.findById(savedOrder.getId()).orElseThrow(() -> new IllegalArgumentException());
    }

    @Transactional(readOnly = true)
    public Review findReview() {
        return reviewRepository.findById(savedReview.getId()).orElseThrow(() -> new IllegalArgumentException());
    }
 }
