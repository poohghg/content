package org.zerock.furniture.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.furniture.dto.FurnitureDTO;
import org.zerock.furniture.dto.Furniture_CommentDTO;
import org.zerock.furniture.dto.Criteria;
import org.zerock.furniture.service.FurnitureService;
import org.zerock.furniture.util.MediaUtils;
import org.zerock.furniture.util.UploadFileUtil;
import org.zerock.member.dto.LoginDTO;

/*
 * 자동생성이 되게 하려면 
 * 1. web.xml에 설정되어 있는 xml 안에 beans > bean 태그를 이용해서 클래스 등록
 * 2. web.xml에 설정되어 있는 xml 안에 context:component-scan 을 이용해서
 *    base-package 속성의 패키지 안에서 클래스를 @Controller, @Service, @Repository,
 *    @Component, @RestController로 지정하면 자동 생성된다.
 */
@Controller
public class FurnitureController
{


	// 사용할 서비스 변수 선언. - DI 적용 : @Inject, @Autowired
	@Inject
	private FurnitureService service;

	// 리스트 - DB에서 데이터를 꺼내와서 JSP로 Model객체에 데이터를 담아서 넘긴다.forward
	@RequestMapping(value = "/furniture/list.do", method = RequestMethod.GET)
	public String list(Model model, Criteria cri, HttpSession session)
	{
		System.out.println(getClass().getSimpleName() + ".list()");
		
		 String root_path = session.getServletContext().getRealPath("/resources/saveImage"); 
	      System.out.println("root_path : " + root_path);
	         
		// System.out.println(cri);
		model.addAttribute("list", service.list(cri,root_path));
		// jsp에서 하단부분 페이지 표시할때 cri 객체가 필요하다. model에 담아서 보낸다.
		
		model.addAttribute("cri", cri);
		
		// prefix + return String + suffix
		// /WEB-INF/views/board/list.jsp
		return "furniture/list";
	}

	// 글보기 - 글번호를 전달 받아서 DB에 글번호에 맞는 BoardDTO를 가져온다. JSP로 전달
	// view.do?no=10
	@RequestMapping(value = "/furniture/view.do", method = RequestMethod.GET)
	public String view(Model model, int id)
	{
		System.out.println(getClass().getSimpleName() + ".view()");
		FurnitureDTO furnitureDTO= service.view(id, true);
		
		furnitureDTO.setContent(MediaUtils.CreateBR(furnitureDTO.getContent()));
		
		
		//model.addAttribute("dto", service.view(id, true));
		model.addAttribute("dto", furnitureDTO);
	
		model.addAttribute("commentlist",service.commentlist(id));
		return "furniture/view";
	}

	@RequestMapping(value = "/furniture/comment.do", method = RequestMethod.GET)
	public String comment ()
	{
		System.out.println(getClass().getSimpleName() + ".comment()");	
		return "furniture/list";
	}
	
	// 글쓰기 폼 -> servlet-context.xml에 view-controller tag로 지정
	@RequestMapping(value = "/furniture/write.do", method = RequestMethod.GET)
	public String write()
	{
		System.out.println(getClass().getSimpleName() + ".write():GET");
		return "furniture/write";
	}

