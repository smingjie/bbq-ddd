package com.microserv.bbq.domain.dict;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainEntity;
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
@DomainEntity
public class DictEntity implements IDomainCRUD<DictEntity> {
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
        return RepositoryFactory.get(DictRepository.class).delete(this);
    }

    /**
     * 根据 id 或者 type+code 唯一索引去保持或更新
     */
    @Override
    public DictEntity saveOrUpdate() {
        if (Objects.nonNull(this.id)) {
            this.id = SequenceUtils.uuid32();
            RepositoryFactory.get(DictRepository.class).insert(this);
        } else {
            RepositoryFactory.get(DictRepository.class).update(this);
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
                              ? RepositoryFactory.get(DictRepository.class).select(this.id)
                              : RepositoryFactory.get(DictRepository.class).selectOne(this.type, this.code);

            ModelUtils.convert(item, this);
        }

        return this;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
