package space.luckycurve.service;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import space.luckycurve.dto.url.Url;

import java.net.URI;

public class UrlParser {
    public static Url parseUrl(String url) {

        URI uri = URI.create(url);

        String schema = uri.getScheme();
        String userinfo = uri.getUserInfo();
        String host = uri.getHost();
        Integer port = uri.getPort();
        String path = uri.getPath();
        MultiValueMap<String, String> query = parseQuery(uri.getQuery());
        String fragment = uri.getFragment();

        return new Url(schema, userinfo, host, port, path, query, fragment);
    }

    private static MultiValueMap<String, String> parseQuery(String query) {
        LinkedMultiValueMap<String, String> res = new LinkedMultiValueMap<>();

        if (StringUtils.hasText(query)) {
            String[] arguments = query.split("&");

            for (String argument : arguments) {
                String[] keyValuePair = argument.split("=");
                Assert.isTrue(keyValuePair.length == 2, MessageFormatter.format("{} is illegal", argument).toString());
                res.add(keyValuePair[0], keyValuePair[1]);
            }
        }

        return res;
    }
}