	// 글쓰기 처리 - 사용자가 제목, 내용, 작성자 데이터 입력 후 넘긴다. -> BoardDTO
	// 리스트로 갈때에 글등록이 완료 되었다는 경고창은 띄우자(단, 한번만)
	// -> RedirectAttributes.flash속성을 이용한다.
	@RequestMapping(value = "/furniture/write.do", method = RequestMethod.POST)
	public String write(FurnitureDTO boardDTO, RedirectAttributes rttr,
			MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session, MultipartFile file ) throws IOException, Exception
	{
		
		System.out.println(getClass().getSimpleName() + ".write():POST");
		String Path = session.getServletContext().getRealPath("/resources/saveImage"); 
		List<MultipartFile> files = null;

		if (multipartHttpServletRequest.getFiles("files").isEmpty() != true  
				&& file.isEmpty() != true)
		{
			files = multipartHttpServletRequest.getFiles("files");
			boardDTO.setPicture(file.getBytes());
		}
		
		boardDTO.setUUID( UUID.randomUUID().toString());
		boardDTO.setPicture(file.getBytes());
		LoginDTO dto = (LoginDTO) session.getAttribute("login");
		boardDTO.setCpn(dto.getEmail());
		
		service.insert(boardDTO); 
			 
		int id = service.getTitleID(boardDTO); 
		boardDTO.setId(id);
		
		if (multipartHttpServletRequest.getFiles("files").isEmpty() != true  
				&& file.isEmpty() != true)
		{
			// 리스트 이미지 저장
			UploadFileUtil.saveImg(boardDTO, Path);
			// 뷰 이미지 저장
			UploadFileUtil.saveViewImg(files, ""+id, Path);
		}
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "writeOK");
		// prefix + return String + suffix
		// /WEB-INF/views/board/list.jsp
		return "redirect:list.do"; // 처리가 다 끝나면 리스트로 자동 이동시킨다.
	}

	// 글수정 폼 - DB에서 글번호에 맞는 데이터를 불러와서 사용자에게 보여준다. -> JSP
	@RequestMapping(value = "/furniture/update.do", method = RequestMethod.GET)
	public String update(Model model, int id)
	{
		System.out.println(getClass().getSimpleName() + ".update():GET");
		model.addAttribute("dto", service.view(id, false));
		// prefix + return String + suffix
		// /WEB-INF/views/board/update.jsp
		return "furniture/update";
	}

	// 글수정 처리 - 수정한 제목, 내용, 작성자를 글번호와 함께 DAO에 보내서 DB에 저장한다.
	@RequestMapping(value = "/furniture/update.do", method = RequestMethod.POST)
	public String update(FurnitureDTO boardDTO, RedirectAttributes rttr,HttpSession session,
			MultipartHttpServletRequest multipartHttpServletRequest, MultipartFile file ) throws IOException
	{
		System.out.println(getClass().getSimpleName() + ".write():POST");
		
		String Path = session.getServletContext().getRealPath("/resources/saveImage"); 
		List<MultipartFile> files = null;

		if (multipartHttpServletRequest.getFiles("files").isEmpty() != true  
				&& file.isEmpty() != true)
		{
			files = multipartHttpServletRequest.getFiles("files");
			boardDTO.setPicture(file.getBytes());
		}
	
		LoginDTO dto = (LoginDTO) session.getAttribute("login");
		boardDTO.setCpn(dto.getEmail());
				 
		service.update(boardDTO);
		System.out.println(boardDTO);
		
		int id = service.getTitleID(boardDTO); 
		boardDTO.setId(id);	
		
		if (multipartHttpServletRequest.getFiles("files").isEmpty() != true  
				&& file.isEmpty() != true)
		{
			System.out.println("이미지 저장 실행");
			// 리스트 이미지 저장
			UploadFileUtil.saveImg(boardDTO, Path);
			// 뷰 이미지 저장
			UploadFileUtil.saveViewImg(files, ""+id, Path);
		}
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "updateOK");
		// prefix + return String + suffix
		// /WEB-INF/views/board/list.jsp
		return "redirect:view.do?id=" + boardDTO.getId();
	}

	// 글삭제 처리 - 삭제할 글번호를 받아서 DB에서 글번호에 맞는 데이터를 삭제한다.
	@RequestMapping(value = "/furniture/delete.do", method = RequestMethod.GET)
	public String delete(int id, RedirectAttributes rttr,HttpSession session)
	{
		System.out.println(getClass().getSimpleName() + ".delete()");
		String Path = session.getServletContext().getRealPath("/resources/saveImage"); 
		
		FurnitureDTO boardDTO = new FurnitureDTO();
		boardDTO.setId(id);
		//String name = service.getIdTitle(boardDTO);
		UploadFileUtil.ImgDelect(Path,""+id+"."+"jpg");
		UploadFileUtil.ImgViewDelect(Path,""+id);
		
		service.delete(id);
		// 딱 한번만 적용되고 다음에는 없어지는 속성 저장
		rttr.addFlashAttribute("msg", "deleteOK");
		// prefix + return String + suffix
		// /WEB-INF/views/board/list.jsp
		return "redirect:list.do";
	}
	
	
	@RequestMapping(value = "/furniture/view.do",  method = RequestMethod.POST)
	public String View (Furniture_CommentDTO tempDTO, HttpSession session )
	{
		LoginDTO dto = (LoginDTO) session.getAttribute("login");
		tempDTO.setUserid( dto.getEmail());
		tempDTO.setFurnitureid(tempDTO.getId());
		System.out.println(getClass().getSimpleName() + ".View():POST");
		System.out.println(tempDTO);
		service.insertComment(tempDTO);	
		return "redirect:list.do";
	}
	
	@RequestMapping(value = "/furniture/buy.do", method = RequestMethod.GET)
	public String buy (int id, String cpn, RedirectAttributes rttr, HttpSession session)
	{
		System.out.println(getClass().getSimpleName() + ".buy()");
		System.out.println(cpn);
		Furniture_CommentDTO boardDTO  = new Furniture_CommentDTO();
		boardDTO.setFurnitureid(id);
		boardDTO.setCpn(cpn);
		LoginDTO dto = (LoginDTO) session.getAttribute("login");
		boardDTO.setUserid(dto.getEmail());
		service.BuyProcess(boardDTO);
		
		rttr.addFlashAttribute("msg", "buyOK");
		return "redirect:list.do";
	}
	
}
