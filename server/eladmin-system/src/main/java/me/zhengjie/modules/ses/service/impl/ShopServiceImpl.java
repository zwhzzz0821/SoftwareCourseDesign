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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.ses.domain.Shop;
import me.zhengjie.modules.ses.domain.vo.ShopQueryCriteria;
import me.zhengjie.modules.ses.mapper.ShopMapper;
import me.zhengjie.modules.ses.service.ShopService;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @description 服务实现
* @author ywx
* @date 2023-12-22
**/
@Service
@RequiredArgsConstructor
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    private final ShopMapper shopMapper;

    @Override
    public PageResult<Shop> queryAll(ShopQueryCriteria criteria, Page<Object> page){
        return PageUtil.toPage(shopMapper.findAll(criteria, page));
    }

    @Override
    public List<Shop> queryAll(ShopQueryCriteria criteria){
        return shopMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Shop resources) {
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Shop resources) {
        Shop shop = getById(resources.getShopId());
        shop.copy(resources);
        saveOrUpdate(shop);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Integer> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<Shop> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Shop shop : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商铺名称", shop.getTitle());
            map.put("商铺地址", shop.getAddress());
            map.put("商铺描述", shop.getDescription());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}