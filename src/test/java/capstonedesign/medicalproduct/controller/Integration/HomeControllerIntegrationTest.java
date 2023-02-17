package capstonedesign.medicalproduct.controller.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @RunWith(SpringRunner.class)
 * @SpringBootTest 있어야 스프링 인티그레이션 해서 스프링 올려 테스트 가능
 * 없으면 @Autowired 실패함, 스프링 안 띄워서 스프링이 관리하고 있는 빈이 주입이 안도니
 * JUnit 실행할 떄 스프링이랑 엮어서 실행한다는 의미
 * JPA 가 DB까지 직접 도는 것을 보여주기 위해 메모리 모드로 DB까지 엮어 테스트
 *
 * 테스트를 진행할 떄 JUnit 에 내장된 실행자 외 다른 실행자 실행
 * 여기서는 SpringRunner 라는 스프링 실행자 사용
 * 즉 스프링부트 테스트와 JUnit 사이에 연결자 역할
 *
 * 해당 어노테이션을 사용하면 JUnit의 러너를 사용하는게 아니라 지정된 SpringRunner 클래스를 사용
 *
 *  jUnit에 내장된 러너를 사용하는 대신 SpringRunner라는 스프링 실행 러너를 이용
 *
 * @SpringBootTest 는 테스트를 위한 스프링 컨텍스트를 스프링 부트가 관리하게 함
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class HomeControllerIntegrationTest extends ControllerIntegrationTest{

    @Test
    public void home() throws Exception{

        //perform 안에 param() 사용가능, 파라미터 이름과 값을 넣어줘야
        //andDo() 는 mockMvc 요청을 한 뒤 행동을 지정하는
        //print() 로 결과를 로그나 sout 로 출력 가능
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(MockMvcResultHandlers.print())
                //andExpect() mockMvc.perform의 결과 검증
                //andExpect() 안에 content() 는 응답 본문의 내용 검증
                //jsonpath() "$.이름" is(값), json 응답값을 필드별로 검증할 수 있는
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}