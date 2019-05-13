package me.wcc.base.controller;

import me.wcc.base.domain.Page;
import me.wcc.base.entity.ValueType;
import me.wcc.base.infra.utils.Results;
import me.wcc.base.service.ValueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 取值类型 管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 15:38:17
 */
@RestController("valueTypeController.v1")
@RequestMapping("/v1/valuetypes")
public class ValueTypeController extends BaseController {

    @Autowired
    private ValueTypeService valueTypeService;

    /**
     * 列表
     */
    @ApiOperation(value = "取值类型列表")
    @GetMapping
    public ResponseEntity<Page<ValueType>> list(ValueType valueType, @ApiIgnore @SortDefault(value = ValueType.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<ValueType> list = valueTypeService.pageAndSort(pageRequest, valueType);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "取值类型明细")
    @RequestMapping("/{id}")
    public ResponseEntity<ValueType> detail(@PathVariable Long id) {
        ValueType valueType = valueTypeService.selectByPrimaryKey(id);
        return Results.success(valueType);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建取值类型")
    @PostMapping
    public ResponseEntity<ValueType> create(@RequestBody ValueType valueType) {
        valueTypeService.insertSelective(valueType);
        return Results.success(valueType);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改取值类型")
    @PutMapping
    public ResponseEntity<ValueType> update(@RequestBody ValueType valueType) {
        valueTypeService.updateByPrimaryKeySelective(valueType);
        return Results.success(valueType);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除取值类型")
    @DeleteMapping("/{id}")
    public ResponseEntity<ValueType> remove(@PathVariable Long id) {
        valueTypeService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
