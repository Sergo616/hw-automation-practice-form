package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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
        $(".table-responsive").shouldHave(text("Bob Smith"));
        $(".table-responsive").shouldHave(text("bob-smith@help.cop"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("5551234567"));
        $(".table-responsive").shouldHave(text("09 January,1999"));
        $(".table-responsive").shouldHave(text("History"));
        $(".table-responsive").shouldHave(text("Sports, Reading"));
        $(".table-responsive").shouldHave(text("test-pic.png"));
        $(".table-responsive").shouldHave(text("Main Street 1"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Merrut"));
    }

    @Test
    void successfulMinimumFillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Smith");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("5551234567");
        $("#submit").scrollIntoView(true).click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Bob Smith"), text("Male"), text("5551234567"));
    }

    @Test
    void emptyFormTest() {
        open("/automation-practice-form");
        $("#submit").scrollIntoView(true).click();

        $(".modal-dialog").shouldNot(appear);
    }

    @Test
    void wrongPhoneFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Smith");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("555");
        $("#submit").scrollIntoView(true).click();

        $(".modal-dialog").shouldNot(appear);
    }

    @Test
    void wrongEmailFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("wrong_email_format");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("5551234567");
        $("#submit").scrollIntoView(true).click();

        $(".modal-dialog").shouldNot(appear);
    }

    @Test
    void successfulSimpleFormTest() {
        open("/text-box");
        $("#userName").setValue("Bob Green");
        $("#userEmail").setValue("bob@green.com");
        $("#currentAddress").setValue("address 1");
        $("#permanentAddress").setValue("address 2");
        $("#submit").click();

        $("#output #name").shouldHave(text("Bob Green"));
        $("#output #email").shouldHave(text("bob@green.com"));
        $("#output #currentAddress").shouldHave(text("address 1"));
        $("#output #permanentAddress").shouldHave(text("address 2"));
    }

    @Test
    void wrongEmailSimpleFormTest() {
        open("/text-box");
        $("#userEmail").setValue("wrong_email_format");
        $("#submit").click();

        $("#output #email").shouldNot(exist);
    }
}
