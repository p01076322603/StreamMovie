package com.streammovie.common;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileManagement {

	public static Map<String, String> fileUploadToTemp(HttpServletRequest request, Map<String, String> paramMap) {

		String tempFolderPath = PathCollection.TEMP_FOLDER_PATH;
		File tempFolderDir = new File(tempFolderPath);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(tempFolderDir);
		factory.setSizeThreshold(50 * 1024 * 1024); // 50Kb
		ServletFileUpload fileUpload = new ServletFileUpload(factory);

		Map<String, String> filenameMap = new HashMap<>();

		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem fileItem : items) {

				if (fileItem.isFormField()) {
					paramMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));

				} else {
					if (fileItem.getSize() > 0) {

						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String filename = fileItem.getName().substring(idx + 1);
						filenameMap.put(fileItem.getFieldName(), filename);

						File tempFile = new File(tempFolderDir + "\\" + filename);
						fileItem.write(tempFile);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filenameMap;

	}

	public static Map<String, String> fileUploadToTemp(HttpServletRequest request) {

		String tempFolderPath = PathCollection.TEMP_FOLDER_PATH;
		File tempFolderDir = new File(tempFolderPath);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(tempFolderDir);
		factory.setSizeThreshold(50 * 1024 * 1024); // 50Kb
		ServletFileUpload fileUpload = new ServletFileUpload(factory);

		Map<String, String> filenameMap = new HashMap<>();

		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem fileItem : items) {

				if (!fileItem.isFormField()) {
					if (fileItem.getSize() > 0) {

						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String filename = fileItem.getName().substring(idx + 1);
						filenameMap.put(fileItem.getFieldName(), filename);

						File tempFile = new File(tempFolderDir + "\\" + filename);
						fileItem.write(tempFile);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filenameMap;

	}

	public static Map<String, String> fileUploadToTempWithUUID(HttpServletRequest request, UUID uuid) {

		String tempFolderPath = PathCollection.TEMP_FOLDER_PATH;
		File tempFolderDir = new File(tempFolderPath);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(tempFolderDir);
		factory.setSizeThreshold(1 * 1024 * 1024 * 1024); // 1Mb
		ServletFileUpload fileUpload = new ServletFileUpload(factory);

		Map<String, String> filenameMap = new HashMap<>();

		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem fileItem : items) {

				if (!fileItem.isFormField()) {
					if (fileItem.getSize() > 0) {

						int index = 0;
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String filename = fileItem.getName().substring(idx + 1);
						filenameMap.put(fileItem.getFieldName(), filename);
						filenameMap.put("uuid" + (++index), uuid.toString());

						String extension = filename.substring(filename.indexOf("."));
						String uuidFilename = uuid + extension;

						File tempFile = new File(tempFolderDir + File.separator + uuidFilename);
						fileItem.write(tempFile);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filenameMap;

	}

}
