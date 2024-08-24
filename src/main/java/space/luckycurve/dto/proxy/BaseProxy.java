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

    protected String name;

    protected String server;

    private Integer port;

    public abstract BaseProxy transferByUrl(Url url);

    public abstract String getType();
}