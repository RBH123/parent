package ruan.provider.pojo.vo;

import java.math.BigInteger;
import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* 
*
* @author ruan
* @since 2021-01-27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRecordVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 
    */
    private BigInteger recordId;
    /**
    * 用户id
    */
    private BigInteger userId;
    /**
    * token
    */
    private String token;
    /**
    * token状态，0--正常，1--失效
    */
    private Integer status;
    /**
    * 创建时间
    */
    private Timestamp createdTime;
    /**
    * 更新时间
    */
    private Timestamp updatedTime;
}
