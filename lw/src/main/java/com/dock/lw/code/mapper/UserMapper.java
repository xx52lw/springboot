package com.dock.lw.code.mapper;

import com.dock.lw.code.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author liwei
 * @since 2020-03-11
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectByPage(Page page, @Param("cm") Map<String, Object> cm);
}