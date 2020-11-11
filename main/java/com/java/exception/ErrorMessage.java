package com.java.exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	
	
		String message;
		LocalDate date;
		String url;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public ErrorMessage(String message, LocalDate date, String url) {
			super();
			this.message = message;
			this.date = date;
			this.url = url;
		}

		public ErrorMessage() {
			super(); 
			// TODO Auto-generated constructor stub } } 
		}
}
