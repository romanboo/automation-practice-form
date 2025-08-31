package properties;

public class SystemProperties {

    public static String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
    public static String browser = System.getProperty("browser", "chrome");
    public static String browserSize = System.getProperty("browserSize", "1920x1080");
    public static String browserVersion = System.getProperty("browserVersion", "122");
}