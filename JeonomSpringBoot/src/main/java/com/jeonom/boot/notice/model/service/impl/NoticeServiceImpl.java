package com.jeonom.boot.notice.model.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeonom.boot.common.utility.Util;
import com.jeonom.boot.notice.model.dao.NoticeDAO;
import com.jeonom.boot.notice.model.mapper.NoticeMapper;
import com.jeonom.boot.notice.model.service.NoticeService;
import com.jeonom.boot.notice.model.vo.Notice;
import com.jeonom.boot.notice.model.vo.NoticeFile;

@Service
public class NoticeServiceImpl implements NoticeService {

//	private NoticeDAO mapper;
	private NoticeMapper mapper;
	
	public NoticeServiceImpl() {}
	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}
	
//	@Autowired
//	public NoticeServiceImpl(NoticeDAO mapper) {
//		this.mapper = mapper;
//	}
	
	// 공지사항 상세조회 Service
	@Override
	public Notice selectOne(Integer noticeNo) {
		Notice notice = mapper.selectOne(noticeNo);
		return notice;
	}
	
	// 공지사항 목록조회 Service
	@Override
	public List<Notice> selectList(Integer currentPage, RowBounds rowBounds) {
		List<Notice> nList = mapper.selectList(currentPage, rowBounds);
		return nList;
	}

	// 공지사항 총 개수 Serivce
	@Override
	public int getTotalCount() {
		int totalCount = mapper.getTotalCount();
		return totalCount;
	}

	// 공지사항&파일 등록 Service
	@Override
	public int insertNotice(Notice inputNotice, MultipartFile uploadFile) throws IllegalStateException, IOException {
		// 공지사항 정보 등록
		int result = mapper.insertNotice(inputNotice);
		
		// 파일 정보 등록
		if(uploadFile != null) {
			String fileName = uploadFile.getOriginalFilename();
			String fileRename = Util.fileRename(fileName);
			// web용 경로
			String filePath = "/images/notice/";
	//		long   fileSize;
			// 절대경로로 실제 파일 저장, 저장할 때는 Rename파일명으로 저장
			uploadFile.transferTo(new File("C:/uploadFile/notice/" + fileRename));
			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setFileName(fileName);
			noticeFile.setFileRename(fileRename);
			noticeFile.setFilePath(filePath);
			noticeFile.setNoticeNo(inputNotice.getNoticeNo());
			result = mapper.insertNoticeFile(noticeFile);
		}
		return result;
	}

//	@Override
//	public int insertNoticeFile(NoticeFile noticeFile) throws IllegalStateException, IOException {
//		// 절대경로로 실제 파일 저장
//		MultipartFile uploadFile = noticeFile.getUploadFile();
//		uploadFile.transferTo(new File("C:/uploadFile/notice/"+noticeFile.getFileRename()));
//		int result = mapper.insertNoticeFile(noticeFile);
//		return result;
//	}
	
	// 공지사항&파일 수정 Service
	@Override
	public int updateNotice(Notice notice, MultipartFile reloadFile) throws IllegalStateException, IOException {
		// 공지사항 정보 수정
		int result = mapper.updateNotice(notice);
		// 파일 정보 수정
//		if(reloadFile != null && !"".equals(reloadFile.getOriginalFilename())) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			String fileName = reloadFile.getOriginalFilename();
			String fileRename = Util.fileRename(fileName);
			String filePath = "/images/notice/";
			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setFileName(fileName);
			noticeFile.setFileRename(fileRename);
			noticeFile.setFilePath(filePath);
			// noticeModify.html에서 input[type="hidden"]으로 noticeNo값이 있어야 함.
			noticeFile.setNoticeNo(notice.getNoticeNo());
			reloadFile.transferTo(new File("C:/uploadFile/notice/" + fileRename));
			
			NoticeFile nFileOne = mapper.selectNoticeFile(notice.getNoticeNo());
			if(nFileOne != null) {
				// new File("").delete();
				File nFile = new File("C:/uploadFile/notice" + nFileOne.getFileRename());
				nFile.delete();
				result = mapper.updateNoticeFile(noticeFile);
			}else {
				result = mapper.insertNoticeFile(noticeFile);
			}
		}
		return result;
	}

	// 공지사항&파일 삭제 Service
	@Override
	public int deleteNotice(Integer noticeNo) {
		int result = mapper.deleteNotice(noticeNo);
		NoticeFile noticeFile = mapper.selectNoticeFile(noticeNo);
		if(noticeFile != null) {
			File nFile = new File("C:/uploadFile/notice/" + noticeFile.getFileRename());
			nFile.delete();
			result = mapper.deleteNoticeFile(noticeNo);
		}
		return result;
	}

}
