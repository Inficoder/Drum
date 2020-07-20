package com.bryce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @ClassName BaseEntity
 * @Description base entity
 * @Author Bryce
 * @Date 2020-07-20 13:09
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 说明：在不做调整的情况下，如果从数据库查询出对象后，即便更新了除updateTime外的字段，也无法实现自动更新updateTime在数据库中的值，
     * 解决：加上update = "now()"
     **/
    @TableField(fill = FieldFill.UPDATE,update = "now()")
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;
    @Version
    private Integer version;
}
