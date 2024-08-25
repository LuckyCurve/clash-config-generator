package space.luckycurve.utils;

import java.util.Arrays;
import java.util.List;

public class Lists {

    public static <T> List<T> clone(List<T> source, T... additionalElements) {
        List<T> res = com.google.common.collect.Lists.newArrayList(source);
        res.addAll(Arrays.asList(additionalElements));

        return res;
    }
}
