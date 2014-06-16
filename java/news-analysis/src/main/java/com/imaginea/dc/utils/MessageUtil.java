package com.imaginea.dc.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

public class MessageUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

	private static MessageSource messageSource;
	
	
	/* Message Source related */
	
	public static String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}
	
	public static String getMessage(String key, Object[] placeholders) {
		return messageSource.getMessage(key, placeholders, Locale.getDefault());
	}
	
	public static String getMessage(String key, Object[] placeholders, Locale locale) {
		return messageSource.getMessage(key, placeholders, locale);
	}
	
	
	/* Getters and Setters */
	
	public void setMessageSource(MessageSource messageSource) {
		MessageUtil.messageSource = messageSource;
	}



}
