package org.zerock.furniture.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.furniture.dto.FurnitureDTO;
import org.zerock.furniture.dto.Furniture_CommentDTO;
import org.zerock.furniture.dao.FurnitureDAO;
import org.zerock.furniture.dto.Criteria;

@Service
public class FurnitureService
{

	@Inject
	private FurnitureDAO dao;

	// 게시판 리스트
	public List<FurnitureDTO> list(Criteria cri ,String Path)
	{
		System.out.println(getClass().getSimpleName() + ".list()");
		// 페이지에 대한 계산을 한다.
		cri.setTotalCount(dao.getTotalCount(cri));
		cri.calcData();
		System.out.println(cri);
		return dao.list(cri,Path);
	}

	// 게시판 글보기+1증가, 글수정 - 글번호를 받아서 dao 한테 전달
	public FurnitureDTO view(Integer id, boolean isView)
	{
		System.out.println(getClass().getSimpleName() + ".view()");
		// 글보기 할 때 increase 시킨다. 조회수 1증가.
		if(isView)
			dao.increase(id);
		return dao.view(id);
	}

	// 게시판 글쓰기 - BoardDTO를 받아서 dao 한테 전달
	public void insert(FurnitureDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".insert()");
		dao.insert(boardDTO);
	}

	// 게시판 글수정 - BoardDTO를 받아서 dao 한테 전달
	public void update(FurnitureDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".update()");
		dao.update(boardDTO);
	}

	// 게시판 글삭제 - 글번호(id)를 받아서 dao 한테 전달
	public void delete(Integer id)
	{
		System.out.println(getClass().getSimpleName() + ".delete()");
		dao.delete(id);
	}
	
	public Integer getTitleID (FurnitureDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".getTitleID()");
		return dao.getTitleId(boardDTO);
	}
	
	public String getIdTitle (FurnitureDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".getTitleID()");
		return dao.getIdTitle(boardDTO);	
	}
	
	public void insertComment (Furniture_CommentDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".insertComment()");
		dao.insertComment(boardDTO);
	}
	
	public List<Furniture_CommentDTO> commentlist (int id)
	{
		System.out.println(getClass().getSimpleName() + ".commentlist()");
		return dao.commentlist(id);
	}
	
	public void BuyProcess (Furniture_CommentDTO boardDTO)
	{
		System.out.println(getClass().getSimpleName() + ".BuyProcess()");
		dao.BuyProcess(boardDTO);
	}

}
