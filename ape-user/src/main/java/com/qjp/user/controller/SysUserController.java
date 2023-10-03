package com.qjp.user.controller;


import com.qjp.bean.PageResponse;
import com.qjp.bean.Result;
import com.qjp.user.entity.po.SysUser;
import com.qjp.user.entity.req.SysUserReq;
import com.qjp.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2023-09-26 15:53:27
 */
@RestController
@RequestMapping("sysUser")
@Api(tags = "系统用户接口")
public class SysUserController  {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

   
   /** 
     * 
     * @param sysUserReq 请求参数封装
     * @author makejava 
     * @description //TODO  分页查询所有数据
     * @date 2023-09-26 15:53:27
     * @return 实例对象
     */
    @PostMapping("/findSysUserSelectList")
    public Result<PageResponse<SysUser>> findSysUserSelectList(SysUserReq sysUserReq ) {
        return Result.ok(sysUserService.queryByPage(sysUserReq));
    }

   
    /** 
     * 
     * @param id 主键
     * @author makejava  
     * @description //TODO  通过主键查询单条数据
     * @date 2023-09-26 15:53:27
     * @return 单条数据
     */
    @GetMapping("/selectSysUserById/{id}")
    @Cacheable(cacheNames = "sysUser",key = "'selectSysUserById'+#id")
    public Result selectSysUserById(@PathVariable Long id) {
        return Result.ok(sysUserService.queryById(id));
    }

    /** 
     * 
     * @author makejava
     * @description //TODO  新增数据
     * @date 2023-09-26 15:53:27
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增用户",notes = "用户数据")
    public Result insert(SysUser sysUser) {
        SysUser user = sysUserService.insert(sysUser);
        return Result.ok(user);
    }

    /** 
     * 
     * @param sysUserdto 实体对象
     * @author makejava  
     * @description //TODO  修改数据
     * @date 2023-09-26 15:53:27
     * @return 修改结果
     */
    @PutMapping
    public Result update(SysUser SysUser) {
         return Result.ok(sysUserService.update(SysUser));
    }

    /** 
     * 
     * @param idList 主键集合
     * @author makejava  
     * @description //TODO  删除数据
     * @date 2023-09-26 15:53:27
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(Long id) {
         return Result.ok(sysUserService.deleteById(id));
    }
}
