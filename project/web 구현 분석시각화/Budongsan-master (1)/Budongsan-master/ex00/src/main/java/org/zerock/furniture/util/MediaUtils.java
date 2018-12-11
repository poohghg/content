package org.zerock.furniture.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils
{

	private static Map<String, MediaType> mediaMap;

	static
	{

		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type)
	{

		return mediaMap.get(type.toUpperCase());
	}
	
	public static String CreateBR (String connent)
	{
		return connent.replace("\n", "<br>");		
	}
	
	public static String CreateBR (int number)
	{
		String temp = Integer.toString(number);
		temp.replace("(?<=\\d)(?=(\\d{3})+)" , ",");
		return temp;
	}
}