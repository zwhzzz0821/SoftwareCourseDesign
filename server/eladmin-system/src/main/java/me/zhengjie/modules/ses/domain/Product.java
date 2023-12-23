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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import me.zhengjie.modules.system.domain.Dept;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @description /
* @author ywx
* @date 2023-12-22
**/
@Data
@TableName("ses_product")
public class Product implements Serializable {

    @TableId(value = "product_id", type = IdType.AUTO)
    @ApiModelProperty(value = "小吃ID")
    private Integer productId;

    @NotBlank
    @ApiModelProperty(value = "小吃名")
    private String title;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "所属商店id")
    private Integer shopId;

    @TableField(value = "cat_id")
    @ApiModelProperty(hidden = true)
    private Integer catId;

    @ApiModelProperty(value = "商品种类")
    @TableField(exist = false)
    private Cat cat;


    public void copy(Product source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
