package space.luckycurve.dto.proxy;

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
public class Hysteria2Proxy extends BaseProxy {

    private String password;

    private String sni;

    private List<String> alpn;

    public BaseProxy transferByUrl(Url url) {
        return new Hysteria2Proxy()
                .setPassword(url.getUserInfo())
                .setSni(url.getQuery().getFirst("sni"))
                .setAlpn(url.getQuery().get("alpn"))
                .setServer(url.getHost()).setName(url.getHost() + ":" + url.getPort())
                .setPort(url.getPort());
    }

    @Override
    public String getType() {
        return "hysteria2";
    }
}
