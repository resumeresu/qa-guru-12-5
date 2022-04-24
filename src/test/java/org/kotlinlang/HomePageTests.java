package org.kotlinlang;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import items.KeepInTouch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HomePageTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://kotlinlang.org";
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "Multiplatform Mobile",
            "Server-side",
    })
    @ParameterizedTest(name = "Clicking tile menu item: {0}")
    void homepageTilesTest(String elementText) {
        Selenide.open("/");
        $x("//h3[text()='" + elementText + "']").click();
        $("a[href='/']").shouldHave(text(elementText));
    }

    @CsvSource(value = {
            "Web Frontend, Kotlin for JavaScript",
            "Android, Kotlin for Android"
    })
    @ParameterizedTest(name = "Clicking tile menu item: {0}")
    void homepageTilesTest2(String elementText, String assertionText) {
        Selenide.open("/");
        $x("//h3[text()='" + elementText + "']").click();
        $(".article__h1").shouldHave(text(assertionText));
    }

    static Stream<Arguments> tryKotlinTestText() {
        return Stream.of(
                arguments("Simple", "Hi, stranger!"),
                arguments("Asynchronous", "Liftoff!"),
                arguments("Object-oriented", "It's me, Sam."),
                arguments("Functional", "Ma\n" +
                        "[Adam, Ma]"),
                arguments("Ideal for tests", "\n" +
                        "Passed: test sum\n" +
                        "Passed: test computation")
        );
    }
    @MethodSource("tryKotlinTestText")
    @ParameterizedTest
    void tryKotlinTest(String elementText, String resultText) {
        Selenide.open("/");
        $(byText(elementText)).scrollIntoView(true).click();
        $(".kotlin-code-examples-section__run").click();
        $(".code-output").shouldHave(text(resultText), Duration.ofSeconds(5));
    }

    @EnumSource(KeepInTouch.class)
    @ParameterizedTest
    void keepInTouchAnimationRevealsItems(KeepInTouch title) {
        Selenide.open("/community");
        $(".scroll-down-hint.aos-init").click();
        $(byText(title.elementTitle)).shouldBe(visible, Duration.ofSeconds(5));
    }
}



