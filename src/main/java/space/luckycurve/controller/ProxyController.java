package space.luckycurve.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import space.luckycurve.dto.proxy.BaseProxy;
import space.luckycurve.dto.proxy.group.ProxyGroup;
import space.luckycurve.service.GenerateConfigService;
import space.luckycurve.utils.Jackson;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Resource
    GenerateConfigService generateConfigService;

    @Value("classpath:baseProxy.yaml")
    org.springframework.core.io.Resource baseResource;

    Yaml yaml = new Yaml();

    @GetMapping(value = "/generate/clash", produces = "application/yaml")
    public String generateClashConfig(@RequestParam("subscribeUrls") List<String> subscribeUrls) throws IOException {
        log.info("request: {}", subscribeUrls);

        Map<Object, Object> base = yaml.load(baseResource.getInputStream());
        List<BaseProxy> proxies = subscribeUrls.stream().map(s -> generateConfigService.generateProxy(s)).toList();
        List<ProxyGroup> proxyGroups = generateConfigService.generateProxyGroup(proxies.stream().map(BaseProxy::getName).collect(Collectors.toList()));

        base.put("proxies", proxies.stream().map(Jackson::convertObjectToMap).collect(Collectors.toList()));
        base.put("proxy-groups", proxyGroups.stream().map(Jackson::convertObjectToMap).collect(Collectors.toList()));

        return yaml.dump(base);
    }
}