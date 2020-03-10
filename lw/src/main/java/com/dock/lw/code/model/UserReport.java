package com.dock.lw.code.model;

import com.dock.lw.common.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登记
 * </p>
 *
 * @author liwei
 * @since 2020-03-05
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@ApiModel(value = "UserReport", description = "用户登记")
public class UserReport extends BaseModel {


    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "单位ID")
    private Long deptId;


}