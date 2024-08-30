package com.jeonom.boot.notice.model.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import com.jeonom.boot.notice.model.vo.Notice;
import com.jeonom.boot.notice.model.vo.NoticeFile;

public interface NoticeService {

	/**
	 * 공지사항 상세조회 Service
	 * @param noticeNo
	 * @return Notice
	 */
	Notice selectOne(Integer noticeNo);

	/**
	 * 공지사항 목록조회 Service
	 * @return List<Notice>
	 */
	List<Notice> selectList(Integer currentPage, RowBounds rowBounds);

	/**
	 * 공지사항 총 개수 Serivce
	 * @return int
	 */
	int getTotalCount();

	/**
	 * 공지사항 등록 Service
	 * @param inputNotice
	 * @return int
	 */
	int insertNotice(Notice inputNotice, MultipartFile uploadFile) throws IllegalStateException, IOException;

	/**
	 * 공지사항 파일 등록 Service
	 * @param noticeFile
	 * @return int
	 */
//	int insertNoticeFile(NoticeFile noticeFile) throws IllegalStateException, IOException;

	/**
	 * 공지사항 수정 Service
	 * @param notice
	 * @return int
	 */
	int updateNotice(Notice notice, MultipartFile reloadFile) throws IllegalStateException, IOException;

	/**
	 * 공지사항 삭제 Service
	 * @param noticeNo
	 * @return int
	 */
	int deleteNotice(Integer noticeNo);

}
