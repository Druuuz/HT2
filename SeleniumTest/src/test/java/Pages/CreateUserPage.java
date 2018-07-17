package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CreateUserPage extends WebPage {

    @FindBy(tagName = "form")
    private List<WebElement> formsFromPage;

    @FindBy(name = "username")
    private WebElement name;

    @FindBy(name = "password1")
    private WebElement password;

    @FindBy(name = "password2")
    private WebElement confirmPassword;

    @FindBy(name = "fullname")
    private WebElement fullName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(id = "yui-gen1-button")
    private WebElement submitFormButton;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public void setDataToForm(String name, String password, String confirmPassword, String fullName, String email) {
        setName(name);
        setFullName(fullName);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
    }

    public void submitFieldForm(String name, String password, String confirmPassword, String fullName, String email) {
        setDataToForm(name, password, confirmPassword, fullName, email);
        submitForm();
    }

    public void submitForm() {
        submitFormButton.click();
    }

    public void setName(String value) {
        name.clear();
        name.sendKeys(value);
    }

    public void setFullName(String value) {
        fullName.clear();
        fullName.sendKeys(value);
    }

    public void setEmail(String value) {
        email.clear();
        email.sendKeys(value);
    }

    public void setPassword(String value) {
        password.clear();
        password.sendKeys(value);
    }

    public void setConfirmPassword(String value) {
        confirmPassword.clear();
        confirmPassword.sendKeys(value);
    }

    public boolean formExists() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//html/body"), 1));
        Collection<WebElement> forms = formsFromPage;
        if (forms.isEmpty()) {
            return false;
        }
        Iterator<WebElement> i = forms.iterator();
        boolean form_found = false;
        WebElement form = null;
        while (i.hasNext()) {
            form = i.next();
            List<WebElement> listOfFields = form.findElements(By.tagName("input"));
            try {
                if ((form.findElement(By.name("username")).getAttribute("type").equalsIgnoreCase("text")) &&
                        (form.findElement(By.name("username")).getText().equals("")) &&
                        (form.findElement(By.name("fullname")).getAttribute("type").equalsIgnoreCase("text")) &&
                        (form.findElement(By.name("fullname")).getText().equals("")) &&
                        (form.findElement(By.name("email")).getAttribute("type").equalsIgnoreCase("text")) &&
                        (form.findElement(By.name("email")).getText().equals("")) &&
                        (form.findElement(By.name("password1")).getAttribute("type").equalsIgnoreCase("password")) &&
                        (form.findElement(By.name("password1")).getText().equals("")) &&
                        (form.findElement(By.name("password2")).getAttribute("type").equalsIgnoreCase("password")) &&
                        (form.findElement(By.name("password2")).getText().equals(""))) {
                    form_found = true;
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return form_found;
    }
}
