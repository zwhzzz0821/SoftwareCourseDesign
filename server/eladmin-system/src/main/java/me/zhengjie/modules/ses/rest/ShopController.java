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
package me.zhengjie.modules.ses.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.ses.domain.Shop;
import me.zhengjie.modules.ses.domain.vo.ShopQueryCriteria;
import me.zhengjie.modules.ses.service.ShopService;
import me.zhengjie.utils.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* @author ywx
* @date 2023-12-22
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "商铺管理")
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

//    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('shop:list')")
    public void exportShop(HttpServletResponse response, ShopQueryCriteria criteria) throws IOException {
        shopService.download(shopService.queryAll(criteria), response);
    }

    @GetMapping
//    @Log("查询商铺")
    @ApiOperation("查询商铺")
    @PreAuthorize("@el.check('shop:list')")
    public ResponseEntity<PageResult<Shop>> queryShop(ShopQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(shopService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
//    @Log("新增商铺")
    @ApiOperation("新增商铺")
    @PreAuthorize("@el.check('shop:add')")
    public ResponseEntity<Object> createShop(@Validated @RequestBody Shop resources){
        shopService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
//    @Log("修改商铺")
    @ApiOperation("修改商铺")
    @PreAuthorize("@el.check('shop:edit')")
    public ResponseEntity<Object> updateShop(@Validated @RequestBody Shop resources){
        shopService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
//    @Log("删除商铺")
    @ApiOperation("删除商铺")
    @PreAuthorize("@el.check('shop:del')")
    public ResponseEntity<Object> deleteShop(@RequestBody List<Integer> ids) {
        shopService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}