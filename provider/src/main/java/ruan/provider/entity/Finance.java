package ruan.provider.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 融资表
 * </p>
 *
 * @author ruan
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sy_finance")
public class Finance extends Model<Finance> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "finance_id", type = IdType.INPUT)
    private Long financeId;

    /**
     * 转让申请编号
     */
    @TableField("transfer_code")
    private String transferCode;

    /**
     * 转让通知函编号
     */
    @TableField("transfer_no")
    private String transferNo;

    /**
     * 付款申请编号
     */
    @TableField("finance_code")
    private String financeCode;

    /**
     * 付款申请书编号
     */
    @TableField("finance_no")
    private String financeNo;

    /**
     * 融资申请书编号
     */
    @TableField("finance_apply_no")
    private String financeApplyNo;

    /**
     * 业务ID(融资ID或者转应收ID)
     */
    @TableField("business_id")
    private String businessId;

    /**
     * 转应收编号（资金性质为转应收才有值）
     */
    @TableField("receivable_number")
    private String receivableNumber;

    /**
     * 客户ID
     */
    @TableField("customer_id")
    private String customerId;

    /**
     * 客户名称
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 行业分类：1、基建工程 2、医药医疗 3、能源化工
     */
    @TableField("industry")
    private String industry;

    /**
     * 买方ID
     */
    @TableField("buyer_id")
    private String buyerId;

    /**
     * 买方名称
     */
    @TableField("buyer_name")
    private String buyerName;

    /**
     * 保理商名称
     */
    @TableField("factor_name")
    private String factorName;

    /**
     * 保理商OAID
     */
    @TableField("factor_oa_id")
    private String factorOaId;

    /**
     * 项目名称
     */
    @TableField("project_name")
    private String projectName;

    /**
     * 转让金额
     */
    @TableField("transfer_amount")
    private BigDecimal transferAmount;

    /**
     * 放款金额或转应收金额，当资金类型为转应收时则为转应收金额
     */
    @TableField("transaction_amount")
    private BigDecimal transactionAmount;

    /**
     * 建议放款日期
     */
    @TableField("suggest_loan_date")
    private Date suggestLoanDate;

    /**
     * 业务模式: 1.订单保理 2. 到货保理 3.应收保理 4.票据保理 5.池保理
     */
    @TableField("biz_model")
    private Integer bizModel;

    /**
     * 融资阶段 1.订单期 2.到货期 3.应收期
     */
    @TableField("finance_stage")
    private Integer financeStage;

    /**
     * 转应收日期
     */
    @TableField("receivable_date")
    private Date receivableDate;

    /**
     * 应收账款到期日
     */
    @TableField("receivable_account_date")
    private Date receivableAccountDate;

    /**
     * 即期天数
     */
    @TableField("working_days")
    private Integer workingDays;

    /**
     * 融资起始日
     */
    @TableField("finance_start_date")
    private Date financeStartDate;

    /**
     * 融资到期日
     */
    @TableField("finance_end_date")
    private Date financeEndDate;

    /**
     * 产值起始日
     */
    @TableField("prd_start_date")
    private Date prdStartDate;

    /**
     * 产值截止日
     */
    @TableField("prd_end_date")
    private Date prdEndDate;

    /**
     * 资金性质 1.资金流转 2.转应收
     */
    @TableField("finance_nature")
    private Integer financeNature;

    /**
     * 是否自动生成还款计划 1.是 0.否，当报价信息中利息收取方式为其他时则为0
     */
    @TableField("auto_repayment_plan")
    private Integer autoRepaymentPlan;

    /**
     * 放款状态：1.全额放款 2.部分放款，当放款明细合计金额等于计划放款金额时则为1，否为2
     */
    @TableField("loan_status")
    private Integer loanStatus;

    /**
     * 还款状态：1.正常 2.结清 3.逾期
     */
    @TableField("repayment_status")
    private Integer repaymentStatus;

    /**
     * 首次放款日期
     */
    @TableField("first_loan_date")
    private Date firstLoanDate;

    /**
     * 最近放款日期
     */
    @TableField("latest_loan_date")
    private Date latestLoanDate;

    /**
     * 实际放款金额合计
     */
    @TableField("actual_loan_amount")
    private BigDecimal actualLoanAmount;

    /**
     * 是否已挂单 0.否 1.是
     */
    @TableField("is_paused")
    private Integer isPaused;

    /**
     * 挂单类型 1.手动挂单 2.展期挂单
     */
    @TableField("paused_type")
    private Integer pausedType;

    /**
     * 挂单日期
     */
    @TableField("paused_date")
    private Date pausedDate;

    /**
     * 融资余额
     */
    @TableField("finance_balance_amount")
    private BigDecimal financeBalanceAmount;

    /**
     * 是否已生成还款计划 1.是 0.否
     */
    @TableField("has_created_plan")
    private Integer hasCreatedPlan;

    /**
     * 业务系统来源 SYT:盛易通 SDT:盛达通
     */
    @TableField("biz_system")
    private String bizSystem;

    /**
     * 租户
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 扩展字段
     */
    @TableField("extend_filed_json")
    private String extendFiledJson;

    /**
     * 是否已删除 1.删除 0.未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Timestamp createdTime;

    /**
     * 最后更新人
     */
    @TableField("last_updated_by")
    private Long lastUpdatedBy;

    /**
     * 最后更新时间
     */
    @TableField("last_updated_time")
    private Timestamp lastUpdatedTime;

    /**
     * 是否excel历史数据 1:是 0否
     */
    @TableField("is_his_data")
    private Integer isHisData;


    public static final String FINANCE_ID = "finance_id";

    public static final String TRANSFER_CODE = "transfer_code";

    public static final String TRANSFER_NO = "transfer_no";

    public static final String FINANCE_CODE = "finance_code";

    public static final String FINANCE_NO = "finance_no";

    public static final String FINANCE_APPLY_NO = "finance_apply_no";

    public static final String BUSINESS_ID = "business_id";

    public static final String RECEIVABLE_NUMBER = "receivable_number";

    public static final String CUSTOMER_ID = "customer_id";

    public static final String CUSTOMER_NAME = "customer_name";

    public static final String INDUSTRY = "industry";

    public static final String BUYER_ID = "buyer_id";

    public static final String BUYER_NAME = "buyer_name";

    public static final String FACTOR_NAME = "factor_name";

    public static final String FACTOR_OA_ID = "factor_oa_id";

    public static final String PROJECT_NAME = "project_name";

    public static final String TRANSFER_AMOUNT = "transfer_amount";

    public static final String TRANSACTION_AMOUNT = "transaction_amount";

    public static final String SUGGEST_LOAN_DATE = "suggest_loan_date";

    public static final String BIZ_MODEL = "biz_model";

    public static final String FINANCE_STAGE = "finance_stage";

    public static final String RECEIVABLE_DATE = "receivable_date";

    public static final String RECEIVABLE_ACCOUNT_DATE = "receivable_account_date";

    public static final String WORKING_DAYS = "working_days";

    public static final String FINANCE_START_DATE = "finance_start_date";

    public static final String FINANCE_END_DATE = "finance_end_date";

    public static final String PRD_START_DATE = "prd_start_date";

    public static final String PRD_END_DATE = "prd_end_date";

    public static final String FINANCE_NATURE = "finance_nature";

    public static final String AUTO_REPAYMENT_PLAN = "auto_repayment_plan";

    public static final String LOAN_STATUS = "loan_status";

    public static final String REPAYMENT_STATUS = "repayment_status";

    public static final String FIRST_LOAN_DATE = "first_loan_date";

    public static final String LATEST_LOAN_DATE = "latest_loan_date";

    public static final String ACTUAL_LOAN_AMOUNT = "actual_loan_amount";

    public static final String IS_PAUSED = "is_paused";

    public static final String PAUSED_TYPE = "paused_type";

    public static final String PAUSED_DATE = "paused_date";

    public static final String FINANCE_BALANCE_AMOUNT = "finance_balance_amount";

    public static final String HAS_CREATED_PLAN = "has_created_plan";

    public static final String BIZ_SYSTEM = "biz_system";

    public static final String TENANT_ID = "tenant_id";

    public static final String REMARK = "remark";

    public static final String EXTEND_FILED_JSON = "extend_filed_json";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_BY = "created_by";

    public static final String CREATED_TIME = "created_time";

    public static final String LAST_UPDATED_BY = "last_updated_by";

    public static final String LAST_UPDATED_TIME = "last_updated_time";

    public static final String IS_HIS_DATA = "is_his_data";

    @Override
    protected Serializable pkVal() {
        return this.financeId;
    }

}
