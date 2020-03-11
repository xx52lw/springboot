package com.dock.lw.code.web;

import com.dock.lw.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dock.lw.common.IPageDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import com.dock.lw.code.model.User;
import com.dock.lw.code.service.UserService;


/**
 * <p>
 * 用户接口
 * </p>
 *
 * @author liwei
 * @since 2020-03-11
 */
@RestController
@RequestMapping({Constant.PLATFORM.APP +"/user",Constant.PLATFORM.MANAGE +"/user"})
@Api(value = "用户接口", description = "用户接口")
public class UserController {

	@Autowired
    private UserService userService;

	@ApiOperation(value = "分页查询用户",response=User.class)
	@PostMapping(value = "/getByPage")
	public IPage<User> query(@RequestBody IPageDto param) {
		IPage<User> p = userService.selectByPage(param.getPage(), param.getCm());
		return p;
	}
}