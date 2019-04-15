package me.wcc.homenvi.controller;

import io.swagger.annotations.Api;
import me.wcc.base.domain.Page;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.Collector;
import me.wcc.homenvi.exception.UnauthorizedCollectorException;
import me.wcc.homenvi.service.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 终端采集节点 管理 API
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-04-12 19:17:00
 */
@Api(tags = "采集器管理")
@RestController("collectorController")
public class CollectorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectorController.class);

    private final CollectorService collectorService;

    public CollectorController(CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @ApiOperation(value = "采集节点刷新信息")
    @GetMapping("/openApi/collectors/refresh")
    public ResponseEntity<String> refresh(Collector collector) {
        try {
            collectorService.refresh(collector);
        } catch (UnauthorizedCollectorException e) {
            LOGGER.error("Unauthorized Collector : {}", collector);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return Results.success(BaseConstants.FIELD_SUCCESS);
    }


    /**
     * 列表
     */
    @ApiOperation(value = "终端采集节点列表")
    @GetMapping("/homenvi/collectors")
    public ResponseEntity<Page<Collector>> list(Collector collector, @ApiIgnore @SortDefault(value = Collector.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Collector> list = collectorService.pageAndSort(pageRequest, collector);
        return Results.success(list);
    }


    /**
     * 详细
     */
    @ApiOperation(value = "终端采集节点明细")
    @RequestMapping("/homenvi/collectors/{id}")
    public ResponseEntity<Collector> detail(@PathVariable Long id) {
        Collector collector = collectorService.selectByPrimaryKey(id);
        return Results.success(collector);
    }

    /**
     * 创建
     */
    @ApiOperation(value = "创建终端采集节点")
    @PostMapping("/homenvi/collectors")
    public ResponseEntity<Collector> create(@RequestBody Collector collector) {
        collectorService.insertSelective(collector);
        return Results.success(collector);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改终端采集节点")
    @PutMapping("/homenvi/collectors")
    public ResponseEntity<Collector> update(@RequestBody Collector collector) {
        collectorService.updateByPrimaryKeySelective(collector);
        return Results.success(collector);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除终端采集节点")
    @DeleteMapping("/homenvi/collectors/{id}")
    public ResponseEntity<Collector> remove(@PathVariable Long id) {
        collectorService.deleteByPrimaryKey(id);
        return Results.success();
    }

}
