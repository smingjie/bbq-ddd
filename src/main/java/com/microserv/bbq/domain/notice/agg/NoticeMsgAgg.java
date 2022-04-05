package com.microserv.bbq.domain.notice.agg;

import com.microserv.bbq.domain.notice.entity.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.vobj.NoticeSendResultVObj;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 消息通知信息实体
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class NoticeMsgAgg extends NoticeMsgEntity {

    private List<NoticeSendResultVObj> sendResult; //是否发布成功


    public static class Actions {
        public void publish() {
            //执行发布操作
        }
    }
}
