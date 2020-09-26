package com.microserv.bbq.domain.model.dict;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.interfaces.ICrud;
import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.DictRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import com.microserv.bbq.infrastructure.persistence.extension.annotation.BbqDomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 领域模型--实体
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@BbqDomainEntity
public class DictEntity implements ICrud<DictEntity> {
    private String  id;          // 唯一id
    private String  name;        // 字典类型名称
    private String  type;        // 字典类型
    private String  code;        // 字典键
    private String  value;       // 字典值
    private Integer orderNum;    // 排序
    private String  remark;      // 备注

    public DictEntity(String id) {
        this.id = id;
    }

    /**
     * 根据 id 或者 type+code 唯一索引去删除
     */
    @Override
    public boolean delete() {
        return RepoFactory.get(DictRepo.class).delete(this);
    }

    /**
     * 根据 id 或者 type+code 唯一索引去保持或更新
     */
    @Override
    public DictEntity saveOrUpdate() {
        if (Objects.nonNull(this.id)) {
            this.id = SequenceUtils.uuid32();
            RepoFactory.get(DictRepo.class).insert(this);
        } else {
            RepoFactory.get(DictRepo.class).update(this);
        }

        return this;
    }

    /**
     * 根据字典的 （id） 或者 （type+code）
     *
     * @return
     */
    @Override
    public DictEntity get() {
        if (!StrUtil.isAllBlank(this.id, this.type, this.code)) {
            DictEntity item = Objects.nonNull(this.id)
                              ? RepoFactory.get(DictRepo.class).select(this.id)
                              : RepoFactory.get(DictRepo.class).selectOne(this.type, this.code);

            ModelUtils.convert(item, this);
        }

        return this;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
