package com.xihu.conference.xihu.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
public class IntegralRecord {

   private Long id;

   private Long userId;

   /**
    * 0代表积分增加，1代表积分减少
    */
   private Integer type;

   /**
    * 对应商品或者积分获取行为的id
    */
   private Long matterId;

   /**
    * 积分的变化数量
    */
   private Integer changeNum;

   @JsonIgnore
   private Timestamp updateTime;

   @JsonIgnore
   private Timestamp createTime;

   @JsonIgnore
   private Short isDelete;
}
