package com.maxiaowei.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * <p>
 * 作者: maxiaowei
 */
public class CollectionUtils {
    private CollectionUtils() {

    }

    // region 返回空集合<T>
    public static <T> ArrayList<T> emptyArrayList() {
        return new ArrayList<>();
    }
    // endregion

    // region 集合判空相关操作
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
    // endregion

    // region 集合间转换相关操作

    /**
     * 将一个集合转换为另外一个集合
     *
     * @param from
     * @param func
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).collect(Collectors.toList());
    }
    //endregion

    // region 创建新集合相关操作
    @SafeVarargs
    public static <E> List<E> newArrayList(E... args) {
        return new ArrayList<>(Arrays.asList(args));
    }
    // endregion
}
