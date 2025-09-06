package pages.components;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class DeleteBannersComponent {
    public void deleteBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
