package BT1_BT2_BT3;

import common.BaseTest;
import common.LocatorCMS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Edit_DeleteCategory extends BaseTest {


    public void LoginCMS() {
        driver.get("https://cms.anhtester.com/login");
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerLoginPage)).isDisplayed(), "Header không tồn tại, không phải trang Login");
        driver.findElement(By.xpath(LocatorCMS.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LocatorCMS.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LocatorCMS.buttonLogin)).click();
        sleep(1);

    }

    @Test
    public void testEditCategory() {
        LoginCMS();
        Assert.assertEquals(driver.getCurrentUrl(), "https://cms.anhtester.com/admin", "Không đến được trang Admin");        // Categories page

        // Categories page
        driver.findElement(By.xpath(LocatorCMS.menuProducts)).click();
        driver.findElement(By.xpath(LocatorCMS.menuCategory)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerCategories)).isDisplayed(), "Không đến được trang All Categories");
        sleep(1);

        String Category_Name = "Phương Add Category 01";

        // Tìm kiếm Category đã thêm
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Category_Name);
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Keys.ENTER);
        sleep(5);

        // Edit category
        driver.findElement(By.xpath(LocatorCMS.buttonEditCategory)).click();
        sleep(1);
        driver.findElement(By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div/div")).click();
        driver.findElement(By.xpath(LocatorCMS.inputSearchParentCategory)).sendKeys("Demo category 2");
        driver.findElement(By.xpath(LocatorCMS.inputSearchParentCategory)).sendKeys(Keys.ENTER);
        String EditinputParentCategory = driver.findElement(By.xpath(LocatorCMS.EditinputParentCategory)).getText();
        Assert.assertEquals(EditinputParentCategory, "Demo category 2", "Giá trị Parent Category edit không đúng");
        driver.findElement(By.xpath(LocatorCMS.buttonSaveCategory)).click();
        sleep(1);

    }

    @Test
    public void testDeleteCategory() {
        LoginCMS();
        Assert.assertEquals(driver.getCurrentUrl(), "https://cms.anhtester.com/admin", "Không đến được trang Admin");        // Categories page

        // Categories page
        driver.findElement(By.xpath(LocatorCMS.menuProducts)).click();
        driver.findElement(By.xpath(LocatorCMS.menuCategory)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerCategories)).isDisplayed(), "Không đến được trang All Categories");
        sleep(1);

        String Category_Name = "Phương Add Category 01";

        // Tìm kiếm Category đã thêm

        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Category_Name);
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Keys.ENTER);
        sleep(5);

        // Xóa category
        driver.findElement(By.xpath(LocatorCMS.buttonDeleteCategory)).click();
        driver.findElement(By.xpath(LocatorCMS.buttonDeletepopup)).click();

        // Tìm kiếm Category đã xóa
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Category_Name);
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Keys.ENTER);
        sleep(2);

        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.textNothingFound)).isDisplayed(), "Xóa category không thành công.");
    }

}
