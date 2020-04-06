package com.microserv.bbq.infrastructure.general.toolkit;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 序列号生产工具
 *
 * @author jockeys
 * @since 2020/4/6
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SequenceUtils {

    public static String snowFlakeID() {
        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        return snowflake.nextIdStr();
    }

    public static String uuid36() {
        return IdUtil.fastUUID();
    }

    public static String uuid32() {
        return IdUtil.fastSimpleUUID();
    }

    public static String objectId() {
        return IdUtil.objectId();
    }

}
