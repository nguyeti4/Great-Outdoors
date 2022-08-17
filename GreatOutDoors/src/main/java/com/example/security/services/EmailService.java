package com.example.security.services;

import com.example.payload.request.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
}
