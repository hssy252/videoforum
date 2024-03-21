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
public class Subscription {

  private Long id;

  private Long userId;

  private Long matterId;

  /**
   * 0代表会议，1则代表活动
   */
  private Short type;

  /**
   * 1表示已删除，0表示为删除
   */
  @JsonIgnore
  private Short isDelete;

  @JsonIgnore
  private Timestamp updateTime;

  @JsonIgnore
  private Timestamp createTime;

}
