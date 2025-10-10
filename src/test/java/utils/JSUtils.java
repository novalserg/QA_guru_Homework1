package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class JSUtils {
    public void deleteBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
