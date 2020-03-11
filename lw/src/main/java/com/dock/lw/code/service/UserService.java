package com.dock.lw.code.service;

import com.dock.lw.code.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author liwei
 * @since 2020-03-11
 */
public interface UserService extends IService<User> {

    IPage<User> selectByPage(Page page, Map<String, Object> cm);
}