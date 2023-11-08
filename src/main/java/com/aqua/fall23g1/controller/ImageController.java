package com.aqua.fall23g1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.constant.Status;
import com.aqua.fall23g1.entity.Image;
import com.aqua.fall23g1.service.ImageStorageService;
import com.aqua.fall23g1.utils.JSONUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/image")
@Tag(name = "Image Management")
public class ImageController {

	@Autowired
	ImageStorageService storageService;

	private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Operation(summary ="Upload File")
	@PostMapping("upload")
	public JSONObject uploadFile(@RequestBody MultipartFile file) {
		String message = "";
		JSONObject resp;
		JSONObject body = new JSONObject();

		try {
			storageService.save(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			resp = JSONUtil.respImage(Status.SUCCESS, " Upload success", message,file.getOriginalFilename(),file.getContentType(), file.getSize() );
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			body.put("upload", false);
			body.put("message", message);
			resp = JSONUtil.resp(Status.FAILED, "Upload failed.", body);
			logger.info(e.getMessage());
		}
		return resp;
	}

    @Operation(summary ="Get Files")
	@GetMapping("files")
	public ResponseEntity<List<Image>> getListFiles() {
		List<Image> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();

			return new Image(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
