package com.streammovie.common;

import java.io.File;

public class PathCollection {

	public static final String RESOURCES_FOLDER_PATH = "D:\\Study\\p01076322603\\StreamMovie\\WebContent\\resources";
	public static final String HOME_URL = "http://localhost:8080/StreamMovie/";
	
	public static final String TEMP_FOLDER_PATH = RESOURCES_FOLDER_PATH + File.separator + "temp";
	public static final String IMAGE_FOLDER_PATH = RESOURCES_FOLDER_PATH + File.separator + "image";

	public static final String IMAGE_MEMBER_FOLDER_PATH = IMAGE_FOLDER_PATH + File.separator + "member";
	public static final String IMAGE_BOARD_FOLDER_PATH = IMAGE_FOLDER_PATH + File.separator + "board";
}
