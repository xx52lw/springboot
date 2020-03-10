package com.dock.lw.code.web;

import com.dock.lw.common.Constant;
import com.dock.lw.common.IPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dock.lw.code.model.UserReport;
import com.dock.lw.code.service.UserReportService;


/**
 * <p>
 * 用户登记接口
 * </p>
 *
 * @author liwei
 * @since 2020-03-05
 */
@RestController
@RequestMapping(Constant.PLATFORM.APP +"/userReport")
@Api(value = "用户登记接口", description = "用户登记接口")
public class UserReportController {

	@Autowired
    private UserReportService userReportService;


	@ApiOperation(value = "分页查询用户登记",response=UserReport.class)
	@PostMapping(value = "/getDetail")
	public IPage<UserReport> getDetail(@RequestBody IPageDto param) {
		return userReportService.selectByPage(param.getPage(), param.getCm());
	}

	@ApiOperation(value = "分页查询用户登记",response=UserReport.class)
	@PostMapping(value = "/getByPage")
	public IPage<UserReport> query(@RequestBody IPageDto param) {
		return userReportService.selectByPage(param.getPage(), param.getCm());
	}
}