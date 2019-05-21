package com;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import javax.swing.*;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    public String productName = null;

    public MainPage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver,15,150);
    }



    public MainPage searchProduct(String _item) throws InterruptedException{

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.cssSelector("input[id='gh-ac")),_item);
        clickButtonByCssSelector("#gh-btn");
        System.out.println("Ürün aratıldı.");

        return new MainPage(driver);
    }

    protected MainPage  searchIt(){
        clickButtonById("gh-btn");
        return new MainPage(driver);
    }

    protected MainPage chooseTheFirstOne(){
        clickButtonByCssSelector("#srp-river-results-listing1 > div > div.s-item__info.clearfix > a > h3");
        return new MainPage(driver);
    }

    protected MainPage collectPriceAndAssigneToVar(){
        String _price = driver.findElement(By.cssSelector("#prcIsum")).getText();
        return new MainPage(driver);
    }

    protected MainPage addToWatchList(){
        clickButtonByCssSelector("#vi-atl-lnk > a > span.vi-atw-txt");
        return new MainPage(driver);
    }

    protected void clickButtonById(String _Id){
        wait.until(ExpectedConditions.elementToBeClickable(By.id(_Id))).click();
    }

    protected void clickButtonByCssSelector(String _css){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(_css))).click();
    }
    protected void sendKeysById(String _id,String _text){
        driver.findElement(By.id(_id)).sendKeys(_text);
    }protected void sendKeysByCssSelector(String _cssSelector,String _text){
        driver.findElement(By.id(_cssSelector)).sendKeys(_text);
    }
    protected void clickButtonByXpath(String _xpath){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(_xpath))).click();
    }
    protected void clickButtonByClassName(String _className){
        wait.until(ExpectedConditions.elementToBeClickable((By.className(_className)))).click();
    }
    protected void mouseOverByCssSelector(String _cssSelector){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(_cssSelector));
        action.moveToElement(element).build().perform();
        System.out.println("Elemente hover edildi.");
    }

    protected MainPage checkTheError(String _actual){
         String _expected = driver.findElement(By.cssSelector("body > div.srp-main.srp-main--isLarge > div.s-error > div > p")).getText();
         Assert.assertEquals("Condition False",_actual,_expected);
         return new MainPage(driver);

    }
    public MainPage login(String _username,String _password) throws InterruptedException{
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.cssSelector("#userid")),_username);
        jse.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.cssSelector("#pass")),_password);

        clickButtonByCssSelector("button[id='sgnBt']");
        System.out.println("Login olundu test üyeligim yok.");
        return new MainPage(driver);

    }


    protected MainPage selectOptionFromDropdown(String item) throws InterruptedException {
        Select selectByVisibleText = new Select(driver.findElement(By.cssSelector("#gh-cat")));
        selectByVisibleText.selectByVisibleText(item);
        Thread.sleep(5000);
        return new MainPage(driver);
    }

    protected MainPage checkThePageCorrect(String _expectedUrl) throws  InterruptedException{
        String pageUrl = driver.getCurrentUrl();
        System.out.println("Beklenen url" + _expectedUrl);
        System.out.println("Gelen url" +pageUrl);


        Assert.assertEquals("Condition False",pageUrl,_expectedUrl);
        System.out.println("Doğru sayfadayız.");
        return new MainPage(driver);

    }

    protected void justWait() throws InterruptedException {
        Thread.sleep(10000);
    }


}
