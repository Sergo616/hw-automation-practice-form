package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class PracticeFormTests extends TestBase{

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("bob-smith@help.cop");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("5551234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--009").click();
        $("#subjectsInput").setValue("History").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("img/test-pic.png");
        $("#currentAddress").setValue("Main Street 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Merrut")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Bob Smith"), text("bob-smith@help.cop"), text("Male"), text("5551234567"), text("09 January,1999"), text("History"), text("Sports, Reading"), text("test-pic.png"), text("Main Street 1"), text("Uttar Pradesh Merrut"));


    }
}
