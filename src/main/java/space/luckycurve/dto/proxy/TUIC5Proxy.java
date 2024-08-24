package space.luckycurve.dto.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import space.luckycurve.dto.url.Url;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TUIC5Proxy extends BaseProxy {

    private String uuid;

    private String password;

    @JsonProperty(value = "congestion-controller")
    private String congestionController;

    private List<String> alpn;

    @Override
    public BaseProxy transferByUrl(Url url) {
        return new TUIC5Proxy()
                .setUuid(url.getQuery().getFirst("uuid"))
                .setPassword(url.getQuery().getFirst("password"))
                .setCongestionController(url.getQuery().getFirst("congestion-controller"))
                .setAlpn(url.getQuery().get("alpn"))
                .setServer(url.getHost())
                .setPort(url.getPort())
                .fill();
    }

    @Override
    public String proxyType() {
        return "tuic";
    }
}
