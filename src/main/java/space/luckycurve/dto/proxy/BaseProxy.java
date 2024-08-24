package space.luckycurve.dto.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import space.luckycurve.dto.url.Url;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public abstract class BaseProxy {

    protected String type;

    protected String name;

    protected String server;

    protected Integer port;

    public abstract BaseProxy transferByUrl(Url url);

    public abstract String proxyType();

    protected BaseProxy fill() {
        return this.fillType().fillName();
    }

    protected BaseProxy fillName() {
        return this.setName(server + ":" + port + ":" + type);
    }

    protected BaseProxy fillType() {
        return this.setType(proxyType());
    }
}