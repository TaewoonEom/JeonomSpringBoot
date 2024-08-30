package com.jeonom.boot.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jeonom.boot.notice.model.vo.Notice;

@Mapper
// DAOImpl에 오버라이딩 할 필요 없이 mapper.xml에 있는 id값을 찾아
// 알아서 쿼리문을 실행해준다.
public interface NoticeDAO {

	/**
	 * 공지사항 상세조회 DAO
	 * @param noticeNo
	 * @return Notice
	 */
	Notice selectOne(Integer noticeNo);

	/**
	 * 공지사항 목록조회 DAO
	 * @return List<Notice>
	 */
	List<Notice> selectList(Integer currentPage);

	/**
	 * 공지사항 총 개수 DAO
	 * @return int
	 */
	int getTotalCount();

	/**
	 * 공지사항 등록 DAO
	 * @param inputNotice
	 * @return int
	 */
	int insertNotice(Notice inputNotice);

	/**
	 * 공지사항 수정 DAO
	 * @param notice
	 * @return int
	 */
	int updateNotice(Notice notice);

	/**
	 * 공지사항 삭제 DAO
	 * @param noticeNo
	 * @return int
	 */
	int deleteNotice(Integer noticeNo);

}
