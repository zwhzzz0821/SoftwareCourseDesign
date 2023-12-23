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
import me.zhengjie.modules.ses.domain.Cat;
import me.zhengjie.modules.ses.domain.vo.CatQueryCriteria;
import me.zhengjie.modules.ses.mapper.CatMapper;
import me.zhengjie.modules.ses.service.CatService;
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
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements CatService {

    private final CatMapper catMapper;

    @Override
    public PageResult<Cat> queryAll(CatQueryCriteria criteria, Page<Object> page){
        return PageUtil.toPage(catMapper.findAll(criteria, page));
    }

    @Override
    public List<Cat> queryAll(CatQueryCriteria criteria){
        return catMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Cat resources) {
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Cat resources) {
        Cat cat = getById(resources.getCatId());
        cat.copy(resources);
        saveOrUpdate(cat);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Integer> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public void download(List<Cat> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Cat cat : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("种类名称", cat.getKind());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}