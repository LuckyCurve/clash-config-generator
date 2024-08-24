package space.luckycurve.controller;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ProxyControllerTest {

    @Resource
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void generateClashHysteria2Config() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/proxy/generate/clash")
                        .param("subscribeUrls", "hysteria2://ae72ef5f-8ddf-450d-8657-f8a58f08dcc9@www.luckycurve.space:31285?peer=www.luckycurve.space&insecure=0&sni=www.luckycurve.space&alpn=h3#ae72ef5f-singbox_hysteria2")
                )
                .andExpectAll(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void generateClashTUIC5Config() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/proxy/generate/clash")
                        .param("subscribeUrls", "tuic://www.luckycurve.space:34192?uuid=3852f37f-3402-4a61-ad70-aad77e28a7f7&password=3852f37f-3402-4a61-ad70-aad77e28a7f7&congestion-controller=bbr")
                )
                .andExpectAll(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}