/*
 *  Copyright 2019-2023 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.ses.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import me.zhengjie.modules.ses.domain.Product;
import me.zhengjie.modules.ses.domain.Review;
import me.zhengjie.modules.ses.domain.vo.ProductQueryCriteria;
import me.zhengjie.modules.ses.mapper.CatMapper;
import me.zhengjie.modules.ses.mapper.ProductMapper;
import me.zhengjie.modules.ses.mapper.ShopMapper;
import me.zhengjie.modules.system.mapper.UserMapper;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.ses.service.ReviewService;
import me.zhengjie.modules.ses.domain.vo.ReviewQueryCriteria;
import me.zhengjie.modules.ses.mapper.ReviewMapper;
import me.zhengjie.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.zhengjie.utils.PageUtil;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import me.zhengjie.utils.PageResult;

/**
 * @author ywx
 * @description 服务实现
 * @date 2023-12-26
 **/
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ShopMapper shopMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @Override
    public PageResult<Review> queryAll(ReviewQueryCriteria criteria, Page<Object> page) {
        Page<Review> reviewList = new LambdaQueryChainWrapper<>(reviewMapper)
                .eq(criteria.getShopId() != null, Review::getShopId, criteria.getShopId())
//                .like(StringUtils.isNoneBlank(criteria.getProductId()), Review::getContents, criteria.getTitle())
                .page(
                        new Page<>(page.getPages(), page.getSize())
                );
        for (Review r : reviewList.getRecords()) {
            r.setShop(shopMapper.selectById(r.getShopId()));
            r.setProduct(productMapper.selectById(r.getProductId()));
            r.setUser(userMapper.selectById(r.getUserId()));
        }
        return PageUtil.toPage(reviewList.getRecords(), reviewList.getTotal());
    }

//    @Override
//    public PageResult<Review> queryAll(ReviewQueryCriteria criteria, Page<Object> page){
//        return PageUtil.toPage(reviewMapper.findAll(criteria, page));
//    }


    @Override
    public List<Review> queryAll(ReviewQueryCriteria criteria) {
        return reviewMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Review resources) {
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Review resources) {
        Review review = getById(resources.getReviewId());
        review.copy(resources);
        saveOrUpdate(review);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Integer> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<Review> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Review review : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("评价内容", review.getContents());
            map.put("评价星级", review.getStar());
            map.put("评价用户", review.getUserId());
            map.put("评价商品", review.getProductId());
            map.put("评价商铺", review.getShopId());
            map.put("创建时间", review.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
