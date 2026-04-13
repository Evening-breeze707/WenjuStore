package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("file")
public class FileController{
	@Autowired
    private ConfigService configService;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
    @IgnoreAuth
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		File upload = getUploadDir();
		String fileName = new Date().getTime()+"."+fileExt;
        if(StringUtils.isNotBlank(type) && type.contains("_template")) {
            fileName = type + "."+fileExt;
            new File(upload.getAbsolutePath()+"/"+fileName).deleteOnExit();
        }
		File dest = new File(upload.getAbsolutePath()+"/"+fileName);
		file.transferTo(dest);
		/**
  		 * 如果使用idea或者eclipse重启项目，发现之前上传的图片或者文件丢失，将下面一行代码注释打开
   		 * 请将以下的"D:\\springbootq33sd\\src\\main\\resources\\static\\upload"替换成你本地项目的upload路径，
 		 * 并且项目路径不能存在中文、空格等特殊字符
 		 */
//		FileUtils.copyFile(dest, new File("D:\\springbootq33sd\\src\\main\\resources\\static\\upload"+"/"+fileName)); /**修改了路径以后请将该行最前面的//注释去掉**/
		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}
		return R.ok().put("file", fileName);
	}
	
	/**
	 * 下载文件
	 */
	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			File upload = getUploadDir();
			File file = new File(upload.getAbsolutePath()+"/"+fileName);
			if(file.exists()){
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
			    headers.setContentDispositionFormData("attachment", fileName);    
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private File getUploadDir() throws IOException {
		File upload = new File("src/main/resources/static/upload");
		if(!upload.exists()) {
			upload.mkdirs();
		}
		return upload;
	}

	/**
	 * 清理未被数据库引用的图片
	 * @param preview true: 仅预览，不删除；false: 执行删除
	 */
	@RequestMapping("/cleanup-unused")
	public R cleanupUnused(@RequestParam(defaultValue = "true") boolean preview) throws IOException {
		File uploadDir = getUploadDir();
		File[] files = uploadDir.listFiles();
		if(files == null) {
			return R.ok().put("preview", preview).put("scanned", 0).put("deleted", 0).put("candidates", new ArrayList<>());
		}

		Set<String> usedFiles = collectUsedFileNames();
		List<String> candidates = new ArrayList<>();
		int deleted = 0;

		for(File f : files) {
			if(!f.isFile()) {
				continue;
			}
			String fileName = f.getName();
			if(!usedFiles.contains(fileName)) {
				candidates.add(fileName);
				if(!preview) {
					if(f.delete()) {
						deleted++;
					}
				}
			}
		}

		Map<String, Object> stat = new HashMap<>();
		stat.put("preview", preview);
		stat.put("scanned", files.length);
		stat.put("used", usedFiles.size());
		stat.put("unused", candidates.size());
		stat.put("deleted", deleted);
		stat.put("candidates", candidates);
		return R.ok().put("data", stat);
	}

	private Set<String> collectUsedFileNames() {
		Set<String> used = new HashSet<>();
		Map<String, String[]> tableColumns = new HashMap<>();
		tableColumns.put("config", new String[]{"value"});
		tableColumns.put("aboutus", new String[]{"picture1", "picture2", "picture3"});
		tableColumns.put("news", new String[]{"picture"});
		tableColumns.put("messages", new String[]{"avatarurl", "cpicture", "rpicture"});
		tableColumns.put("yonghu", new String[]{"touxiang"});
		tableColumns.put("wenjuxinxi", new String[]{"wenjutupian"});
		tableColumns.put("yuleshangpin", new String[]{"shangpintupian"});

		for(Map.Entry<String, String[]> entry : tableColumns.entrySet()) {
			String table = entry.getKey();
			for(String col : entry.getValue()) {
				String sql = "select " + col + " from " + table + " where " + col + " is not null and " + col + " <> ''";
				List<String> values = jdbcTemplate.queryForList(sql, String.class);
				for(String v : values) {
					if(v == null) continue;
					String[] arr = v.split(",");
					for(String item : arr) {
						String name = normalizeToFileName(item);
						if(name != null && name.length() > 0) {
							used.add(name);
						}
					}
				}
			}
		}
		return used;
	}

	private String normalizeToFileName(String raw) {
		if(raw == null) return null;
		String s = raw.trim();
		if(s.length() == 0) return null;
		s = s.replace("\\", "/");
		int q = s.indexOf("?");
		if(q >= 0) {
			s = s.substring(0, q);
		}
		if(s.contains("/")) {
			s = s.substring(s.lastIndexOf("/") + 1);
		}
		return s;
	}
	
}
