package space.luckycurve.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import space.luckycurve.dto.proxy.BaseProxy;
import space.luckycurve.service.GenerateConfigService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Resource
    GenerateConfigService generateConfigService;

    @Resource
    Yaml yaml;

    @GetMapping(value = "/generate/clash", produces = "application/yaml")
    public String generateClashConfig(@RequestParam("subscribeUrls") List<String> subscribeUrls) {
        log.info("request: {}", subscribeUrls);

        List<BaseProxy> res = subscribeUrls.stream().map(s -> generateConfigService.generateClashConfig(s)).toList();

        return yaml.dump(res);
    }
}
