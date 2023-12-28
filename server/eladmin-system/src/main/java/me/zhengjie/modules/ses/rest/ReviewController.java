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
import me.zhengjie.modules.ses.domain.Review;
import me.zhengjie.modules.ses.service.ReviewService;
import me.zhengjie.modules.ses.domain.vo.ReviewQueryCriteria;
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
* @author ywx
* @date 2023-12-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "评价管理")
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

//    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('review:list')")
    public void exportReview(HttpServletResponse response, ReviewQueryCriteria criteria) throws IOException {
        reviewService.download(reviewService.queryAll(criteria), response);
    }

    @GetMapping
//    @Log("查询评价")
    @ApiOperation("查询评价")
    @PreAuthorize("@el.check('review:list')")
    public ResponseEntity<PageResult<Review>> queryReview(ReviewQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(reviewService.queryAll(criteria,page),HttpStatus.OK);
    }

    @PostMapping
//    @Log("新增评价")
    @ApiOperation("新增评价")
    @PreAuthorize("@el.check('review:add')")
    public ResponseEntity<Object> createReview(@Validated @RequestBody Review resources){
        reviewService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
//    @Log("修改评价")
    @ApiOperation("修改评价")
    @PreAuthorize("@el.check('review:edit')")
    public ResponseEntity<Object> updateReview(@Validated @RequestBody Review resources){
        reviewService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
//    @Log("删除评价")
    @ApiOperation("删除评价")
    @PreAuthorize("@el.check('review:del')")
    public ResponseEntity<Object> deleteReview(@RequestBody List<Integer> ids) {
        reviewService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
