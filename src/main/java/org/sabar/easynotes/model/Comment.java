package org.sabar.easynotes.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;

@Entity
@Table(name = "m_comment")
public class Comment extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "m_comment_id")
	private Long id;
	
	@NonNull
	@Lob
	private String text;	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "c_note_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Note note;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Note getNote() {
		return note;
	}


	public void setNote(Note note) {
		this.note = note;
	}
}
