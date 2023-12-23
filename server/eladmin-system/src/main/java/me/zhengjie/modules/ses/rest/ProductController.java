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
import me.zhengjie.modules.ses.domain.Product;
import me.zhengjie.modules.ses.domain.vo.ProductQueryCriteria;
import me.zhengjie.modules.ses.service.ProductService;
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
@Api(tags = "商品管理")
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('product:list')")
    public void exportProduct(HttpServletResponse response, ProductQueryCriteria criteria) throws IOException {
        productService.download(productService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询商品")
    @ApiOperation("查询商品")
    @PreAuthorize("@el.check('product:list')")
    public ResponseEntity<PageResult<Product>> queryProduct(ProductQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(productService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商品")
    @ApiOperation("新增商品")
    @PreAuthorize("@el.check('product:add')")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody Product resources){
        productService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商品")
    @ApiOperation("修改商品")
    @PreAuthorize("@el.check('product:edit')")
    public ResponseEntity<Object> updateProduct(@Validated @RequestBody Product resources){
        productService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除商品")
    @ApiOperation("删除商品")
    @PreAuthorize("@el.check('product:del')")
    public ResponseEntity<Object> deleteProduct(@RequestBody List<Integer> ids) {
        productService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}