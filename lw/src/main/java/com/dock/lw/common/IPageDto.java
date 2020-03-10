package com.dock.lw.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

@ApiModel
public class IPageDto {
    /** 当前页 */
    private long current = 1;

    /** 每页显示条数 */
    private long size = 10;

    /** 查询条件 */
    private Map<String, Object> cm = new HashMap<>();

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Map<String, Object> getCm() {
        return cm;
    }

    public void setCm(Map<String, Object> cm) {
        this.cm = cm;
    }

    @ApiModelProperty(hidden = true)
    public Page getPage(){
        Page page = new Page();
        page.setCurrent(getCurrent());
        page.setSize(getSize());
        return page;
    }
}
