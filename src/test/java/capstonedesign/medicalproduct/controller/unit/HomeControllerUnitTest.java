package capstonedesign.medicalproduct.controller.unit;

import capstonedesign.medicalproduct.controller.HomeController;
import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.factory.item.ItemFactory;
import capstonedesign.medicalproduct.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HomeControllerUnitTest {

    //    @WebMvcTest(HomeController.class) 사용할 때 사용
    //기본적으로 Service 빈 주입되지 않아 반드시 주입시켜줘야함
    //직접 Service단까지 가서 DB에 값을 얻어오는게 아닌 Service를 Mocking하여
    //서비스까지 가지 않고 개발자가 직접 임의의 값을 리턴하여 테스트할 수 있습니다.
    //@MockBean이란 어노테이션을 사용하면 기존에 있는 Bean을
    //MockBean으로 대체하여 개발자가 테스트할 때 값을 커스터마이징하기 쉬워집니다.
//    @MockBean
//    ItemService sampleItemService;

    @InjectMocks
    HomeController homeController;

    @Mock
    ItemService itemService;

    MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    @DisplayName("검색한 이름에 해당하는 상품을 조회한다.")
    public void findAllByName() throws Exception {
        //given
        ItemSearch itemSearch = new ItemSearch("수술가운");

        //mocking
        given(itemService.findAllByName(any(), ArgumentMatchers.anyInt())).willReturn(ItemFactory.makePageItemDto());

        //when, then
        ResultActions resultActions = mockMvc.perform(get("/"));

        resultActions
        .andExpect(status().isOk());
    }
}
