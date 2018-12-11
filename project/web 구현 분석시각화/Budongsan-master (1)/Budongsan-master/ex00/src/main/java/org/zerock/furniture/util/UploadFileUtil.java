package org.zerock.furniture.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.furniture.dto.FurnitureDTO;

public class UploadFileUtil
{

	// private static final Logger logger =
	// LoggerFactory.getLogger(UploadFileUtil.class);

	// 서버 파일의 경로를 가지고 있는 String
	private static final String loacalPathImgae = "D:/workspace/STS/budongsang/Budongsan/ex00/src/main/webapp/resources/saveImage";
	//뷰이미지 저장 갯수
	private static int MAX_IMGCOUNT = 3;

	//뷰이미지 저장 갯수 리턴 메서드
	public static int getMAX_imgSize ()  { return MAX_IMGCOUNT;}
	
	// 뷰 이미지 저장  메서드
	public static void saveViewImg (List<MultipartFile> files, String name, String Path) throws IOException
	{
		int index=0;
		
		  for (MultipartFile temp : files )
			{
				if (index >= UploadFileUtil.getMAX_imgSize())
					break;
		
				UploadFileUtil.saveImg(temp.getBytes(), name+"_"+index ,Path);
				index++;
			}
	}
	
	
	
	
	// 데이터 베이스에서 파일 가져와서 서버에 저장하는 메서드
	public static List<FurnitureDTO> DownloadImg(List<FurnitureDTO> list, String Path)
	{
		FurnitureDTO tempDTO = null;
		
		for (int i = 0; i < list.size(); i++)
		{
			tempDTO = list.get(i);
			System.out.println(tempDTO);
			ImgSave(tempDTO.getPicture(), tempDTO.getTitle(), "jpg", Path);

		}

		return list;
	}

	// 이미지 파일을 저장하는 메서드 (실제 동작은 ImgSave) 리스트용
	public static void saveImg(FurnitureDTO temp, String Path)
	{

		ImgSave(temp.getPicture(), ""+ temp.getId(), "jpg", Path);

	}
	
	// 이미지 파일을 저장하는 메서드 (실제 동작은 ImgSave) 뷰용
	public static void saveImg (byte[] temp, String name, String Path)
	{
		ImgSave(temp, ""+ name, "jpg", Path);
	}

	
	
	// 폴더가 생성 되지 않았을 경우 폴더를 생성 해주는 메서드
	private static void isCreateFolder(String path)
	{
		File file = new File(path);
		if(!file.exists())
		{
			file.mkdirs();
			System.out.println("create directory succfully");
		}
	}
	
	//삭제 루틴
	public static void ImgDelect (String path, String FileName)
	{
		DelectImg(loacalPathImgae,FileName);
		DelectImg(path,FileName);
	}
	
	// 뷰 이미지 삭제 루틴
	public static void ImgViewDelect (String path, String FileName)
	{	
		for (int i =0; i<getMAX_imgSize(); i++)
		{
			System.out.println(FileName+"_"+i+".jpg");
			DelectImg(loacalPathImgae,FileName+"_"+i+".jpg");
			DelectImg(path,FileName+"_"+i+".jpg");
		}
		
	}
	
	

	private static void DelectImg (String path, String FileName)
	{
		File file = new File (path+"/"+FileName);
		if (file.exists())
		{
			if(file.delete())
			{
                System.out.println("파일삭제 성공");
            }
			else
			{
                System.out.println("파일삭제 실패");
			}
		}
			
		else
		{
	         System.out.println("파일이 존재하지 않습니다.");
		}


		
	}
	
	
	
	// 파일 이름이 같은가를 확인 해주는 메서드
	private static boolean isEqualFileName (String path, String FileName)
	{
		File file = new File (path);
		File[] fileList = file.listFiles(); 
		
		for (int i=0; i<fileList.length; i++)
		{
			File f = fileList[i];
			//System.out.println(f.getName());
			
		}
		
		return true;
	}
	
	
	// 파일,이름,확장자,경로를 받아서 파일을 저장하는 메서드 
	private static void ImgSave (byte[] temp, String name, String Extensions, String Path)
	{
		
		System.out.println(temp.length);
		InputStream in = new ByteArrayInputStream(temp);
		BufferedImage bimg = null;
		
		isCreateFolder(Path);
		isCreateFolder(loacalPathImgae);
		
		isEqualFileName(Path,"/temp."+ Extensions);
		try
		{
			bimg = ImageIO.read(in);
			ImageIO.write(bimg, Extensions, new File(Path+"/"+ name+"."+ Extensions));
			ImageIO.write(bimg, Extensions, new File(loacalPathImgae +"/"+name+"."+ Extensions));
			
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	

//	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception
//	{
//
//		UUID uid = UUID.randomUUID();
//		String savedName = uid.toString() + "_" + originalName;
//		String savedPath = calcPath(uploadPath);
//		File target = new File(uploadPath + savedPath, savedName);
//		FileCopyUtils.copy(fileData, target);
//		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
//		String uploadedFileName = null;
//
//		if(MediaUtils.getMediaType(formatName) != null)
//		{
//			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
//		} else
//		{
//			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
//		}
//
//		return uploadedFileName;
//
//	}
//
//	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception
//	{
//
//		String iconName = uploadPath + path + File.separator + fileName;
//		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
//	}
//
//	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception
//	{
//
//		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
//		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
//		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
//		File newFile = new File(thumbnailName);
//		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
//		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
//		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
//	}
//
//	private static String calcPath(String uploadPath)
//	{
//
//		Calendar cal = Calendar.getInstance();
//		String yearPath = File.separator + cal.get(Calendar.YEAR);
//		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
//		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
//		makeDir(uploadPath, yearPath, monthPath, datePath);
//		logger.info(datePath);
//		return datePath;
//	}
//
//	private static void makeDir(String uploadPath, String... paths)
//	{
//
//		if(new File(paths[paths.length - 1]).exists())
//		{
//			return;
//		}
//
//		for (String path : paths)
//		{
//
//			File dirPath = new File(uploadPath + path);
//
//			if(!dirPath.exists())
//			{
//				dirPath.mkdir();
//			}
//		}
//	}

}