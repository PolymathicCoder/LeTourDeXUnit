package com.polymathiccoder.talk.xunit.repository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	private transient final Session mailSession;

	public Mailer(final Session mailSession) {
		this.mailSession = mailSession;
	}

	public void sendEmail(final String emailRecipient, final String emailSubject, final String emailBody) throws MessagingException {

		final Transport transport = mailSession.getTransport();

		final MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(emailSubject);
		message.setContent(emailBody, "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));

		transport.connect();
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}
}
