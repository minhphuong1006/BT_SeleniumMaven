package BT1_BT2_BT3;

import common.BaseTest;
import common.LocatorCMS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddCategory extends BaseTest {
    //+ Tách Login sẽ chạy trước mỗi test case
    @BeforeMethod
    public void LoginCMS() {
        driver.get("https://cms.anhtester.com/login");
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerLoginPage)).isDisplayed(), "Header không tồn tại, không phải trang Login");
        driver.findElement(By.xpath(LocatorCMS.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LocatorCMS.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LocatorCMS.buttonLogin)).click();
        sleep(1);

    }

    @Test(priority = 1)
    public void testAddCategory () {

        // Login CMS
        //LoginCMS();
        Assert.assertEquals(driver.getCurrentUrl(), "https://cms.anhtester.com/admin", "Không đến được trang Admin");

        // Categories page

        driver.findElement(By.xpath(LocatorCMS.menuProducts)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCMS.menuCategory)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerCategories)).isDisplayed(), "Không đến được trang All Categories");
        sleep(1);

        driver.findElement(By.xpath(LocatorCMS.buttonAddNewCategory)).click();
        Assert.assertTrue(driver.findElement(By.xpath(LocatorCMS.headerAddCategory)).isDisplayed(), "Không đến được trang Add Category");
        sleep(1);

        // Add Category

        System.out.println("Add Category");
        String Category_Name = "Phương Add Category 01";

        driver.findElement(By.xpath(LocatorCMS.inputNameCategory)).sendKeys(Category_Name);
        driver.findElement(By.xpath(LocatorCMS.dropdownParentCategory)).click();
        driver.findElement(By.xpath(LocatorCMS.inputSearchParentCategory)).sendKeys("Demo category 1");
        driver.findElement(By.xpath(LocatorCMS.inputSearchParentCategory)).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(LocatorCMS.inputOrderingNumber)).sendKeys("100695");

        driver.findElement(By.xpath(LocatorCMS.dropdownType)).click();
        driver.findElement(By.xpath(LocatorCMS.selctType)).click();

        //Chú ý phần chọn hình: click Browse -> search tên hình add bằng tay -> click item hình -> Click Add button
        driver.findElement(By.xpath(LocatorCMS.inputBanner)).click();
        driver.findElement(By.xpath(LocatorCMS.searchBanner)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCMS.searchBanner)).sendKeys("Add_Category");
        sleep(3);
        driver.findElement(By.xpath(LocatorCMS.selectBanner)).click();
        sleep(1);

        driver.findElement(By.xpath(LocatorCMS.buttonAddFiles)).click();
        sleep(3);
        driver.findElement(By.xpath(LocatorCMS.inputIcon)).click();
        sleep(2);
        driver.findElement(By.xpath(LocatorCMS.searchIcon)).sendKeys("Add_Category");
        sleep(3);
        driver.findElement(By.xpath(LocatorCMS.selectIcon)).click();
        sleep(1);
        driver.findElement(By.xpath(LocatorCMS.buttonAddFiles)).click();

        driver.findElement(By.xpath(LocatorCMS.inputMetaTitle)).sendKeys("Text Title");
        driver.findElement(By.xpath(LocatorCMS.inputMetadescription)).sendKeys("Text Description");
        driver.findElement(By.xpath(LocatorCMS.dropdownFilteringAttributes)).click();
        driver.findElement(By.xpath(LocatorCMS.inputSearchFilteringAttributes)).sendKeys("Size",Keys.ENTER);
        //driver.findElement(By.xpath(LocatorCMS.inputSearchFilteringAttributes)).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(LocatorCMS.buttonSaveCategory)).click();

        //- Sau khi add category xong trở về menu Category (trang list)
        //- Search category name vừa add
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Category_Name);
        driver.findElement(By.xpath(LocatorCMS.inputSearchCategories)).sendKeys(Keys.ENTER);
        sleep(2);

        // Kiểm tra giá trị name của Category mới thêm
        // Get cái Text của item đầu tiên cột Name dưới Table sau khi search xem đúng không
        String categoryItem = driver.findElement(By.xpath(LocatorCMS.firstItemOnCategoryTable)).getText();
        Assert.assertEquals(categoryItem, Category_Name, "Giá trị name của Category không đúng");

    }
}





//    BÀI TẬP 1 CHẠY TRÊN PROJECT MAVEN

//        (Bắt đầu từ bài học số 9 trở về sau)
//        - https://cms.anhtester.com/login
//        - Add Category
//        + Add hình sẵn vài tấm bằng tay trước khi chạy auto
//        + Chú ý phần chọn hình: click Browse -> search tên hình add bằng tay -> click item hình -> Click Add button
//        - Sau khi add category xong trở về menu Category (trang list)
//        - Search category name vừa add
//        - Get cái Text của item đầu tiên cột Name dưới Table sau khi search xem đúng không

//    BÀI TẬP 2 VỀ GHI CHÚ TRONG TESTNG (Annotation)
//
//        - Tách code cũ ở BT Add Category ra theo các ghi chú (annotation):
//        (không dùng hàm main nữa)
//
//        + Tách đoạn setup Driver ra riêng không để chung trong test case
//        + Tách Login sẽ chạy trước mỗi test case
//        + Optimize code chạy cho ổn định hơn (sleep, xpath)
//        + Tập chạy code trên file suite XML
