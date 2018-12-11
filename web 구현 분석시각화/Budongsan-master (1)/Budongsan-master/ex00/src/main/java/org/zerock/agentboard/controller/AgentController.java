package org.zerock.agentboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.agentboard.dto.AgentDTO;
import org.zerock.agentboard.dto.Criteria;
import org.zerock.agentboard.dto.FileDTO;
import org.zerock.agentboard.service.AgentService;
import org.zerock.agentboard.util.UploadFileUtils;
import org.zerock.agentboard.util.MediaUtils;

/*
 * 자동생성이 되게 하려면 
 * 1. web.xml에 설정되어 있는 xml 안에 beans > bean 태그를 이용해서 클래스 등록
 * 2. web.xml에 설정되어 있는 xml 안에 context:component-scan 을 이용해서
 *    base-package 속성의 패키지 안에서 클래스를 @Controller, @Service, @Repository,
 *    @Component, @RestController로 지정하면 자동 생성된다.
 */
@Controller
public class AgentController {

	// 사용할 서비스 변수 선언. - DI 적용 : @Inject, @Autowired
	@Inject
	private AgentService service;
	
	// servlet-context.xml에 선언한 bean을 사용한다.
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping("/displayFile")
	//  /localhost/displayFile?filename=~~~
	public ResponseEntity<byte[]> displyFile(String filename)
			throws Exception{
		System.out.println
		(getClass().getSimpleName()+".displayFile().filename");
		
		// 파일을 읽어 올때 사용하는 객체
		InputStream in = null;
		
		// 이미지 인지 알아내는 프로그램: 이미지 -> img tag, 이미지 아니면 -> a tag를 사용(다운)
		String formatName 
		= filename.substring(filename.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		//http를 이용해서 파일의 정보를 확인할 수 있는 데이터 : Header
		HttpHeaders headers = new HttpHeaders();
		
		// 서버의 임의 컴퓨터 안에 파일로 저장된 것을 파일 입력 객체 연결
		in= new FileInputStream(uploadPath+filename);
		
		// 이미지 인지 아닌지 처리하는 프로그램
		// 만약에 이미지 이면 이미지 타입정보를 헤더에 넣어 준다.
		if(mType != null) headers.setContentType(mType);
		// 만약에 이미지 파일이 아니면
		else {
			// 원래의 파일명을 구한다.
			filename = filename.substring(filename.indexOf("_")+1);
			// 헤더에 다운로드하는 파일로 셋팅
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// 원래의 파일명을 헤더에 붙인다. tomcat(ISO-8859-1) http(utf-8)
			headers.add("Content-DisPosition",
					"attachment; filename=\""
					+new String(filename.getBytes("UTF-8"),"ISO-8859-1")
					+"\"");
		}
		
		ResponseEntity<byte[]> entity 
		= new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
				headers, HttpStatus.CREATED);
		in.close();
		return entity;
	}
	
