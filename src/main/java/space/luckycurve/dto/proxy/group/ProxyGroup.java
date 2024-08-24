package space.luckycurve.dto.proxy.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyGroup {

    private String name;

    private List<String> proxies;

    private String type;
}
