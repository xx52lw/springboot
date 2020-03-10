package com.dock.lw.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dock.lw.code.model.User;
import com.dock.lw.common.enums.SystemContextType;

import java.util.Date;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> {

    @Override
    public boolean save(T entity) {
        User user = SystemContext.getCurrentValue(SystemContextType.CURRENT_USER, User.class);
        if(user != null) {
            entity.setCreateBy(user.getId());
        }
        entity.setCreateAt(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        User user = SystemContext.getCurrentValue(SystemContextType.CURRENT_USER, User.class);
        if(user != null) {
            entity.setUpdateBy(user.getId());
        }
        entity.setUpdateAt(new Date());
        return super.updateById(entity);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        User user = SystemContext.getCurrentValue(SystemContextType.CURRENT_USER, User.class);
        Long id = entity.getId();
        if(id == null) {
            // add
            entity.setCreateAt(new Date());
            if(user != null) {
                entity.setCreateBy(user.getId());
            }
        }else {
            // update
            entity.setUpdateAt(new Date());
            if(user != null) {
                entity.setUpdateBy(user.getId());
            }
        }
        return super.saveOrUpdate(entity);
    }
}
