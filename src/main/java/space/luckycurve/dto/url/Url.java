package space.luckycurve.dto.url;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

/**
 * URL = scheme ":" "//" [userinfo "@"] host [":" port] path ["?" query] ["#" fragment]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    private String schema;

    /**
     * Optional
     */
    private String userInfo;

    private String host;

    /**
     * optional
     */
    private Integer port;

    private String path;

    /**
     * optional
     */
    private MultiValueMap<String, String> query;

    /**
     * optional
     */
    private String fragment;

    public void checkUrl() {
        Assert.hasText(schema, "schema must not be empty");
        Assert.hasText(host, "host must not be empty");
        Assert.hasText(path, "path must not be empty");
    }
}
