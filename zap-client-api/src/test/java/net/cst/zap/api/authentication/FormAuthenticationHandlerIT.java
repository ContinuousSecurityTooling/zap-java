package net.cst.zap.api.authentication;

import static net.cst.zap.api.authentication.AbstractAuthenticationHandler.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import net.cst.zap.api.model.AuthenticationInfo;
import net.cst.zap.api.util.BaseIT;
import org.zaproxy.clientapi.core.ApiResponseSet;
import org.zaproxy.clientapi.core.ClientApiException;

public class FormAuthenticationHandlerIT extends BaseIT {
	
	@Test
	public void handleAuthenticationTest() throws ClientApiException {
		AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
				.buildFormAuthenticationInfo("http://app:8080/login", "username", "password");
		
		String targetUrl = "targetUrl";
		FormAuthenticationHandler formHandler = new FormAuthenticationHandler(api, zapInfo, authenticationInfo);
		formHandler.handleAuthentication(targetUrl);
		
		ApiResponseSet response = (ApiResponseSet) api.authentication.getAuthenticationMethod(ZAP_DEFAULT_CONTEXT_ID);
		assertEquals(response.getAttribute("loginUrl"), authenticationInfo.getLoginUrl());
		assertEquals(response.getAttribute("loginRequestData"), authenticationInfo.getLoginRequestData());
		assertEquals(response.getAttribute("methodName"), "formBasedAuthentication");
	}
	
}
