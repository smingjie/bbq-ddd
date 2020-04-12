package com.microserv.bbq.domain.model.dict;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.ICrud;
import com.microserv.bbq.domain.repository.RepoContext;
import com.microserv.bbq.infrastructure.general.toolkit.ConvertUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/6
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DictEntity implements ICrud<DictEntity> {
    //----- field -----//

    private String id;
    private String name;
    private String type;
    private String code;
    private String value;
    private Integer orderNum;
    private String remark;

    //----- constructor -----//

    public DictEntity(String id) {
        this.id = id;
    }

    //----- public method-----//
    @Override
    public DictEntity get() {
        if (!StrUtil.isAllBlank(this.id, this.type, this.code)) {
            DictEntity item = Objects.nonNull(this.id) ?
                    RepoContext.dictRepo.select(this.id) : RepoContext.dictRepo.selectOne(this.type, this.code);
            ConvertUtils.convert(item, this);
        }
        return this;
    }

    @Override
    public DictEntity saveOrUpdate() {
        if (Objects.nonNull(this.id)) {
            this.id = SequenceUtils.uuid36();
            RepoContext.dictRepo.insert(this);
        } else {
            RepoContext.dictRepo.update(this);
        }
        return this;
    }

    @Override
    public boolean delete() {
        return RepoContext.dictRepo.delete(this);
    }

}
