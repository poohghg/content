package org.zerock.agentboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.agentboard.dto.AgentDTO;
import org.zerock.agentboard.dto.AttachFile;
import org.zerock.agentboard.dto.Criteria;
import org.zerock.agentboard.dto.FileDTO;

@Repository
public class AgentDAO {

	@Inject
	private SqlSession sqlSession;
	
	// 게시판 리스트
	public List<AgentDTO> list(Criteria cri) {
		System.out.println(getClass().getSimpleName()+".list()");
		return sqlSession.selectList
				("org.zerock.mapper.agentMapper.list", cri);
	}
	
	public List<FileDTO> fileList(){
		return sqlSession.selectList("org.zerock.mapper.agentMapper.listfile");
				
	}
	
	// 게시판 글보기, 글 수정 폼 - 글번호가 필요하다. 파라메터로 전달 받는다.
	public AgentDTO view(Integer no) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".view()");
		return sqlSession.selectOne
		("org.zerock.mapper.agentMapper.view", no);
	}
	
	// 게시판 글쓰기 
	public void insert(AgentDTO boardDTO) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".insert()");
		sqlSession.insert
		("org.zerock.mapper.agentMapper.insert", boardDTO);
	}
	
	// 게시판 글수정 처리
	public void update(AgentDTO boardDTO) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".update()");
		sqlSession.update
		("org.zerock.mapper.agentMapper.update", boardDTO);
	}
	
	// 게시판 글보기 1 증가 처리
	public void increase(Integer no) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".increase()");
		sqlSession.update
		("org.zerock.mapper.agentMapper.increase", no);
	}
	
	// 게시판 글삭제 - 글번호를 받아서 처리
	public void delete(Integer no) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".delete()");
		sqlSession.delete
		("org.zerock.mapper.agentMapper.delete", no);
	}

	// 게시판 전체 글의 갯수 구하는 메서드
	public Integer getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+".getTotalCount()");
		return sqlSession.selectOne
		("org.zerock.mapper.agentMapper.totalCount", cri);
	}

	public void insertFile(AgentDTO boardDTO) {
		// TODO Auto-generated method stub
		sqlSession.insert("org.zerock.mapper.agentMapper.insertFile", boardDTO);
	}
	
	public List<AttachFile> getFiles(int no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("org.zerock.mapper.agentMapper.viewFiles", no);
	}
	
	// 특정 게시물 번호의 모든 첨부파일 삭제
	public void deleteAttach(int no) {
		System.out.println(getClass().getSimpleName()+".deleteAttach");
		sqlSession.delete("org.zerock.mapper.agentMapper.deleteAttach", no);
	}
	
	// 특정 게시물의 첨부파일을 시간 순서대로 가져오는 sql문 가져오기
	public List<String> getAttach(Integer no) {
		System.out.println(getClass().getSimpleName()+".getAttach()");
		return sqlSession.selectList("org.zerock.mapper.agentMapper.getAttach", no);
	}
	
	//메일 페이지에 올라갈 게시물 3개
		public List<AgentDTO> mainCarousel(){
			return sqlSession.selectList("org.zerock.mapper.agentMapper.mainPage");
		}
}
