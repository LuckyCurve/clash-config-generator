package space.luckycurve.dto.proxy.group;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ProxyGroup {

    @NonNull
    private String name;

    @NonNull
    private List<String> proxies;

    @NonNull
    private String type;

    private Integer interval;

    private Integer tolerance;

    private String url;
}
