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

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.ses.domain.Blog;
import me.zhengjie.modules.ses.service.BlogService;
import me.zhengjie.modules.ses.domain.vo.BlogQueryCriteria;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.utils.PageResult;

/**
* @author hello
* @date 2023-12-28
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "论坛管理")
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('blog:list')")
    public void exportBlog(HttpServletResponse response, BlogQueryCriteria criteria) throws IOException {
        blogService.download(blogService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询论坛")
    @ApiOperation("查询论坛")
    @PreAuthorize("@el.check('blog:list')")
    public ResponseEntity<PageResult<Blog>> queryBlog(BlogQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(blogService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增论坛")
    @ApiOperation("新增论坛")
    @PreAuthorize("@el.check('blog:add')")
    public ResponseEntity<Object> createBlog(@Validated @RequestBody Blog resources){
        blogService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改论坛")
    @ApiOperation("修改论坛")
    @PreAuthorize("@el.check('blog:edit')")
    public ResponseEntity<Object> updateBlog(@Validated @RequestBody Blog resources){
        blogService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除论坛")
    @ApiOperation("删除论坛")
    @PreAuthorize("@el.check('blog:del')")
    public ResponseEntity<Object> deleteBlog(@RequestBody List<Integer> ids) {
        blogService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
