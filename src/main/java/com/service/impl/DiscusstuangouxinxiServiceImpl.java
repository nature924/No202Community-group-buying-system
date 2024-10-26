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


import com.dao.DiscusstuangouxinxiDao;
import com.entity.DiscusstuangouxinxiEntity;
import com.service.DiscusstuangouxinxiService;
import com.entity.vo.DiscusstuangouxinxiVO;
import com.entity.view.DiscusstuangouxinxiView;

@Service("discusstuangouxinxiService")
public class DiscusstuangouxinxiServiceImpl extends ServiceImpl<DiscusstuangouxinxiDao, DiscusstuangouxinxiEntity> implements DiscusstuangouxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusstuangouxinxiEntity> page = this.selectPage(
                new Query<DiscusstuangouxinxiEntity>(params).getPage(),
                new EntityWrapper<DiscusstuangouxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstuangouxinxiEntity> wrapper) {
		  Page<DiscusstuangouxinxiView> page =new Query<DiscusstuangouxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscusstuangouxinxiVO> selectListVO(Wrapper<DiscusstuangouxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusstuangouxinxiVO selectVO(Wrapper<DiscusstuangouxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusstuangouxinxiView> selectListView(Wrapper<DiscusstuangouxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusstuangouxinxiView selectView(Wrapper<DiscusstuangouxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
