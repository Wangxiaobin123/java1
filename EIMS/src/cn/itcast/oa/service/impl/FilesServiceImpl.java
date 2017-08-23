package cn.itcast.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Files;
import cn.itcast.oa.service.FilesService;
@Service
@Transactional
public class FilesServiceImpl extends DaoSupportImpl<Files> implements FilesService{

}
