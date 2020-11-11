package ruan.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ruan.common.entity.GoodsInfo;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author ruan
 * @since 2020-10-26
 */
@Mapper
public interface GoodsInfoDao extends BaseMapper<GoodsInfo> {
    List<GoodsInfo> getGoodsCountByIdList(List<Long> goodsIdList);
}

