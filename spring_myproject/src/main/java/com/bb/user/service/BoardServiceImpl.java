package com.bb.user.service;



import java.util.*;

import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;


import com.bb.user.dao.BoardDao;
import com.bb.user.dto.*;




@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private FileService fs;
	
	
	
	@Override
	public String insertBoard(Review r, MultipartFile fileAttach, HttpSession session) {
	
		r = reviewInstanse(r, fileAttach, session);
		return boardDao.insertBoard(r);
	}

	
	
	@Override
	public String updateBoard(Review r, MultipartFile fileAttach, HttpSession session) {
		
		r = reviewInstanse(r, fileAttach, session);		
		return boardDao.updateBoard(r);
	}

	
	
	
	@Override
	public HashMap<String, Object> getBoardList(String no, String pageNum) {
		
		return boardDao.getReviewList(no, pageNum);
	}

	@Override
	public Review getReviewContent(String no, String email) {
		return boardDao.getReviewContent(no, email);

	}
	
	@Override
	public int delReview(String reviewNo, HttpSession session) {
		
		return boardDao.delReview(reviewNo, session);
	}
	

	@Override
	public ArrayList<Review> getBookmarkList(String email) {
		return boardDao.getBookMarkList(email);
	}
	

	@Override
	public int bookmarkProc(String review_no, String review_url, String email) {
		
		return boardDao.bookmarkProc(review_no, review_url, email);
	}
	
	@Override
	public HashMap<String, Object> search(String select, String condition, String pageNum, String hosNo) {
		return boardDao.searchReview(select, condition, pageNum, hosNo);
		
	}

	
	@Override
	public ArrayList<Notice> getNoticeList() {
		
		return boardDao.getNoticeList();
	}

	
	@Override
	public Notice getNoticeContent(String noticeNo) {
		
		return boardDao.getNoticeContent(noticeNo);
	}


	
	@Override
	public ArrayList<Notice> getNoticeListLimit() {
		
		return boardDao.getNoticeListLimit();
	}
	
	
	
	@Override
	public ArrayList<SnsReview> getSNSList(String hospitalNo) {
		return boardDao.getSnsReviewList(hospitalNo);
	}

	
	@Override
	public int insertSnsReview(SnsReview sr, HttpSession session, MultipartFile attachFile, String hospitalNo) {
		
		sr = snsReviewInstance(sr, attachFile, session);
		return boardDao.insertSnsBoard(sr, hospitalNo);
	}
	
	
	@Override
	public int delSnsReview(String snsNo, HttpSession session) {
		
		return boardDao.delSnsReview(snsNo, session);
	}
	
	
	@Override
	public String updateSnsReview(SnsReview snsReview, MultipartFile attachFileMod, HttpSession session) {
		
		snsReview = snsReviewInstance(snsReview, attachFileMod, session);
		
		return boardDao.updateSnsReview(snsReview, session);
	}


	@Override
	public HashMap<String, Object> getHospitalList(String location) {
		
		return boardDao.getHospitalList(location);
	}
	

	@Override
	public int insertReply(Reply r) {
		
		return boardDao.insertReply(r);
	}

	

	@Override
	public int deleteReply(int replyNo) {
		
		return boardDao.deleteReply(replyNo);
	}

	
	@Override
	public int updateReply(int replyNo, String content) {
		return boardDao.updateReply(replyNo, content);
	}


	@Override
	public int recommendReview(String sessionId, String reviewNo) {

		return boardDao.recommendReview(sessionId, reviewNo);
	}


	@Override
	public int reviewReport(String sessionId, String reviewNo) {
		
		return boardDao.reviewReport(sessionId, reviewNo);
	}

	@Override
	public HashMap<String, Object> searchHospital(String[] hospitalSearchCondition, String location) {
		return boardDao.searchHospital(hospitalSearchCondition, location);
	}









	
	
	
	
	//insert 혹은 update할 fileAttached 객체 생성
	private Review reviewInstanse(Review r, MultipartFile fileAttach, HttpSession session) {
		
		FileAttached f = new FileAttached();
		r.setFileAttached(f);
		
		if(fileAttach != null) {
			if(fileAttach.getSize()>0) {
				f = fs.getInstanceOf(f, fileAttach, session);
			}			
		}
		
		return r;
	}
	
	
	private SnsReview snsReviewInstance(SnsReview sr, MultipartFile fileAttach, HttpSession session) {
		
		FileAttached f = new FileAttached();
		sr.setFileAttached(f);
		
		if(fileAttach != null) {
			if(fileAttach.getSize()>0) {
				f = fs.getInstanceOf(f, fileAttach, session);
			}			
		}
		
		return sr;
	}



	




	
}
