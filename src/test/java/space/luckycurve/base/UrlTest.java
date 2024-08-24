package space.luckycurve.base;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlTest {

    @Test
    public void parseUlrParam() throws MalformedURLException {
        URL url = new URL("hysteria2://ae72ef5f-8ddf-450d-8657-f8a58f08dcc9@www.luckycurve.space:31285?peer=www.luckycurve.space&insecure=0&sni=www.luckycurve.space&alpn=h3#ae72ef5f-singbox_hysteria2");
        System.out.println(url.getQuery());
    }
}
