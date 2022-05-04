package com.microserv.bbq.domain.dict.model;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
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
public class DictEntity implements IDomainCRUD<DictEntity>, IDomainMetaData {
    private static final DictRepository dictRepository = RepositoryFactory.get(DictRepository.class);

    private String id;          // 唯一id
    private String name;        // 字典类型名称
    private String type;        // 字典类型
    private String code;        // 字典键
    private String value;       // 字典值
    private Integer orderNum;   // 排序
    private String remark;      // 备注

    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;


    public DictEntity(String id) {
        this.id = id;
    }

    public DictEntity(String type, String code) {
        this.type = type;
        this.code = code;
    }

    /**
     * 根据 id 或者 type+code 唯一索引去删除
     */
    @Override
    public boolean delete() {
        return dictRepository.delete(this);
    }

    /**
     * 根据 id 或者 type+code 唯一索引去保持或更新
     */
    @Override
    public DictEntity saveOrUpdate() {
        boolean success = Objects.isNull(this.id)
                ? dictRepository.insert(this.setId(SequenceUtils.uuid32()))
                : dictRepository.update(this);

        if (success) {
            DictEntity entity = dictRepository.selectById(this.id);
            ModelUtils.convert(entity, this);
        }
        return this;
    }

    /**
     * 根据字典的 （id） 或者 （type+code）
     *
     * @return
     */
    @Override
    public DictEntity fetch() {
        DictEntity entity = null;

        if (StrUtil.isNotBlank(this.id)) {
            entity = getInstance(this.id);
        } else if (StrUtil.isAllNotBlank(this.type, this.code)) {
            entity = getInstance(this.type, this.code);
        }

        if (entity != null) {
            ModelUtils.convert(entity, this);
        }

        return this;
    }


    public static DictEntity getInstance(String type, String code) {
        return dictRepository.selectByTypeAndCode(type, code);
    }


    public static DictEntity getInstance(String id) {
        return dictRepository.selectById(id);
    }

}

