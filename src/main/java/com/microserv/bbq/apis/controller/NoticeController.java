package com.microserv.bbq.apis.controller;

import com.microserv.bbq.application.model.notice.NoticeResultDTO;
import com.microserv.bbq.application.service.NoticeApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/4
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeApplicationService noticeApplicationService;

    @ApiOperation(value = "获取一个指定消息的投递结果")
    @GetMapping("/notice/{msgId}/result")
    public NoticeResultDTO getNoticeResult(@PathVariable String msgId) {
        return noticeApplicationService.getReceiveResult(msgId);
    }
}
