package space.luckycurve.service;

import org.junit.jupiter.api.Test;
import space.luckycurve.dto.url.Url;

import static org.junit.jupiter.api.Assertions.*;

class UrlParserTest {

    @Test
    void parseUrl() {
        Url url = UrlParser.parseUrl("hysteria2://ae72ef5f-8ddf-450d-8657-f8a58f08dcc9@www.luckycurve.space:31285?peer=www.luckycurve.space&insecure=0&sni=www.luckycurve.space&alpn=h3#ae72ef5f-singbox_hysteria2");
        System.out.println(url);
    }
}