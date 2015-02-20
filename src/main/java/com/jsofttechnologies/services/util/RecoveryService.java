package com.jsofttechnologies.services.util;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.util.EmailUtil;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("services/recovery_service")
@Stateless
public class RecoveryService {

	@PersistenceContext(unitName = ProjectConstants.MAIN_PU)
	private EntityManager entityManager;

	@EJB
	private EmailUtil emailUtil;

	@Path("forgot_password/{email}")
	@POST
	public Response forgotPassword(@PathParam("email") String email) {
		Response response = null;
		try {
			List<FlowUser> flowUserList = entityManager
					.createNamedQuery(FlowUser.FIND_BY_EMAIL, FlowUser.class)
					.setParameter("email", email).getResultList();

			if (flowUserList != null && !flowUserList.isEmpty()) {

				FlowUser flowUser = flowUserList.get(0);

				FlowUserDetail flowUserDetail = entityManager.find(
						FlowUserDetail.class, flowUser.getId());

				Map<String, Object> jsonMap = new HashMap<>();

				jsonMap.put("id", flowUser.getId());
				jsonMap.put("secretQuestion",
						flowUserDetail.getSecretQuestion());

				response = Response.ok(ProjectHelper.json(jsonMap),
						MediaType.APPLICATION_JSON_TYPE).build();
			} else {
				response = Response
						.serverError()
						.entity(ProjectHelper.message(email
                                + " doesn't exists!"))
						.type(MediaType.APPLICATION_JSON_TYPE).build();
			}

		} catch (Exception e) {
			response = ProjectHelper.error(e);
		}

		return response;
	}

	@Path("submit_password")
	@POST
	public Response submitPassword(Answer answer) {
		Response response = null;
		try {

			FlowUser flowUser = entityManager
					.find(FlowUser.class, answer.id);

			String hashedPassword = PasswordHash.createHash(answer
                    .getPassword());

			flowUser.setPassword(hashedPassword);

			entityManager.merge(flowUser);

			StringBuilder builder = new StringBuilder();
			builder.append("Hi ").append(flowUser.getUsername()).append(".\n");
			builder.append(
					"\tYour password has been changed to "
							+ answer.getPassword()).append(".");
			emailUtil.send(ProjectConstants.SUBJECT_PASSWORD_RESET,
					builder.toString(), flowUser.getEmail());

			response = Response.ok(
					ProjectHelper.message("Password has been changed."),
					MediaType.APPLICATION_JSON_TYPE).build();

		} catch (Exception e) {
			response = ProjectHelper.error(e);
		}
		return response;
	}

	@Path("answer")
	@POST
	public Response answer(Answer answer) {
		Response response = null;
		try {

			FlowUserDetail flowUserDetail = entityManager.find(
					FlowUserDetail.class, answer.getId());

			if (flowUserDetail.getSecretAnswer() != null) {
				if (flowUserDetail.getSecretAnswer().toLowerCase()
						.equals(answer.getAnswer().toLowerCase())) {
					response = Response.ok().build();
				} else {
					response = Response.serverError()
							.entity(ProjectHelper.message("Invalid answer."))
							.type(MediaType.APPLICATION_JSON_TYPE).build();
				}
			} else {
				response = Response.serverError()
						.entity(ProjectHelper.message("Invalid answer."))
						.type(MediaType.APPLICATION_JSON_TYPE).build();
			}

		} catch (Exception e) {
			response = ProjectHelper.error(e);
		}
		return response;

	}

	@XmlRootElement
	public static class Answer {
		private Long id;
		private String secretQuestion;
		private String answer;
		private String password;

		public void setId(Long id) {
			this.id = id;
		}

		public Long getId() {
			return id;
		}

		public void setSecretQuestion(String secretQuestion) {
			this.secretQuestion = secretQuestion;
		}

		public String getSecretQuestion() {
			return secretQuestion;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public String getAnswer() {
			return answer;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPassword() {
			return password;
		}

	}

}
