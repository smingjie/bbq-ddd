package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.general.toolkit.ConvertUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import com.microserv.bbq.infrastructure.persistent.po.SysDict;
import com.microserv.bbq.infrastructure.persistent.repository.SysDictMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/6
 */
@Data
@NoArgsConstructor
public class DictItemEntity {
    //----- field -----//

    private String id;
    private String name;
    private String type;
    private String code;
    private String value;
    private Integer orderNum;
    private String remark;

    private static final SysDictMapper repo = ApplicationUtils.getBean(SysDictMapper.class);

    //----- constructor -----//

    public DictItemEntity(String id) {
        this.id = id;
    }

    //----- public method-----//

    public DictItemEntity get() {
        if (Objects.nonNull(this.id)) {
            ConvertUtils.convertOnto(repo.selectById(this.id), this);
        }
        return this;
    }

    public DictItemEntity saveOrUpdate() {
        if (Objects.nonNull(this.id)) {
            this.id = SequenceUtils.uuid36();
            repo.insert(this.aspo());
        } else {
            repo.updateById(this.aspo());
        }
        return this;
    }

    public boolean delete() {
        return Objects.isNull(this.id) ? false : repo.deleteById(this.id) > 0;
    }

    public SysDict aspo() {
        return ConvertUtils.convert(this, SysDict.class);
    }
}
