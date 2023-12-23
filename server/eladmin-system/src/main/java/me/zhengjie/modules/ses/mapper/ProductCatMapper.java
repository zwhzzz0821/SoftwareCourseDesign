package me.zhengjie.modules.ses.mapper;

import me.zhengjie.modules.ses.domain.Cat;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author ywx
 * @date 2023-12-22
 */
public interface ProductCatMapper {
    void insertData(@Param("productId") Long productId, @Param("cats") Set<Cat> cats);

    void deleteByProductId(@Param("productId") Long productId);

    void deleteByProductIds(@Param("productIds") Set<Long> productIds);
}
