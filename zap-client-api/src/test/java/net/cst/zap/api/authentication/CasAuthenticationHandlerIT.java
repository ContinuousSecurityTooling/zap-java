package net.cst.zap.api.authentication;

import static net.cst.zap.api.authentication.AbstractAuthenticationHandler.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import net.cst.zap.api.model.AuthenticationInfo;
import net.cst.zap.api.util.BaseIT;
import net.cst.zap.api.util.ZapProperties;
import org.zaproxy.clientapi.core.ApiResponseSet;
import org.zaproxy.clientapi.core.ClientApiException;

public class CasAuthenticationHandlerIT extends BaseIT {

	@Test
	public void handleAuthenticationTest() throws ClientApiException {
		AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
				.buildCasAuthenticationInfo("http://app:8080/login", "username", "password", "protectedPage1", "protectedPage2");
		
		String targetUrl = "http://" + ZapProperties.getHost() + ":" + ZapProperties.getPort();
		CasAuthenticationHandler casHandler = new CasAuthenticationHandler(api, zapInfo, authenticationInfo);
		casHandler.handleAuthentication(targetUrl);
		
		ApiResponseSet response = (ApiResponseSet) api.authentication.getAuthenticationMethod(ZAP_DEFAULT_CONTEXT_ID);
		assertEquals(response.getAttribute("loginUrl"), authenticationInfo.getLoginUrl());
		assertEquals(response.getAttribute("protectedPages"), authenticationInfo.getProtectedPagesSeparatedByComma());
		assertEquals(response.getAttribute("methodName"), "scriptBasedAuthentication");
		assertEquals(response.getAttribute("scriptName"), "cas-auth");
	}
	
}
