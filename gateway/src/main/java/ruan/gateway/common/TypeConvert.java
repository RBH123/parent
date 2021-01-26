package ruan.gateway.common;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;


public class TypeConvert extends MySqlTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        if (fieldType.toLowerCase().contains("bigint")) {
            return DbColumnType.BIG_INTEGER;
        }
        return super.processTypeConvert(config, fieldType);
    }
}
