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
import me.zhengjie.modules.ses.domain.Cat;
import me.zhengjie.modules.ses.domain.vo.CatQueryCriteria;
import me.zhengjie.modules.ses.service.CatService;
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
@Api(tags = "种类管理")
@RequestMapping("/api/cat")
public class CatController {

    private final CatService catService;

//    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('cat:list')")
    public void exportCat(HttpServletResponse response, CatQueryCriteria criteria) throws IOException {
        catService.download(catService.queryAll(criteria), response);
    }

    @GetMapping
//    @Log("查询种类")
    @ApiOperation("查询种类")
    @PreAuthorize("@el.check('cat:list')")
    @ResponseBody
    public ResponseEntity<PageResult<Cat>> queryCat(CatQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(catService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
//    @Log("新增种类")
    @ApiOperation("新增种类")
    @PreAuthorize("@el.check('cat:add')")
    public ResponseEntity<Object> createCat(@Validated @RequestBody Cat resources){
        catService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
//    @Log("修改种类")
    @ApiOperation("修改种类")
    @PreAuthorize("@el.check('cat:edit')")
    public ResponseEntity<Object> updateCat(@Validated @RequestBody Cat resources){
        catService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
//    @Log("删除种类")
    @ApiOperation("删除种类")
    @PreAuthorize("@el.check('cat:del')")
    @ResponseBody
    public ResponseEntity<Object> deleteCat(@RequestBody List<Integer> ids) {
        catService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
