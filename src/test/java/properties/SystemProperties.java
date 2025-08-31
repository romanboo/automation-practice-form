package properties;

public class SystemProperties {
    public static String browser = System.getProperty("browser", "chrome");
    public static String task = System.getProperty("task", "smoke");
    public static String remoteUrl = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
}