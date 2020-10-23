package ruan.demo.mapper;

import ruan.demo.entity.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author ruan
 * @since 2020-10-20
 */
@Mapper
public interface GoodsInfoDao extends BaseMapper<GoodsInfo> {

    List<GoodsInfo> getAllGoodsInventory();
}

