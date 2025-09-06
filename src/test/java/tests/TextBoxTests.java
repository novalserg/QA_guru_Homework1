package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import pages.components.TextBoxFillingResultsComponent;

public class TextBoxTests {

    @Test
    void fillFormTest() {

        TextBoxPage textBoxPage = new TextBoxPage();
        TextBoxFillingResultsComponent textBoxFillingResultsComponent = new TextBoxFillingResultsComponent();


        textBoxPage.openPage()
                .setUserName("Alexandra Zabnenkova")
                .setUserEmail("novalserg@soap.ru")
                .setCurrentAddress("Moscow, NY, UK")
                .setPermanentAddress("Moscow, Kashirskoye shosse, 58")
                .clickButton();

        textBoxFillingResultsComponent.checkOutputResult("Name", "Alexandra Zabnenkova")
                .checkOutputResult("Email", "novalserg@soap.ru")
                .checkOutputResult("Current Address", "Moscow, NY, UK")
                .checkOutputResult("Permananet Address", "Moscow, Kashirskoye shosse, 58");


    }
}