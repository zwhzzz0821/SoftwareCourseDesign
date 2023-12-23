package me.zhengjie.modules.ses.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @description /
* @author ywx
* @date 2023-12-22
**/
@Data
@TableName("ses_shop")      //使用 MyBatis Plus 的 @TableName 注解，指定了该类对应数据库中的表名为 'ses_shop'。
public class Shop implements Serializable {

    @TableId(value = "shop_id", type = IdType.AUTO) //使用了 MyBatis Plus 中的 @TableId 注解标识了 shopId 作为表的主键，指定了主键生成策略为自增。
    @ApiModelProperty(value = "商铺ID") //@ApiModelProperty 注解描述了类的属性，在 Swagger文档中用于显示属性的描述信息。
    private Integer shopId;

    @NotBlank       // @NotBlank 注解用于验证字符串字段不能为空。
    @ApiModelProperty(value = "商铺名称")
    private String title;

    @ApiModelProperty(value = "商铺地址")
    private String address;

    @ApiModelProperty(value = "商铺描述")
    private String description;

    public void copy(Shop source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
