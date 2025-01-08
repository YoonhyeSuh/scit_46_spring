package net.scit.spring7.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public class FileService {
	// 저장파일명을 반환
	public static String saveFile(MultipartFile uploadFile, String uploadPath) {
		
		//저장 디렉토리 생성
		if(!uploadFile.isEmpty()) {//파일이 존재한다면
			File path = new File(uploadPath);
			if(!path.isDirectory()) {//디렉토리가 존재하지 않으면 생성해라
				path.mkdirs();
			}
		}
		String originalFileName = uploadFile.getOriginalFilename();		//원본파일명
		String savedFileName = null;		//저장할 때 사용할 파일명
		String filename = null;				//파일명
		String ext = null;					//파일 형식(jpg)
		String uuid = UUID.randomUUID().toString();		//난수
		
		
		//.의 위치 찾기
		//이 코드를 쓴 이유는 난수를 발생하기 위해서
		int position = originalFileName.lastIndexOf(".");
		if(position == -1) {//확장자가 없는 파일
			ext = "";			
		} else {
			ext = "." + originalFileName.substring(position+1);
			filename = originalFileName.substring(0, position);
		}
		
		savedFileName = filename + "_" + uuid + ext;
	
		//디렉토리에 저장하기
		//c:/uploadPath/savedFileName
		String fullPath = uploadPath + "/" + savedFileName;	//문자열
		
		File serverFile = null;
		serverFile = new File(fullPath);
		
		try {
			uploadFile.transferTo(serverFile);
		} catch (IOException e) {	//저장장치에 저장이 안된것이므로 DB도 저장하면 안됨
			savedFileName = null;
			e.printStackTrace();
		}
		
		
		return savedFileName;
	}

	//파일 삭제
   public static boolean deleteFile(String fullPath) {
      boolean result = false;    //삭제여부 반환
      
      File file = new File(fullPath);
      if(file.isFile()) {
         file.delete();
      }
      return result;
   }	
}
