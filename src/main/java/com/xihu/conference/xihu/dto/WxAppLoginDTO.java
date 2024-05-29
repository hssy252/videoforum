package com.xihu.conference.xihu.dto;

import lombok.AllArgsConstructor;
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
public class WxAppLoginDTO {

    private String nickName;

    private String phone;

}
