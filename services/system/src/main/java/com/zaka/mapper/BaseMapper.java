package com.zaka.mapper;

/**
 *
 * @param <T>
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     *
     * @param info
     * @return
     */
    default T saveOrUpdate(T info) {
        try {
            this.insert(info);
        } catch (Exception e) {
            this.updateById(info);
        }
        return info;
    }
}
