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
package me.zhengjie.modules.ses.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.ses.domain.Cat;
import me.zhengjie.modules.ses.domain.vo.CatQueryCriteria;
import me.zhengjie.utils.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* @description 服务接口
* @author ywx
* @date 2023-12-22
**/
public interface CatService extends IService<Cat> {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param page 分页参数
    * @return PageResult
    */
    PageResult<Cat> queryAll(CatQueryCriteria criteria, Page<Object> page);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CatDto>
    */
    List<Cat> queryAll(CatQueryCriteria criteria);

    /**
    * 创建
    * @param resources /
    */
    void create(Cat resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Cat resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(List<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<Cat> all, HttpServletResponse response) throws IOException;
}