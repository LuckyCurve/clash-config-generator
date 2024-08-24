package space.luckycurve.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import space.luckycurve.ClashConfigGeneratorApplication;
import space.luckycurve.dto.proxy.BaseProxy;
import space.luckycurve.dto.proxy.group.ProxyGroup;
import space.luckycurve.dto.url.Url;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GenerateConfigService {

    private final Map<String, BaseProxy> baseProxyMap = new HashMap<>();

    {
        Set<Class<? extends BaseProxy>> subClass = ClashConfigGeneratorApplication.getRootReflections().getSubTypesOf(BaseProxy.class);
        subClass.forEach(clazz -> {
            try {
                Constructor<? extends BaseProxy> constructor = clazz.getConstructor();
                BaseProxy baseProxy = constructor.newInstance();
                baseProxyMap.put(baseProxy.proxyType(), baseProxy);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public BaseProxy generateProxy(String subscribeUrl) {
        Url url = UrlParser.parseUrl(subscribeUrl);

        return baseProxyMap.get(url.getSchema()).transferByUrl(url);
    }

    public List<ProxyGroup> generateProxyGroup(List<String> proxyNames) {
        proxyNames.add("DIRECT");

        return Lists.newArrayList(
                new ProxyGroup("\uD83D\uDC8D PROXY", proxyNames, "select"),
                new ProxyGroup("\uD83C\uDFAF DEFAULT", proxyNames, "select"));
    }
}