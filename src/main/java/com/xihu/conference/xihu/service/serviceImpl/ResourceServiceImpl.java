package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.mapper.ResouceMapper;
import com.xihu.conference.xihu.service.ResourceService;
import com.xihu.conference.xihu.utils.AliOssUtil;
import com.xihu.conference.xihu.vo.ResourceVO;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private static final String PPT_PREFIX = "ppt/";

    @Autowired
    private ResouceMapper resouceMapper;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public void download(Long id, String name, HttpServletResponse response) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            aliOssUtil.exportOssFile(outputStream, PPT_PREFIX + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ResourceVO> listResources(Short type) {
        return resouceMapper.listResources(type);
    }

    @Transactional
    @Override
    public void upload(MultipartFile file, String cover, String description,Short type) {
        String originalName = file.getOriginalFilename();
        String ossName = UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
        ossName = PPT_PREFIX + ossName;
        String url = "";
        try {
            url = aliOssUtil.upload(file.getBytes(), ossName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resouceMapper.upload(ossName,url,cover,description,type);
    }

}
