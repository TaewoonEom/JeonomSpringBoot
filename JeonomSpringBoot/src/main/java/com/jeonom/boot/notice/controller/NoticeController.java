package com.jeonom.boot.notice.controller;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.jeonom.boot.common.utility.Util;
import com.jeonom.boot.notice.model.service.NoticeService;
import com.jeonom.boot.notice.model.vo.Notice;
import com.jeonom.boot.notice.model.vo.NoticeFile;
import com.jeonom.boot.notice.model.vo.Pagination;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService nService;
	
	public NoticeController() {}
	
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService = nService;
	}
	
	// 공지사항 등록 페이지 바로가기
	@GetMapping("/write")
	public String showWriteForm() {
		return "notice/noticeWrite";
	}
	
	// 공지사항 등록(첨부파일 포함)
	@PostMapping("/write")
	public String noticeWrite(Notice inputNotice
			, @RequestParam("uploadFile") MultipartFile uploadFile) throws IllegalStateException, IOException {
		int result = nService.insertNotice(inputNotice, uploadFile);
		return "redirect:/notice/list";
	}
	
	// 공지사항 수정 페이지 바로가기
	@GetMapping("/modify/{noticeNo}")
	public String showModifyForm(@PathVariable Integer noticeNo
			, Model model) {
		Notice notice = nService.selectOne(noticeNo);
		model.addAttribute("notice", notice);
		return "notice/noticeModify";
	}
	
	// 공지사항 수정
	@PostMapping("/modify")
	public String noticeModify(Notice notice
			, @RequestParam("reloadFile") MultipartFile reloadFile) throws IllegalStateException, IOException{
		int result = nService.updateNotice(notice, reloadFile);
		return "redirect:/notice/detail/" + notice.getNoticeNo();
	}
	
	// 공지사항 삭제
	@GetMapping("/delete/{noticeNo}")
	public String noticeDelete(@PathVariable Integer noticeNo) {
		int result = nService.deleteNotice(noticeNo);
		return "redirect:/notice/list";
	}
	
	// 공지사항 목록 페이지 바로가기
	@GetMapping("/list")
	public String showNoticeList(@RequestParam(value="cp", required=false, defaultValue="1") Integer currentPage
			, Model model) {
		int totalCount = nService.getTotalCount();
		Pagination pn = new Pagination(totalCount, currentPage);
		int limit = pn.getBoardLimit();
		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit); 
		List<Notice> nList = nService.selectList(currentPage, rowBounds);
		model.addAttribute("nList", nList);
		model.addAttribute("pn", pn);
//		model.addAttribute("startNavi", pn.getStartNavi());
//		model.addAttribute("endNavi", pn.getEndNavi());
		return "notice/noticeList";
	}
	
	// 공지사항 상세조회 바로가기
	@GetMapping("/detail/{noticeNo}")
	public String showNoticeDetail(@PathVariable Integer noticeNo
			, Model model) {
		// Notice 클래스에 noticeFile 필드를 적어줌
		Notice notice = nService.selectOne(noticeNo);
		model.addAttribute("notice", notice);
//		model.addAttribute("noticeFile", noticeFile);
		return "notice/noticeDetail";
	}
	
}
