package com.jsofttechnologies.util;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Stateless
@Asynchronous
public class EmailUtil {

	private static Properties properties;

	static {
		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}

	public static void emailSender(final String username,
			final String password, final String from, String subject,
			String content, String... to) {
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			List<Address> addresses = new ArrayList<>();
			for (String t : to) {
				addresses.add(new InternetAddress(t));
			}
			message.setSubject(subject);
			message.setRecipients(Message.RecipientType.TO,
					addresses.toArray(new Address[addresses.size()]));
			message.setText(content);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void send(String subject, String content, String... to) {
		try {
			ObjectInputStream ois = new ObjectInputStream(EmailUtil.class
					.getClassLoader().getResourceAsStream("smtp.auth"));

			SenderObject senderObject = (SenderObject) ois.readObject();

			emailSender(senderObject.getEmail(), senderObject.getPassword(),
					senderObject.getEmail(), subject, content, to);

		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	public static final class SenderObject implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1583213419786656010L;
		private String email;
		private String password;

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return email;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "SenderObject [email=" + email + ", password=" + password
					+ "]";
		}

	}
}
