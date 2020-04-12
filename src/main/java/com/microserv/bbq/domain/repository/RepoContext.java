package com.microserv.bbq.domain.repository;

import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.persistence.DictDao;

/**
 * 仓储上下文
 *
 * @author jockeys
 * @since 2020/4/12
 */
public abstract class RepoContext {
    public static final DictRepo dictRepo = ApplicationUtils.getBean(DictDao.class);
}
