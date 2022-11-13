package com.microserv.bbq.infrastructure.general.toolkit;

import com.microserv.bbq.infrastructure.general.common.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 断言工具类，取代if-else抛出自定义异常
 *
 * @author mingjie
 * @date 2022/4/10
 */
public final class AssertUtils {

    /**
     * 如果入参collection集合为空，则抛出异常
     *
     * @param collection 集合
     * @param errMsg     错误提示信息
     * @throws BusinessException
     */
    public static void notEmpty(@Nullable Collection<?> collection, String errMsg) throws BusinessException {
        if (ObjectUtils.isEmpty(collection)) {
            throw new BusinessException(errMsg);
        }
    }

    /**
     * 如果入参对象为null，抛出异常
     *
     * @param object 任何对象
     * @param errMsg 错误提示信息
     * @throws BusinessException
     */
    public static <T> T notNull(T object, String errMsg) throws BusinessException {
        if (object == null) {
            throw new BusinessException(errMsg);
        }
        return object;
    }

    /**
     * 如果入参text为空，则抛出异常
     *
     * @param text   字符串
     * @param errMsg 错误提示信息
     * @throws BusinessException
     */
    public static void hasText(@Nullable String text, String errMsg) throws BusinessException {
        if (!StringUtils.hasText(text)) {
            throw new BusinessException(errMsg);
        }
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出 {@code BusinessException} 异常<br>
     *
     * @param expression 布尔值
     * @param errMsg     错误提示信息
     * @throws BusinessException if expression is {@code false}
     */
    public static void isTrue(boolean expression, String errMsg) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errMsg);
        }
    }

}
