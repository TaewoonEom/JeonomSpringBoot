package com.jeonom.boot.notice.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeonom.boot.notice.model.dao.NoticeDAO;
import com.jeonom.boot.notice.model.vo.Notice;

//@Repository
public class NoticeDAOImpl { //implements NoticeDAO {

//	private SqlSession session;
//	
//	public NoticeDAOImpl() {}
//	
//	@Autowired
//	public NoticeDAOImpl(SqlSession session) {
//		this.session = session;
//	}
//	
//	@Override
//	public Notice selectOne(Integer noticeNo) {
//		Notice notice = session.selectOne("NoticeMapper.selectOne", noticeNo);
//		return notice;
//	}
//
//	@Override
//	public List<Notice> selectList(Integer currentPage) {
//		int limit = 10;
//		int offset = (currentPage - 1) * limit;
//		RowBounds rowBounds = new RowBounds(offset, limit);
//		List<Notice> nList = session.selectList("NoticeMapper.selectList", null, rowBounds);
//		return nList;
//	}
//
//	@Override
//	public int getTotalCount() {
//		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
//		return totalCount;
//	}
//
//	@Override
//	public int insertNotice(Notice inputNotice) {
//		int result = session.insert("NoticeMapper.insertNotice", inputNotice);
//		return result;
//	}
//
//	@Override
//	public int updateNotice(Notice notice) {
//		int result = session.update("NoticeMapper.updateNotice", notice);
//		return result;
//	}
//
//	@Override
//	public int deleteNotice(Integer noticeNo) {
//		int result = session.delete("NoticeMapper.deleteNotice", noticeNo);
//		return result;
//	}

}
