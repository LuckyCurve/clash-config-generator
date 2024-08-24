package space.luckycurve.configuration.yaml;

import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import space.luckycurve.ClashConfigGeneratorApplication;

import java.util.Set;

@Configuration
@ConditionalOnMissingBean(Yaml.class)
public class YamlAutoConfiguration {

    private final Class<ClashConfigGeneratorApplication> mainClass = ClashConfigGeneratorApplication.class;

    @Bean
    public Yaml yaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.AUTO);

        Representer representer = new Representer(options);
        String packageName = getProjectBasePackageName();
        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(YamlDump.class);
        for (Class<?> clazz : typesAnnotatedWith) {
            representer.addClassTag(clazz, Tag.MAP);
        }

        return new Yaml(representer, options);
    }

    private String getProjectBasePackageName() {
        return mainClass.getPackage().getName();
    }
}
