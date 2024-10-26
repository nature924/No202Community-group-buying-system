package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.TuangouxinxiEntity;
import com.entity.view.TuangouxinxiView;

import com.service.TuangouxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 团购信息
 * 后端接口
 * @author 
 * @email 
 * @date 2021-04-07 21:27:10
 */
@RestController
@RequestMapping("/tuangouxinxi")
public class TuangouxinxiController {
    @Autowired
    private TuangouxinxiService tuangouxinxiService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TuangouxinxiEntity tuangouxinxi,
		HttpServletRequest request){
        EntityWrapper<TuangouxinxiEntity> ew = new EntityWrapper<TuangouxinxiEntity>();
		PageUtils page = tuangouxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuangouxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TuangouxinxiEntity tuangouxinxi, HttpServletRequest request){
        EntityWrapper<TuangouxinxiEntity> ew = new EntityWrapper<TuangouxinxiEntity>();
		PageUtils page = tuangouxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuangouxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TuangouxinxiEntity tuangouxinxi){
       	EntityWrapper<TuangouxinxiEntity> ew = new EntityWrapper<TuangouxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tuangouxinxi, "tuangouxinxi")); 
        return R.ok().put("data", tuangouxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TuangouxinxiEntity tuangouxinxi){
        EntityWrapper< TuangouxinxiEntity> ew = new EntityWrapper< TuangouxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tuangouxinxi, "tuangouxinxi")); 
		TuangouxinxiView tuangouxinxiView =  tuangouxinxiService.selectView(ew);
		return R.ok("查询团购信息成功").put("data", tuangouxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TuangouxinxiEntity tuangouxinxi = tuangouxinxiService.selectById(id);
        return R.ok().put("data", tuangouxinxi);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TuangouxinxiEntity tuangouxinxi = tuangouxinxiService.selectById(id);
        return R.ok().put("data", tuangouxinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        TuangouxinxiEntity tuangouxinxi = tuangouxinxiService.selectById(id);
        if(type.equals("1")) {
        	tuangouxinxi.setThumbsupnum(tuangouxinxi.getThumbsupnum()+1);
        } else {
        	tuangouxinxi.setCrazilynum(tuangouxinxi.getCrazilynum()+1);
        }
        tuangouxinxiService.updateById(tuangouxinxi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TuangouxinxiEntity tuangouxinxi, HttpServletRequest request){
    	tuangouxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(tuangouxinxi);
        tuangouxinxiService.insert(tuangouxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TuangouxinxiEntity tuangouxinxi, HttpServletRequest request){
    	tuangouxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(tuangouxinxi);
        tuangouxinxiService.insert(tuangouxinxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TuangouxinxiEntity tuangouxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tuangouxinxi);
        tuangouxinxiService.updateById(tuangouxinxi);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tuangouxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<TuangouxinxiEntity> wrapper = new EntityWrapper<TuangouxinxiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = tuangouxinxiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	


}
