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
public class RolesPermissions extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;


}
