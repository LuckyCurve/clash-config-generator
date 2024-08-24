package space.luckycurve.service;

import org.springframework.stereotype.Service;
import space.luckycurve.dto.proxy.BaseProxy;
import space.luckycurve.dto.proxy.Hysteria2Proxy;

@Service
public class GenerateConfigService {
    public BaseProxy generateClashConfig(String subscribeUrl) {
        return new Hysteria2Proxy().setHost(subscribeUrl);
    }
}