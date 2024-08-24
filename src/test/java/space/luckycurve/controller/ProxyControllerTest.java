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
    void generateClashConfig() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/proxy/generate/clash").param("subscribeUrls", "url"))
                .andExpectAll(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}