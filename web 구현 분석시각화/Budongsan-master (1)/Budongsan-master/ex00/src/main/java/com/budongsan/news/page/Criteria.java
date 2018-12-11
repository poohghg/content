package com.budongsan.news.page;
/*
 *  - 현재 페이지의 데이터를 가져 오기 위한 정보
 *  페이지, 한페이지의 표시할 데이터의 갯수, 시작 줄 번호, 끝 줄 번호
 *  
 *  - 화면에 보여줄 페이지 번호
 *  총 데이터의 갯수, 총 페이지의 갯수
 *  한페이지에 표시한 페이지 번호의 갯수, 시작페이지, 끝페이지
 *  이전 페이지 유무, 다음 페이지의 유무
 */
public class Criteria {
	//DB에서 페이지 처리된 데이터를 가져오는데 필요한 정보
	private int page, perPageNum,startRow, endRow;
	
	// [<][1][2]..[>]
	private int totalCount, totalPage, displayPageNum,startPage,endPage;
	
	private boolean prev, next;
	
	//검색 멤버 변수
	private String searchType;
	private String keyword;
	
	public Criteria() {
		page=1;
		perPageNum=10;
		startRow=1;
		endRow=10;
		
		displayPageNum=10;
		
		prev = false;
		next = false;
	}
	//페이저 정보 계산
	public void calcData() {
		startRow=(page-1)* perPageNum +1;
		endRow=startRow+perPageNum -1;
		
		//totalPage
		totalPage=(totalCount-1)/perPageNum+1;
		//start,endPage
		startPage=(page-1)/displayPageNum * displayPageNum +1;
		endPage= startPage + displayPageNum -1;		
		
	//endPage는 totalPage를 넘을수 없다넘게되면 endPage에다 totalPage를 넣는다.
			if(endPage>totalPage) endPage=totalPage;
			//prev,next 를 구한다  [<],[>]
			if(startPage != 1) prev = true;
			if(endPage != totalPage) next = true;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", displayPageNum=" + displayPageNum
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
}
