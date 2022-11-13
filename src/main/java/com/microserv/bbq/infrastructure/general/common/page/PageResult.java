package com.microserv.bbq.infrastructure.general.common.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页查询结果的响应封装
 *
 * @author mingjie
 * @date 2022/4/5
 */
@ApiModel
@Data
public class PageResult<T> implements Serializable {

    @ApiModelProperty("页面容量")
    private long pageSize;
    @ApiModelProperty("当前页面，从1算起")
    private long pageCurrent;
    @ApiModelProperty("总页数")
    private long pageCount;
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("记录集合")
    private Collection<T> records;

    private PageResult(long pageSize, long pageCurrent, long pageCount, long total, Collection<T> records) {
        this.pageSize = pageSize;
        this.pageCurrent = pageCurrent;
        this.pageCount = pageCount;
        this.total = total;
        this.records = records;
    }

    private PageResult(long pageSize, long pageCurrent) {
        this.pageSize = pageSize;
        this.pageCurrent = pageCurrent;
    }

    public static <T> PageResult<T> valueOf(PageQueryParam pageQueryParam) {
        return valueOf(pageQueryParam.getPageSize(), pageQueryParam.getCurrent());
    }

    public static <T> PageResult<T> valueOf(long pageSize, long pageCurrent) {
        return new PageResult<>(pageSize, pageCurrent);
    }

    public static <T> PageResult<T> valueOf(IPage<T> page) {
        return new PageResult<>(
                page.getSize(),
                page.getCurrent(),
                page.getPages(),
                page.getTotal(),
                page.getRecords());
    }

    public static <T> PageResult<T> valueOf(PageInfo<T> page) {
        return new PageResult<>(
                page.getSize(),
                page.getPageNum(),
                page.getPages(),
                page.getTotal(),
                page.getList());
    }

    public static <Q, T> PageResult<T> transform(PageInfo<Q> page, Function<Q, T> function) {
        return new PageResult<>(
                page.getSize(),
                page.getPageNum(),
                page.getPages(),
                page.getTotal(),
                transform(page.getList(), function)
        );
    }

    public static <Q, T> PageResult<T> transform(IPage<Q> page, Function<Q, T> function) {
        return new PageResult<>(
                page.getSize(),
                page.getCurrent(),
                page.getPages(),
                page.getTotal(),
                transform(page.getRecords(), function)
        );
    }

    public static <Q, T> Collection<T> transform(Collection<Q> collection, Function<Q, T> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(function).collect(Collectors.toList());
    }

    public static <Q, T> PageResult<T> transformAsync(IPage<Q> page, Function<Q, T> function, Executor executor) {
        return new PageResult<>(
                page.getSize(),
                page.getCurrent(),
                page.getPages(),
                page.getTotal(),
                transformAsync(page.getRecords(), function, executor)
        );
    }

    public static <Q, T> PageResult<T> transformAsync(IPage<Q> page, Function<Q, T> function) {
        return transformAsync(page, function, null);

    }

    public static <Q, T> Collection<T> transformAsync(Collection<Q> collection, Function<Q, T> function, Executor executor) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
                .map(e -> {
                            CompletableFuture<T> future = null;
                            if (executor != null) {
                                future = CompletableFuture.supplyAsync(() -> function.apply(e), executor);
                            } else {
                                future = CompletableFuture.supplyAsync(() -> function.apply(e));
                            }
                            return future;
                        }
                )
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}
