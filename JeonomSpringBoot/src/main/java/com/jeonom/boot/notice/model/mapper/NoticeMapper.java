package com.jeonom.boot.notice.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.jeonom.boot.notice.model.vo.Notice;
import com.jeonom.boot.notice.model.vo.NoticeFile;

/*
 * @Mapper 어노테이션
 * Mapper 인터페이스 메소드명과
 * mapper.xml에 작성한 태그 아이디가 같은 것끼리
 * 연결해주는 어노테이션
 * 
 * 1. SqlSessionTemplate bean에 의존성 주입받는 코드 작성을 안함.
 * 작성을 안해도 Spring Container에서 알아서 생성 후 사용
 * 
 * 2. 네임스페이스.아이디값을 적지 않아도 됨.
 * 
 * 3. session에서 사용했었던 메소드(insert, update, ..) 작성하지 않아도 됨.
 * 
 * ★★★ 꼭 기억해야 하는 것 ★★★
 * 중요) mapper.xml에 Mapper 인터페이스의 경로를 namespace에 적어주어야 함.
 * 
 */

@Mapper
// DAOImpl에 오버라이딩 할 필요 없이 mapper.xml에 있는
// id값을 찾아 알아서 쿼리문을 실행한다.
// mapper.xml의 namespace에는 인터페이스의 절대경로를 적어줌
public interface NoticeMapper {

	/**
	 * 공지사항 상세조회 Mapper
	 * @param noticeNo
	 * @return Notice
	 */
	Notice selectOne(Integer noticeNo);

	/**
	 * 공지사항 목록조회 Mapper
	 * @return List<Notice>
	 */
	List<Notice> selectList(Integer currentPage, RowBounds rowBounds);

	/**
	 * 공지사항 총 개수 Mapper
	 * @return int
	 */
	int getTotalCount();

	/**
	 * 공지사항 등록 Mapper
	 * @param inputNotice
	 * @return int
	 */
	int insertNotice(Notice inputNotice);

	/**
	 * 공지사항 파일 등록 Mapper
	 * @param noticeFile
	 * @return int
	 */
	int insertNoticeFile(NoticeFile noticeFile);

	/**
	 * 공지사항 수정 Mapper
	 * @param notice
	 * @return int
	 */
	int updateNotice(Notice notice);

	/**
	 * 공지사항 파일 수정 Mapper
	 * @param noticeFile
	 * @return int
	 */
	int updateNoticeFile(NoticeFile noticeFile);
	
	/**
	 * 공지사항 삭제 Mapper
	 * @param noticeNo
	 * @return int
	 */
	int deleteNotice(Integer noticeNo);
	
	/**
	 * 공지사항 파일 삭제 Mapper
	 * @param noticeNo
	 * @return
	 */
	int deleteNoticeFile(Integer noticeNo);

	/**
	 * 공지사항 파일 조회 Mapper
	 * @param noticeNo
	 * @return NoticeFile
	 */
	NoticeFile selectNoticeFile(Integer noticeNo);

}
