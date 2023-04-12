package com.edu.common.util;

public class Criteria { //Criteria(기준) : 검색의 기준을 마련한다.
	
	private int page; //현재 페이지 번호
	private int perPageNum;	//한 페이지당 보여줄 게시글의 갯순
	
	public Criteria() {
		this.page = 1; // (기본)페이지를 1로 시작
		this.perPageNum = 9; // (기본) 한 페이지당 보여줄 게시글 갯수 =9	
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) { // 현재 페이지가 0이하면 1로 설정해준다.
			this.page = 1;
		} else {
			this.page = page;
		}	
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	
	public void setPerPageNum(int perPageNum) { //이부분은 아직 보류 테스트해볼거 남음
		this.perPageNum = perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	

}
