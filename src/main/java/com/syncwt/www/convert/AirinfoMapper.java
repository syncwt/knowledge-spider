/**
 * 
 */
package com.syncwt.www.convert;

import com.syncwt.www.domain.po.AircubeInfo;
import com.syncwt.www.domain.vo.AircubeInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName           : AirinfoMapper
 * @Description         :
 * @Author              : xujialin
 * @CreationDate        : 2016年12月15日下午4:10:20
 */
@Mapper
public interface AirinfoMapper {
	AirinfoMapper INSTANCE = Mappers.getMapper(AirinfoMapper.class);

	AircubeInfoVo poTovoDto(AircubeInfo airinfo);

	AircubeInfo voTopo(AircubeInfoVo vo);
}
