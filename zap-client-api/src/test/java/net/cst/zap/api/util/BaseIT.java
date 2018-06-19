package net.cst.zap.api.util;

import org.testng.annotations.BeforeClass;

import net.cst.zap.api.ZapClient;
import net.cst.zap.commons.ZapInfo;
import org.zaproxy.clientapi.core.ClientApi;

/**
 * Base class for integration tests. 
 * 
 * @author pdsec
 */
public class BaseIT {

	protected ZapInfo zapInfo;
	protected ZapClient zapClient;
	protected ClientApi api;
	
	@BeforeClass
	public void init() {
		zapInfo   = ZapInfo.builder().buildToUseRunningZap(ZapProperties.getHost(), ZapProperties.getPort());
		zapClient = new ZapClient(zapInfo);
		api       = new ClientApi(zapInfo.getHost(), zapInfo.getPort());
	}
	
}
