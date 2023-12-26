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
package me.zhengjie.modules.ses.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import me.zhengjie.modules.system.domain.User;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @description /
* @author ywx
* @date 2023-12-26
**/
@Data
@TableName("ses_review")
public class Review implements Serializable {

    @TableId(value = "review_id", type = IdType.AUTO)
    @ApiModelProperty(value = "评价编号")
    private Integer reviewId;

    @NotBlank
    @ApiModelProperty(value = "评价内容")
    private String contents;

    @NotNull
    @ApiModelProperty(value = "评价星级")
    private Integer star;

    @TableField(value = "user_id")
    @ApiModelProperty(hidden = true)
    private Integer userId;

//    @ApiModelProperty(value = "评价用户")
//    private Integer userId;

    @TableField(value = "product_id")
    @ApiModelProperty(hidden = true)
    private Integer productId;


//    @ApiModelProperty(value = "评价商品")
//    private Integer productId;

    @ApiModelProperty(value = "评价商铺")
    private Integer shopId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;

    @ApiModelProperty(value = "评价用户")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty(value = "评价商品")
    @TableField(exist = false)
    private Product product;

    @ApiModelProperty(value = "评价商铺")
    @TableField(exist = false)
    private Shop shop;



    public void copy(Review source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
