package properties;

public class SystemProperties {
    public static String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
    public static String browser = System.getProperty("browser", "chrome");
    public static String browserSize = System.getProperty("browserSize", "1920x1080");
    public static String browserVersion = System.getProperty("browserVersion", "128.0");
    public static String task = System.getProperty("task", "smoke");
    public static String remoteUrl = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
}