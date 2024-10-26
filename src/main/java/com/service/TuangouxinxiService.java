package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TuangouxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TuangouxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TuangouxinxiView;


/**
 * 团购信息
 *
 * @author 
 * @email 
 * @date 2021-04-07 21:27:10
 */
public interface TuangouxinxiService extends IService<TuangouxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TuangouxinxiVO> selectListVO(Wrapper<TuangouxinxiEntity> wrapper);
   	
   	TuangouxinxiVO selectVO(@Param("ew") Wrapper<TuangouxinxiEntity> wrapper);
   	
   	List<TuangouxinxiView> selectListView(Wrapper<TuangouxinxiEntity> wrapper);
   	
   	TuangouxinxiView selectView(@Param("ew") Wrapper<TuangouxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TuangouxinxiEntity> wrapper);
   	
}

