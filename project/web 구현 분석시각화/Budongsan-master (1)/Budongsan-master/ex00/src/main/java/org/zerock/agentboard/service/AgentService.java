package org.zerock.agentboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.agentboard.dao.AgentDAO;
import org.zerock.agentboard.dto.AgentDTO;
import org.zerock.agentboard.dto.Criteria;
import org.zerock.agentboard.dto.FileDTO;
import org.zerock.agentboard.dto.AttachFile;

@Service
public class AgentService {

	@Inject
	private AgentDAO dao;
	
	// 게시판 리스트
	public List<AgentDTO> list(Criteria cri) {
		System.out.println(getClass().getSimpleName()+".list()");
		// 페이지에 대한 계산을 한다.
		cri.setTotalCount(dao.getTotalCount(cri));
		cri.calcData();
		System.out.println(cri);
		return dao.list(cri);
	}
	
	public List<FileDTO> fileList(){
		return dao.fileList();
	}
	
	// 게시판 글보기+1증가, 글수정 - 글번호를 받아서 dao 한테 전달
	public AgentDTO view(Integer no, boolean isView) {
		System.out.println(getClass().getSimpleName()+".view()");
		// 글보기 할 때 increase 시킨다. 조회수 1증가.
		if(isView) dao.increase(no);
		return dao.view(no);
	}
	
	// 자료실 글보기에서 글번호에 따라 파일 가져오기
		public List<AttachFile> getFiles(int no){
			return dao.getFiles(no);
		}
	
	// 게시판 글쓰기 - BoardDTO를 받아서 dao 한테 전달
	public void insert(AgentDTO boardDTO) {
		System.out.println(getClass().getSimpleName()+".insert()");
		dao.insert(boardDTO);
		// data_file DB에 데이터 저장
			if(boardDTO.getFileName()!=null && !boardDTO.getFileName().equals(""))
				dao.insertFile(boardDTO);
	}
	
	// 게시판 글수정 - BoardDTO를 받아서 dao 한테 전달
	public void update(AgentDTO boardDTO) {
		System.out.println(getClass().getSimpleName()+".update()");
		dao.update(boardDTO);
	}
	
	// 게시판 글삭제 - 글번호(no)를 받아서 dao 한테 전달
	@Transactional
	public void delete(Integer no) {
		System.out.println(getClass().getSimpleName()+".delete()");
		dao.deleteAttach(no);
		dao.delete(no);
	}
	
	// 첨부파일을 조회하는 기능
	public List<String> getAttach(Integer no) {
		System.out.println(getClass().getSimpleName()+".getAttach()");
		return dao.getAttach(no);
	}
	
	//메인 페이지에 올라갈 게시물 3개
		public List<AgentDTO> mainCarousel(){
			return dao.mainCarousel();
		}
}
