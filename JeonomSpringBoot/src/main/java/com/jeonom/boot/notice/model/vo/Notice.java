package com.jeonom.boot.notice.model.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Data : getter, setter, toString, equals, hashcode 메소드를 자동 생성
@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@ToString
// @AllArgsConstructor : 모든 필드를 매개변수로 가진 생성자
public class Notice {
	private Integer noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone="Asia/Seoul")
	private Timestamp noticeDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone="Asia/Seoul")
	private Timestamp updateDate;
	private Integer viewCount;
	
	// 파일 정보
	private NoticeFile noticeFile;
	
}
