package com.microserv.bbq.domain.dict.model.dpo;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.dict.model.DictEntity;
import com.microserv.bbq.domain.dict.model.DictItemEntity;
import com.microserv.bbq.domain.dict.model.DictTypeEntity;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 领域负载对象-字典类型和其对应的字典值集合
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Setter
@Getter
@Accessors(chain = true)
public class DictTypeDPO extends DictTypeEntity {
    private List<DictItemEntity> itemList;

    public DictTypeDPO(String type) {
        super(type);
    }

    private DictTypeDPO fetch() {
        if (Objects.nonNull(this.getType())) {
            DictTypeDPO agg = getInstance(this.getType());
            ModelUtils.convert(agg, this);
        }

        return this;
    }

    /**
     * 根据字典类型来获取此类型的字典聚合
     *
     * @param type 字典类型
     * @return 字典类型聚合结果
     */
    public static DictTypeDPO getInstance(String type) {
        DictTypeDPO agg = new DictTypeDPO(type);
        if (Objects.nonNull(type)) {
            List<DictEntity> dictEntities = RepositoryFactory.get(DictRepository.class).selectByType(type);
            if (!CollectionUtils.isEmpty(dictEntities)) {
                agg.setName(dictEntities.get(0).getName());
                agg.setItemList(dictEntities.stream().map(DictItemEntity::new).collect(Collectors.toList()));
            }
        }
        return agg;
    }
}


