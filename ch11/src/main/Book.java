package main;

import java.time.LocalDate;

public class Book {
	private String name; //책이름
	private String author; //저자
	private Integer number; //책번호
	private LocalDate pubDate; //출판일
	private String description; //책 소개
	
	public Book() {
		
	}
	
	public Book(String name, String author, Integer number, LocalDate pubDate, String description) {
		this.name = name;
		this.author = author;
		this.number = number;
		this.pubDate = pubDate;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", number=" + number + ", pubDate=" + pubDate
				+ ", description=" + description + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getPubDate() {
		return pubDate;
	}

	public void setPubDate(LocalDate pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
