package net.cst.zap.api.analysis;

import net.cst.zap.api.model.AnalysisInfo;
import net.cst.zap.api.report.ZapReport;
import org.zaproxy.clientapi.core.ClientApi;

public class WithAjaxSpiderAnalyzer extends BaseAnalyzer {
	
	public WithAjaxSpiderAnalyzer(String apiKey, ClientApi api) {
		super(apiKey, api);
	}
	
	public ZapReport analyze(AnalysisInfo analysisInfo) {
		init(analysisInfo.getAnalysisTimeoutInMillis());
		
		runSpider(analysisInfo);
		runAjaxSpider(analysisInfo);
		runActiveScan(analysisInfo);

		return generateReport();
	}
	
}
