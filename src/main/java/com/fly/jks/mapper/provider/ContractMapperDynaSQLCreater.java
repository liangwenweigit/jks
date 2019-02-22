package com.fly.jks.mapper.provider;

import com.fly.jks.domain.Contract;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/2/22 - 15:20
 */
public class ContractMapperDynaSQLCreater {
    /**
     * 真删除1条/批量删除
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM contract WHERE contract_id IN ("+sql+")");
    }

    /**
     *批量/单 合同状态  private String contract_state;//状态1未完成  0完成
     * @param sql
     * @return
     */
    public String updateStopStateSQL(String sql){
        return new String("UPDATE contract SET contract_state = '0' WHERE contract_id IN ("+sql+")");
    }

    /**
     *批量/单 合同状态  private String contract_state;//状态1未完成  0完成
     * @param sql
     * @return
     */
    public String updateStartStateSQL(String sql){
        return new String("UPDATE contract SET contract_state = '1' WHERE contract_id IN ("+sql+")");
    }
    /**
     * 新增一条动态sql
     * @return
     */
    public String insertContractSQL(){
        return new SQL(){{
            INSERT_INTO("contract");
            VALUES("contract_id, offeror, contract_no, signing_date, input_by, check_by, inspector, total_price, crequest, customer_name, ship_date, import_num, instructions, print_style, old_state, contract_state, out_state, delivery_date, trade_clause, create_by, create_bept, create_time",
                    "#{contract_id}, #{offeror}, #{contract_no}, #{signing_date}, #{input_by}, #{check_by}, #{inspector}, #{total_price}, #{crequest}, #{customer_name}, #{ship_date}, #{import_num}, #{instructions}, #{print_style}, #{old_state}, #{contract_state}, #{out_state}, #{delivery_date}, #{trade_clause}, #{create_by}, #{create_bept}, #{create_time}");
        }}.toString();
    }

    /**
     * 更新
     * @return
     */
    public String updateContractSQL(Contract contract){
        return new SQL(){{
            UPDATE("contract");
            if (contract.getOfferor()!=null){
                SET(" offeror= #{offeror}");
            }
            if (contract.getContract_no()!=null){
                SET("contract_no = #{contract_no}");
            }
            if (contract.getSigning_date()!=null){
                SET("signing_date = #{signing_date}");
            }
            if (contract.getInput_by()!=null){
                SET("input_by = #{input_by}");
            }
            if (contract.getCheck_by()!=null){
                SET("check_by = #{check_by}");
            }
            if (contract.getInspector()!=null){
                SET("inspector = #{inspector}");
            }
            if (contract.getTotal_price()!=null){
                SET("total_price = #{total_price}");
            }
            if (contract.getCrequest()!=null){
                SET("crequest = #{crequest}");
            }
            if (contract.getCustomer_name()!=null){
                SET("customer_name = #{customer_name}");
            }
            if (contract.getShip_date()!=null){
                SET("ship_date = #{ship_date}");
            }
            if (contract.getImport_num()!=null){
                SET("import_num = #{import_num}");
            }
            if (contract.getInstructions()!=null){
                SET("instructions = #{instructions}");
            }
            if (contract.getPrint_style()!=null){
                SET("print_style = #{print_style}");
            }
            if (contract.getOld_state()!=null){
                SET("old_state = #{old_state}");
            }
            if (contract.getContract_state()!=null){
                SET("contract_state = #{contract_state}");
            }
            if (contract.getOut_state()!=null){
                SET("out_state = #{out_state}");
            }
            if (contract.getDelivery_date()!=null){
                SET("delivery_date = #{delivery_date}");
            }
            if (contract.getTrade_clause()!=null){
                SET("trade_clause = #{trade_clause}");
            }
            if (contract.getCreate_by()!=null){
                SET("create_by = #{create_by}");
            }
            if (contract.getCreate_bept()!=null){
                SET("create_bept = #{create_bept}");
            }
            if (contract.getCreate_time()!=null){
                SET("create_time = #{create_time}");
            }
            WHERE("contract_id = #{contract_id}");
        }}.toString();
    }
}
