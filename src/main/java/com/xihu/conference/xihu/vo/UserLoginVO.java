package com.xihu.conference.xihu.vo;

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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {

    private String token;

    private String openid;

    private Long userId;
}