	//첨부파일에 대한 삭제 작업 처리
	@RequestMapping(value = "deleteAllFiles", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@RequestParam("file[]") String[] files){
		System.out.println(getClass().getSimpleName()+".deleteAllFiles");
		
		if(files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		for (String filename : files) {
			String formatName = filename.substring(filename.lastIndexOf(".")+1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			if(mType != null) {
				
				String front = filename.substring(0, 12);
				String end = filename.substring(14);
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			
			new File(uploadPath + filename.replace('/', File.separatorChar)).delete();
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	//Ajax로 호출되는 특정게시물의 첨부파일을 처리하는 메소드
	//호출 경로 '/agentboard/getAttach/게시물 번호', 리턴타입은 문자열 리스트 형태로 작성
	@RequestMapping("/getAttach/{no}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("no")Integer no) {
		return service.getAttach(no);
	}
	
	// 리스트 - DB에서 데이터를 꺼내와서 JSP로 Model객체에 데이터를 담아서 넘긴다.forward
	@RequestMapping(value = "/agentboard/list.do", method = RequestMethod.GET)
	public String list(Model model, Criteria cri) {
		System.out.println(getClass().getSimpleName()+".list()");
//		System.out.println(cri);
		ArrayList<AgentDTO> list = (ArrayList<AgentDTO>) service.list(cri);
		ArrayList<FileDTO> fileList = (ArrayList<FileDTO>) service.fileList();
		
		for(int i = 0; i < list.size(); i++) 
		{
			for (int j=0; j<fileList.size(); j++)
			{
				if (list.get(i).getNo() == fileList.get(j).getNo())
					list.get(i).setFileName(fileList.get(j).getFileName()); 
			}
		}	    
		
		//model.addAttribute("list", service.list(cri));
		model.addAttribute("list", list);
	    // jsp에서 하단부분 페이지 표시할때 cri 객체가 필요하다. model에 담아서 보낸다.
	    model.addAttribute("cri", cri);
		// prefix + return String + suffix
		// /WEB-INF/views/board/list.jsp
		return "agentboard/list";
	}
	// 글보기 - 글번호를 전달 받아서 DB에 글번호에 맞는 BoardDTO를 가져온다. JSP로 전달
	// view.do?no=10
	@RequestMapping(value = "/agentboard/view.do", method = RequestMethod.GET)
	public String view(Model model, int no) {
		System.out.println(getClass().getSimpleName()+".view()");
		model.addAttribute("dto", service.view(no, true));
		// 첨부파일 리스트
		model.addAttribute("list", service.getFiles(no));
		// /WEB-INF/views/board/view.jsp
		return "agentboard/view";
	}
	
	// 글쓰기 폼 -> servlet-context.xml에 view-controller tag로 지정
	@RequestMapping(value = "/agentboard/write.do", method = RequestMethod.GET)
	public String write() {
		System.out.println(getClass().getSimpleName()+".write():GET");
		// prefix + return String + suffix
		// /WEB-INF/views/agentboard/list.jsp
		return "agentboard/write";
	}
	
	// 글쓰기 처리 - 사용자가 제목, 내용, 작성자 데이터 입력 후 넘긴다. -> BoardDTO
	// 리스트로 갈때에 글등록이 완료 되었다는 경고창은 띄우자(단, 한번만)
	// -> RedirectAttributes.flash속성을 이용한다.
	@RequestMapping(value = "/agentboard/write.do", method = RequestMethod.POST)
	public String write(AgentDTO boardDTO, RedirectAttributes rttr, HttpSession session) 
			throws IOException, Exception {
		System.out.println(getClass().getSimpleName()+".write():POST");
		// 파일 업로드 처리를 한다.
		String originalName = boardDTO.getFile1().getOriginalFilename();
		String path = session.getServletContext().getRealPath("/resources/imgfile");
		System.out.println(path);
		
		// 엔터입력한 부분을 html tag인 <br>로 입력 , DB에 <br>태그와 같이 저장됨
		String content = ((String)boardDTO.getContent()).replace("\r\n", "<br>");
		boardDTO.setContent(content);
		
		if(originalName.equals("")) System.out.println("빈문자");
		String savedFile = "";
		// 첨부 파일이 없으면 저장하지 않고 있으면 저장한다.
		if(!originalName.equals("")) {
			// 날짜폴더가 포함된 파일명                                      
			savedFile 
			= UploadFileUtils.uploadFile(uploadPath, originalName,
					boardDTO.getFile1().getBytes());
			boardDTO.setFileName(savedFile);
			}				
		// 데이터를 db에 저장`
		service.insert(boardDTO);
		
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "writeOK");
		// prefix + return String + suffix
		// /WEB-INF/views/agentboard/list.jsp
		return "redirect:list.do"; // 처리가 다 끝나면 리스트로 자동 이동시킨다.
	}
	
	// 글수정 폼 - DB에서 글번호에 맞는 데이터를 불러와서 사용자에게 보여준다. -> JSP
	@RequestMapping(value = "/agentboard/update.do", method = RequestMethod.GET)
	public String update(Model model, int no) {
		System.out.println(getClass().getSimpleName()+".update():GET");
		model.addAttribute("dto", service.view(no, false));
		// prefix + return String + suffix
		// /WEB-INF/views/agentboard/update.jsp
		return "agentboard/update";
	}
	
	// 글수정 처리 - 수정한 제목, 내용, 작성자를 글번호와 함께 DAO에 보내서 DB에 저장한다.
	@RequestMapping(value = "/agentboard/update.do", method = RequestMethod.POST)
	public String update(AgentDTO boardDTO, RedirectAttributes rttr, Criteria cri, Model model) {
		System.out.println(getClass().getSimpleName()+".update():POST");
		
		// 엔터입력한 부분을 html tag인 <br>로 입력 , DB에 <br>태그와 같이 저장됨
		String content = ((String)boardDTO.getContent()).replace("\r\n", "<br>");
		boardDTO.setContent(content);
		
		service.update(boardDTO);
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "updateOK");
		model.addAttribute("cri", cri);
		// prefix + return String + suffix
		// /WEB-INF/views/agentboard/list.jsp
		return "redirect:view.do?no="+boardDTO.getNo()+"&page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum();
	}
	
	// 글삭제 처리 - 삭제할 글번호를 받아서 DB에서 글번호에 맞는 데이터를 삭제한다.
	@RequestMapping(value = "/agentboard/delete.do", method = RequestMethod.GET)
	public String delete(int no, RedirectAttributes rttr) {
		System.out.println(getClass().getSimpleName()+".delete()");
		service.delete(no);
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "deleteOK");
		// prefix + return String + suffix
		// /WEB-INF/views/agentboard/list.jsp
		return "redirect:list.do";
	}
}
