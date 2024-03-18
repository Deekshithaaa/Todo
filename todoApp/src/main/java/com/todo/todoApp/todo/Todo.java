package com.todo.todoApp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


@Entity
public class Todo {
	
	public Todo(String attachmentType) {
		super();
		this.attachmentType = attachmentType;
	}



	public Todo() {
				
			}
	
	

	public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done, String priority,
			String comments, byte[] attachmentData, String tags) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
		this.priority = priority;
		this.comments = comments;
		this.attachmentData = attachmentData;
		this.tags = tags;
	}

	
	
	@Id
    @GeneratedValue
	private Integer id;
	private String username;
	private String description;
	private LocalDate targetDate;
	private boolean done;
	private String priority;
	private String comments;
	@Lob
    private byte[] attachmentData;
	private String tags;
	private String attachmentType;
	
	public String getAttachmentType() {
		return attachmentType;
	}


	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public byte[] getAttachmentData() {
		return attachmentData;
	}
	
	public void setAttachmentData(byte[] attachmentData) {
        this.attachmentData = attachmentData;
    }

	public void setAttachmentType(String attachmentType) {
        if (!attachmentType.equals("image/png") && !attachmentType.equals("image/jpeg")) {
            throw new IllegalArgumentException("Only PNG or JPG files are allowed");
        }
        this.attachmentType = attachmentType;
    }

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}

}