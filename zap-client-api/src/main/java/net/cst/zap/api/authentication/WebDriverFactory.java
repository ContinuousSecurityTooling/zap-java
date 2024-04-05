package net.cst.zap.api.authentication;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.cst.zap.api.model.AuthenticationInfo;
import net.cst.zap.commons.ZapInfo;

public final class WebDriverFactory {

    private static final int FIREFOX_MANUAL_PROXY_CONFIGURATION_OPTION = 1;

    public static WebDriver makeWebDriver(ZapInfo zapInfo, AuthenticationInfo authenticationInfo) {
        String host = zapInfo.getHost();
        int port = zapInfo.getPort();

        switch (authenticationInfo.getSeleniumDriver()) {
            case HTMLUNIT:
                return makeHtmlUnitDriver(host, port);
            case FIREFOX:
                return makeFirefoxDriver(host, port);
            case CHROME:
                return makeChromeDriver(host, port);
            default:
                return makeFirefoxDriver(host, port);
        }
    }

    public static HtmlUnitDriver makeHtmlUnitDriver(String host, int port) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setProxy(host, port);
        return driver;
    }

    public static FirefoxDriver makeFirefoxDriver(String host, int port) {
        FirefoxOptions profile = new FirefoxOptions();
        profile.setCapability("network.proxy.type", FIREFOX_MANUAL_PROXY_CONFIGURATION_OPTION);
        profile.setCapability("network.proxy.share_proxy_settings", true);
        profile.setCapability("network.proxy.no_proxies_on", "");
        profile.setCapability("network.proxy.http", host);
        profile.setCapability("network.proxy.http_port", port);

        return new FirefoxDriver(profile);
    }

    public static ChromeDriver makeChromeDriver(String host, int port) {
        ChromeOptions profile = new ChromeOptions();
        profile.addArguments("--proxy-server=http://" + host + ":" + port);
        return new ChromeDriver(profile);
    }

    private static WebDriver makePhantomJSDriver(String host, int port) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("proxy", createProxy(host, port));

        return new PhantomJSDriver(capabilities);
    }

    private static Proxy createProxy(String host, int port) {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(host + ":" + port);
        return proxy;
    }

    private WebDriverFactory() {
    }

}
