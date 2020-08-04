package com.bryce.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author bryce
 * @since 2020-07-21
 */
@Data
@Accessors(chain = true)
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private String permission;

    /**
     * 描述
     */
    private String description;

    /**
     * 关联角色
     */
    private Integer rid;

    /**
     * 是否锁定
     */
    private String available;


}
