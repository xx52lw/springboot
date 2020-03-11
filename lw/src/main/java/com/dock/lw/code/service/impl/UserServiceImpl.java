package com.dock.lw.code.service.impl;

import com.dock.lw.common.BaseServiceImpl;
import com.dock.lw.code.model.User;
import com.dock.lw.code.mapper.UserMapper;
import com.dock.lw.code.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 用户  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2020-03-11
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> selectByPage(Page page, Map<String, Object> cm) {
        return userMapper.selectByPage(page, cm);
    }
}