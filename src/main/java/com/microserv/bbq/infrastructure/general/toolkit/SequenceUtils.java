package com.microserv.bbq.infrastructure.general.toolkit;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * guid 生成器
 *
 * @author jockeys
 * @since 2020/2/4
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SequenceUtils {
    /**
     * 获取36字符UUID
     *
     * @return uuid
     */
    public static String uuid36() {
        return IdUtil.fastUUID();
    }

    /**
     * 获取32字符UUID
     *
     * @return uuid
     */
    public static String uuid32() {
        return IdUtil.fastSimpleUUID();
    }

    /**
     * 获取时间戳序列号(毫秒时间戳+3位随机数) 如20200101120030999+010
     *
     * @return 20位序列号
     */
    public static String timestampNo() {
        StringBuilder builder = new StringBuilder();
        builder.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        builder.append(String.format("%03d", new Random(1000)));
        return builder.toString();
    }

    /**
     * 获取时间戳序列号（带前缀）
     *
     * @param prefix 前缀，如image
     * @return 前缀+20位序列号，如image(+)20200101120030999(+)010
     */
    public static String timestampNo(String prefix) {

        return prefix + timestampNo();
    }

    /**
     * 雪花算法，id
     *
     * @return
     */
    public String snowId() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        return snowflake.nextIdStr();

    }
}
