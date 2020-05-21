package com.bygenji11.Util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class BrowserHandler implements Callable<Boolean> {

    private WebDriver webDriver = null;
    private DataHandler dataHandler;

    public BrowserHandler() throws InterruptedException, IOException {
        dataHandler = new DataHandler();
        Runtime.getRuntime().exec("taskkill /IM chromedriver.exe  /F");
        if (webDriver == null) {
            WebDriverManager.getInstance(CHROME).setup();
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            loginToNaver();
        }
    }

    private void writeToCafe(String subject, String body) throws InterruptedException {
        try {
            Thread.sleep(620);
            webDriver.get("https://m.cafe.naver.com/ArticleWrite.nhn?m=write&clubid=30111005&menuid=28");
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject")));
            WebElement T_BOX_SUBJECT = webDriver.findElement(By.id("subject"));
            T_BOX_SUBJECT.clear();
            T_BOX_SUBJECT.sendKeys(subject);
            webDriver.switchTo().frame("frame");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            WebElement T_BOX_CONTENT = webDriver.findElement(By.tagName("body"));
            T_BOX_CONTENT.click();
            T_BOX_CONTENT.sendKeys(body);
            webDriver.switchTo().defaultContent();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ct\"]/div/div[14]/div[2]/a")));
            webDriver.findElement(By.xpath("//*[@id=\"ct\"]/div/div[14]/div[2]/a")).click();
            Thread.sleep(3000);
            webDriver.close();
        }catch (Exception e){
            e.printStackTrace();
            webDriver.quit();
            webDriver = null;
        }
    }

    private void loginToNaver() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            Thread.sleep(300);
            webDriver.get("https://nid.naver.com/nidlogin.login?url=http%3A%2F%2Fwww.naver.com");
            Thread.sleep(640);
            setClipBoard(dataHandler.getID());
            Thread.sleep(320);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
            webDriver.findElement(By.id("id")).sendKeys(Keys.CONTROL + "v");
            Thread.sleep(730);
            setClipBoard(dataHandler.getPass());
            Thread.sleep(320);
            webDriver.findElement(By.id("pw")).sendKeys(Keys.CONTROL + "v");
            Thread.sleep(770);
            webDriver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
            writeToCafe("test", "test<Enter>");
        } catch (Exception e){
            webDriver.quit();
            webDriver = null;
        }
    }

    //testing
    public static void main(String[] args) throws IOException, InterruptedException {
       new BrowserHandler();

    }
    private void setClipBoard(String value){
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
    }


    @Override
    public Boolean call() throws Exception {
        Runtime.getRuntime().exec("taskkill /IM chromedriver.exe  /F");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bkcha\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
        driver.quit();
        return true;
    }
}
