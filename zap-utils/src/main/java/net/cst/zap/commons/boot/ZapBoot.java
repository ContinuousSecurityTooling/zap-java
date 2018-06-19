package net.cst.zap.commons.boot;

import net.cst.zap.commons.ZapInfo;
import net.cst.zap.commons.exception.ZapInitializationTimeoutException;

/**
 * This interface should be implemented by any class capable 
 * of starting and stopping ZAP, no matter how.
 * 
 * @author pdsec
 */
public interface ZapBoot {

	/**
	 * Starts ZAP.
	 * <p>
	 * It should throw {@link ZapInitializationTimeoutException ZapInitializationTimeoutException}
	 * in case ZAP is not started before a timeout, defined by {@code zapInfo.initializationTimeout}
	 * (the default value is {@code 120000}).
	 * 
	 * @param zapInfo an object with all the information needed to start ZAP.
	 */
	void startZap(ZapInfo zapInfo);
	
	/**
	 * Stops ZAP.
	 */
	void stopZap();
	
}
