package com.tengs.idol.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("id")
    private Integer id;


    @TableField("userName")
    private String userName;

    @TableField("openId")
    private String openId;

    @TableField("imageUrl")
    private String imageUrl;


}
