package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.TuangouxinxiDao;
import com.entity.TuangouxinxiEntity;
import com.service.TuangouxinxiService;
import com.entity.vo.TuangouxinxiVO;
import com.entity.view.TuangouxinxiView;

@Service("tuangouxinxiService")
public class TuangouxinxiServiceImpl extends ServiceImpl<TuangouxinxiDao, TuangouxinxiEntity> implements TuangouxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TuangouxinxiEntity> page = this.selectPage(
                new Query<TuangouxinxiEntity>(params).getPage(),
                new EntityWrapper<TuangouxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TuangouxinxiEntity> wrapper) {
		  Page<TuangouxinxiView> page =new Query<TuangouxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<TuangouxinxiVO> selectListVO(Wrapper<TuangouxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TuangouxinxiVO selectVO(Wrapper<TuangouxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TuangouxinxiView> selectListView(Wrapper<TuangouxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TuangouxinxiView selectView(Wrapper<TuangouxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
