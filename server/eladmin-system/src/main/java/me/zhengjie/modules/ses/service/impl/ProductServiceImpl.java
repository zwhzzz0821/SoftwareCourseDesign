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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.ses.domain.Product;
import me.zhengjie.modules.ses.domain.vo.ProductQueryCriteria;
import me.zhengjie.modules.ses.mapper.CatMapper;
import me.zhengjie.modules.ses.mapper.ProductMapper;
import me.zhengjie.modules.ses.service.ProductService;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywx
 * @description 服务实现
 * @date 2023-12-22
 **/
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductMapper productMapper;
    private final CatMapper catMapper;

    @Override
    public PageResult<Product> queryAll(ProductQueryCriteria criteria, Page<Object> page) {
        Page<Product> productList = new LambdaQueryChainWrapper<>(productMapper)
                .eq(criteria.getShopId() != null, Product::getShopId, criteria.getShopId())
                .like(StringUtils.isNoneBlank(criteria.getTitle()), Product::getTitle, criteria.getTitle())
                .page(
                        new Page<>(page.getPages(), page.getSize())
                );
        for (Product p:
                productList.getRecords()) {
            p.setCat(catMapper.selectById(p.getCatId()));
        }
        return PageUtil.toPage(productList.getRecords(), productList.getTotal());
    }

    @Override
    public List<Product> queryAll(ProductQueryCriteria criteria) {
        return productMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Product resources) {
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Product resources) {
        Product product = getById(resources.getProductId());
        product.copy(resources);
        saveOrUpdate(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Integer> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<Product> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Product product : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("小吃名", product.getTitle());
            map.put("单价", product.getUnitPrice());
            map.put("数量", product.getQuantity());
            map.put("描述", product.getDescription());
            map.put("所属商店id", product.getShopId());
            map.put("种类id", product.getCatId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
