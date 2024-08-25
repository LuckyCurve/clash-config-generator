package space.luckycurve.controller;

import com.google.common.collect.Lists;
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
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProxyControllerTest {

    @Resource
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    Yaml yaml = new Yaml();

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void generateClashHysteria2Config() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/proxy/generate/clash").param("subscribeUrls", "hysteria2://ae72ef5f-8ddf-450d-8657-f8a58f08dcc9@www.luckycurve.space:31285?peer=www.luckycurve.space&insecure=0&sni=www.luckycurve.space&alpn=h3#ae72ef5f-singbox_hysteria2"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        Map<String, ?> res = yaml.load(response);

        Map<?, ?> proxy = (Map<?, ?>) ((List<?>) res.get("proxies")).getFirst();
        assertEquals(proxy.get("type"), "hysteria2");
        assertEquals(proxy.get("name"), "www.luckycurve.space:31285:hysteria2");
        assertEquals(proxy.get("server"), "www.luckycurve.space");
        assertEquals(proxy.get("port"), 31285);
        assertEquals(proxy.get("password"), "ae72ef5f-8ddf-450d-8657-f8a58f08dcc9");
        assertEquals(proxy.get("sni"), "www.luckycurve.space");
        assertEquals(proxy.get("type"), "hysteria2");
        assertEquals(proxy.get("alpn"), Lists.newArrayList("h3"));

        List<?> proxyGroups = (List<?>) res.get("proxy-groups");

        Map<?, ?> proxyGroup = (Map<?, ?>) proxyGroups.getFirst();
        assertEquals(proxyGroup.get("name"), "\uD83D\uDC8D PROXY");
        assertEquals(proxyGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:31285:hysteria2", "\uD83C\uDF40 AUTO_SELECT", "DIRECT"));
        assertEquals(proxyGroup.get("type"), "select");

        Map<?, ?> autoGroup = (Map<?, ?>) proxyGroups.get(1);
        assertEquals(autoGroup.get("name"), "\uD83C\uDF40 AUTO_SELECT");
        assertEquals(autoGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:31285:hysteria2"));
        assertEquals(autoGroup.get("type"), "url-test");
        assertEquals(autoGroup.get("interval"), 300);
        assertEquals(autoGroup.get("tolerance"), 50);
        assertEquals(autoGroup.get("url"),"http://www.gstatic.com/generate_204");

        Map<?, ?> defaultGroup = (Map<?, ?>) proxyGroups.get(2);
        assertEquals(defaultGroup.get("name"), "\uD83C\uDFAF DEFAULT");
        assertEquals(defaultGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:31285:hysteria2", "\uD83C\uDF40 AUTO_SELECT", "DIRECT"));
        assertEquals(defaultGroup.get("type"), "select");
    }

    @Test
    void generateClashTUIC5Config() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/proxy/generate/clash")
                        .param("subscribeUrls", "tuic://www.luckycurve.space:34192?uuid=3852f37f-3402-4a61-ad70-aad77e28a7f7&password=3852f37f-3402-4a61-ad70-aad77e28a7f7&congestion-controller=bbr&alpn=h3")
                )
                .andExpectAll(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        Map<String, ?> res = yaml.load(response);

        Map<?, ?> proxy = (Map<?, ?>) ((List<?>) res.get("proxies")).getFirst();
        assertEquals(proxy.get("type"), "tuic");
        assertEquals(proxy.get("name"), "www.luckycurve.space:34192:tuic");
        assertEquals(proxy.get("server"), "www.luckycurve.space");
        assertEquals(proxy.get("port"), 34192);
        assertEquals(proxy.get("uuid"), "3852f37f-3402-4a61-ad70-aad77e28a7f7");
        assertEquals(proxy.get("password"), "3852f37f-3402-4a61-ad70-aad77e28a7f7");
        assertEquals(proxy.get("alpn"), Lists.newArrayList("h3"));
        assertEquals(proxy.get("congestion-controller"), "bbr");

        List<?> proxyGroups = (List<?>) res.get("proxy-groups");

        Map<?, ?> proxyGroup = (Map<?, ?>) proxyGroups.getFirst();
        assertEquals(proxyGroup.get("name"), "\uD83D\uDC8D PROXY");
        assertEquals(proxyGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:34192:tuic", "\uD83C\uDF40 AUTO_SELECT", "DIRECT"));
        assertEquals(proxyGroup.get("type"), "select");

        Map<?, ?> autoGroup = (Map<?, ?>) proxyGroups.get(1);
        assertEquals(autoGroup.get("name"), "\uD83C\uDF40 AUTO_SELECT");
        assertEquals(autoGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:34192:tuic"));
        assertEquals(autoGroup.get("type"), "url-test");
        assertEquals(autoGroup.get("interval"), 300);
        assertEquals(autoGroup.get("tolerance"), 50);
        assertEquals(autoGroup.get("url"),"http://www.gstatic.com/generate_204");

        Map<?, ?> defaultGroup = (Map<?, ?>) proxyGroups.get(2);
        assertEquals(defaultGroup.get("name"), "\uD83C\uDFAF DEFAULT");
        assertEquals(defaultGroup.get("proxies"), Lists.newArrayList("www.luckycurve.space:34192:tuic", "\uD83C\uDF40 AUTO_SELECT", "DIRECT"));
        assertEquals(defaultGroup.get("type"), "select");
    }
}