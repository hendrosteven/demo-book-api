package com.brainmatics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Code is required")
	@Size(min=3, max=5, message = "Code length must be 3 to 5 characters")
	@Pattern(regexp = "BT[0-9]+", message = "Code must be start with BT")
	@Column(length = 5, nullable = false, unique = true)
	private String code;
	
	@NotEmpty(message = "Title is required")
	@Column(length = 200, nullable = false)
	private String title;
	
	@NotEmpty(message = "Description is required")
	@Column(length = 1000, nullable = false)
	private String descriptions;
	
	@NotEmpty(message = "Author is required")
	@Column(length= 150, nullable = false)
	private String author;
	
	@NotEmpty(message = "Image is required")
	@Column(length= 255, nullable = false)
	private String image;
	
	@ManyToOne
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
	
}
