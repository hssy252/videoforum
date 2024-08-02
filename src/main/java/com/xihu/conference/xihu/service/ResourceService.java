package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.vo.ResourceVO;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface ResourceService {

    void download(Long id, String url, HttpServletResponse response);

    List<ResourceVO> listResources(Short type);

    void upload(MultipartFile file, String cover, String description,Short type);

    String fetchUrl(Long id);
}
