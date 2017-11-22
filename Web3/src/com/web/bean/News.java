package com.web.bean;

import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private String image;
	private String content;
	private NewsType newsType;
	private User user;
	private Date createTime;
	private Date publishTime;
	private Integer publishStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", image=" + image + ", content=" + content + ", newsType="
				+ newsType + ", user=" + user + ", createTime=" + createTime + ", publishTime=" + publishTime
				+ ", publishStatus=" + publishStatus + "]";
	}

}
