package com.LoveToCode.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer instructorDetailId;

	@Column(name = "youtube_channel")
	private String youtubeChannel;

	@Column(name = "hobby")
	private String hobby;
	
	
	public Integer getInstructorDetailId() {
		return instructorDetailId;
	}

	public void setInstructorDetailId(Integer instructorDetailId) {
		this.instructorDetailId = instructorDetailId;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public InstructorDetail(String youtubeChannel, String hobby) {

		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "InstructorDetail [instructorDetailId=" + instructorDetailId + ", youtubeChannel=" + youtubeChannel
				+ ", hobby=" + hobby + "]";
	}

	public InstructorDetail() {

	}
}
