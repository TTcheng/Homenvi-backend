package me.wcc.homenvi.controller;

import me.wcc.base.controller.BaseController;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.InfluxUser;
import me.wcc.homenvi.service.InfluxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 *  influx用户管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-04-23 16:39:19
 */
@RestController("influxUserController.v1")
@RequestMapping("/homenvi/influxusers")
public class InfluxUserController extends BaseController {

    @Autowired
    private InfluxUserService influxUserService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @GetMapping
    public ResponseEntity<Page<InfluxUser>> list(InfluxUser influxUser, @ApiIgnore @SortDefault(value = InfluxUser.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest){
        Page<InfluxUser> list = influxUserService.pageAndSort(pageRequest, influxUser);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "明细")
    @RequestMapping("/{id}")
    public ResponseEntity<InfluxUser> detail(@PathVariable Long id) {
        InfluxUser influxUser = influxUserService.selectByPrimaryKey(id);
        return Results.success(influxUser);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建")
    @PostMapping
    public ResponseEntity<InfluxUser> create(@RequestBody InfluxUser influxUser) {
            influxUserService.insertSelective(influxUser);
        return Results.success(influxUser);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public ResponseEntity<InfluxUser> update(@RequestBody InfluxUser influxUser) {
            influxUserService.updateByPrimaryKeySelective(influxUser);
        return Results.success(influxUser);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResponseEntity<InfluxUser> remove(@PathVariable Long id) {
            influxUserService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
