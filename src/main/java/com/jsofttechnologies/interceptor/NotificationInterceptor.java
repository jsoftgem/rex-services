package com.jsofttechnologies.interceptor;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.services.session.NotificationSession;
import com.jsofttechnologies.util.ProjectConstants;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * Created by Jerico on 12/29/2014.
 */
@Notify
@Interceptor
public class NotificationInterceptor implements Serializable {

    @EJB
    private MergeExceptionSummary exceptionSummary;
    @EJB
    private NotificationSession notificationSession;

    @AroundInvoke
    public Object queue(InvocationContext ic) {
        Notify notify = null;

        Object proceed = null;

        try {
            proceed = ic.proceed();

            Object target = ic.getTarget();

            if (target != null) {
                if (target.getClass().isAnnotationPresent(Notify.class)) {
                    notify = target.getClass().getAnnotation(Notify.class);
                }
            }

            if (notify == null) {
                try {
                    return proceed;
                } catch (Exception e) {
                    exceptionSummary.handleException(e, target.getClass());
                }
            }

            if (proceed instanceof Response) {

                Response response = (Response) proceed;

                Response.StatusType statusType = response.getStatusInfo();


                if (statusType.getStatusCode() >= 200 && statusType.getStatusCode() <= 206) {

                    String authorizationScheme = response.getHeaderString(ProjectConstants.HEADER_AUTHORIZATION);

                    String authorization = authorizationScheme.replace(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

                    JSONObject jsonObject = new JSONObject(response.getEntity().toString());

                    String msg = jsonObject.getString("msg");
                    Long id = jsonObject.getLong("id");
                    notificationSession.queue(authorization, msg, "success", false, notify.alertType().toString(), notify.task(), notify.page(), id.toString(), null);
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return proceed;
    }

}
