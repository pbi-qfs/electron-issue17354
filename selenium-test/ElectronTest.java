import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.ServerSocket;

import javax.script.ScriptException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ElectronTest
{
    protected static int getFreeTcpPort()
    {
        try (final ServerSocket s = new ServerSocket(0)) {
        final int localPort = s.getLocalPort();
        return localPort;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws ScriptException, FileNotFoundException, InterruptedException {

        final String driverPath = "lib/chromedriver-v5.0.0-beta.6-darwin-x64/chromedriver";
        final String binaryPath = "app/dist/mac/electron-quick-start.app/Contents/MacOS/electron-quick-start";

        System.out.format("Driver: %s\nBinary: %s\n\n", driverPath, binaryPath);

        System.out.println("Starting with ");
        System.setProperty("webdriver.chrome.driver", driverPath);

        {
            final int freeTcpPort = getFreeTcpPort();
            System.out.println("Starting with --remote-debugging-port=" + freeTcpPort);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-debugging-port=" + freeTcpPort);
            options.setBinary(binaryPath);
            WebDriver driver = new ChromeDriver(options);
            System.out.println("Worked well.\n");
            driver.quit();
        }

        {
            System.out.println("Starting without parameters");

            ChromeOptions options = new ChromeOptions();
            options.setBinary(binaryPath);
            WebDriver driver = new ChromeDriver(options);
            System.out.println("Worked well.\n");
            driver.quit();
        }
    }

}
