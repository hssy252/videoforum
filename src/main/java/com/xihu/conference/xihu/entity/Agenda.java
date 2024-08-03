package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
public class Agenda {

   private Long id;

   private String title;

   /**
    * 分类标签
    */
   private String tag;

   /**
    * 封面url
    */
   private String cover;

   /**
    * 对应视频或直播的url
    */
   private String contentUrl;

   private String subTitle;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Timestamp startTime;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Timestamp endTime;

   private String timespan;

   private Integer likeCount;

   private String location;

   /**
    * 0表示已结束，1表示正在直播中
    */
   private Short isLive;

   /**
    * 默认是0,0表示没有资源
    */
   private Long resourceId;

   @JsonIgnore
   private Timestamp updateTime;

   @JsonIgnore
   private Timestamp createTime;

   @JsonIgnore
   private Short isDelete;
}
