package com.bygenji11.Util;

import com.bygenji11.NaverCafeMain;
import com.bygenji11.Skript.EvNaverCafe;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.logging.Level;


public class BrowserHandler implements Runnable {

    public static WebDriver webDriver;
    private DataHandler dataHandler = new DataHandler();
    private ThreadHandler threadHandler;
    private String subject;
    private String body;
    private NaverCafeMain instance;

    public BrowserHandler(String str1, String str2, ThreadHandler th, NaverCafeMain naverCafeMain) throws IOException {
        this.subject = str1;
        this.body = str2;
        this.threadHandler = th;
        this.instance = naverCafeMain;
    }

    private void CallEvent(String value){
        instance.getServer().getPluginManager().callEvent(new EvNaverCafe(value));
    }

    private void setClipBoard(String value){
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
    }

    private void setUpDefault() throws InterruptedException {
        CallEvent("SETUP");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        threadHandler.SetRun(true);
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        webDriver = new ChromeDriver();
        loginToNaver();
    }

    private void writeToCafe() {
        try {
            Thread.sleep(620);
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            webDriver.get("https://m.cafe.naver.com/ArticleWrite.nhn?m=write&clubid=30111005&menuid=28");
            wait.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            CallEvent("WRITE");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject")));
            webDriver.findElement(By.id("subject")).sendKeys(subject);
            webDriver.switchTo().frame(webDriver.findElement(By.id("frame")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            WebElement T_BOX_CONTENT = webDriver.findElement(By.tagName("body"));
            T_BOX_CONTENT.click();
            if(dataHandler.getBackSpace() > 0){
                for(int i = 0; i < dataHandler.getBackSpace(); i++){
                    T_BOX_CONTENT.sendKeys(Keys.BACK_SPACE);
                }
            }
            if(body.contains("<Enter>")){
                String[] bodylist = body.split("<Enter>");
                for(String b : bodylist){
                    T_BOX_CONTENT.sendKeys(Keys.ENTER);
                    T_BOX_CONTENT.sendKeys(b);
                }
            }else{
                T_BOX_CONTENT.sendKeys(body);
            }
            webDriver.switchTo().defaultContent();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ct\"]/div/div[14]/div[2]/a")));
            webDriver.findElement(By.xpath("//*[@id=\"ct\"]/div/div[14]/div[2]/a")).click();
            CallEvent("FINISH");
            threadHandler.SetRun(false);
            webDriver.quit();
        }catch (Exception e){
            CallEvent("WRITE_ERROR");
            threadHandler.SetRun(false);
            e.printStackTrace();
            webDriver.quit();
            webDriver = null;
        }
    }

    private void loginToNaver() throws InterruptedException {
        try {
            Thread.sleep(300);
            CallEvent("LOGIN");
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
            CallEvent("REDIRECT");
            writeToCafe();
        } catch (Exception e){
            CallEvent("LOGIN_ERROR");
            threadHandler.SetRun(false);
            e.printStackTrace();
            webDriver.quit();
            webDriver = null;
        }
    }

    @Override
    public void run() {
        try {
            setUpDefault();
        } catch (InterruptedException e) {
            threadHandler.SetRun(false);
            e.printStackTrace();
        }
    }

}
