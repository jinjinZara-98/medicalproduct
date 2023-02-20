package capstonedesign.medicalproduct.controller.unit;

import capstonedesign.medicalproduct.controller.HomeController;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HomeControllerUnitTest {

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

        //mocking
        given(itemService.findAllByName(any(), ArgumentMatchers.anyInt())).willReturn(ItemFactory.makePageItemDto());

        //when, then
        ResultActions resultActions = mockMvc.perform(get("/"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("itemSearch"));
    }
}
