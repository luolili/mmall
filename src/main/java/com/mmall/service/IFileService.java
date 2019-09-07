package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    //path : 上传文件的path
    String upload(MultipartFile file, String path);


}
