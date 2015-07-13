package org.lgb.web;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.lgb.model.File;
import org.lgb.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
@Api(value = "file", description = "gets some data from a servlet")
public class FileController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "fileService")
	private FileService fileService;

	@RequestMapping("{fileId}")
	@ApiOperation(httpMethod = "GET", value = "Resource to get a user", nickname="view", position=1)
	public File get(@PathVariable Long fileId) {
		return fileService.get(fileId);
	}

}