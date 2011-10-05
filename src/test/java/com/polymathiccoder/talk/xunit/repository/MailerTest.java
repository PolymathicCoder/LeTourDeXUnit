package com.polymathiccoder.talk.xunit.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class MailerTest {
	private transient Mailer mailer;
	private transient SimpleSmtpServer simpleSmtpServer;

	public final static String TO = "test@test.com"; // NOPMD
	public final static String SUBJECT = "Test";
	public final static String BODY = "This is a test";


	@Before
	public void setUp() {
		simpleSmtpServer = SimpleSmtpServer.start();

		final Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", "localhost");
		properties.setProperty("mail.user", "emailuser");
		properties.setProperty("mail.password", "");
		final Session mailSession = Session.getDefaultInstance(properties);

		mailer = new Mailer(mailSession);
	}

	@After
	public void tearDown() {
		simpleSmtpServer.stop();
	}

	@Test
	public void sendEmail_validParams_emailSent() throws MessagingException { // NOPMD
		mailer.sendEmail(TO, SUBJECT, BODY);

		final SmtpMessage email = (SmtpMessage) simpleSmtpServer.getReceivedEmail().next();

	    assertThat("The email recipient is wrong", email.getHeaderValue("To"), is(equalTo(TO)));
	    assertThat("The email subject is wrong", email.getHeaderValue("Subject"), is(equalTo(SUBJECT)));
	    assertThat("The email body is wrong", email.getBody(), is(equalTo(BODY)));
	}
}
