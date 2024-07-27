package com.xihu.conference.xihu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestAgendaVO {

    private Long agendaId;

    private String agendaTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp agendaStartTime;

}
