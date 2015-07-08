package org.lgb.web;

import org.apache.log4j.Logger;

import javax.annotation.Resource;

import org.lgb.model.File;
import org.lgb.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "fileService")
	private FileService fileService;

	@RequestMapping("{fileId}")
	public File get(@PathVariable Long fileId) {
		return fileService.get(fileId);
	}

}