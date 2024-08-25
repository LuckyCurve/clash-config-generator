package space.luckycurve.service;

import org.springframework.stereotype.Service;
import space.luckycurve.ClashConfigGeneratorApplication;
import space.luckycurve.dto.proxy.BaseProxy;
import space.luckycurve.dto.proxy.group.ProxyGroup;
import space.luckycurve.dto.url.Url;
import space.luckycurve.utils.Lists;

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
        String auto = "\uD83C\uDF40 AUTO_SELECT";
        String direct = "DIRECT";

        return com.google.common.collect.Lists.newArrayList(
                new ProxyGroup("\uD83D\uDC8D PROXY", Lists.clone(proxyNames, auto, direct), "select"),
                new ProxyGroup("\uD83C\uDF40 AUTO_SELECT", proxyNames, "url-test", 300, 50, "http://www.gstatic.com/generate_204"),
                new ProxyGroup("\uD83C\uDFAF DEFAULT", Lists.clone(proxyNames, auto, direct), "select"));
    }
}