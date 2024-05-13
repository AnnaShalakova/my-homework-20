package ru.tinkoff.fintech.meowle.kaspresso

import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.fintech.meowle.PreferenceRule
import ru.tinkoff.fintech.meowle.presentation.MainActivity
import ru.tinkoff.fintech.meowle.screens.kaspresso.KaspressoCatDetailsScreen
import ru.tinkoff.fintech.meowle.screens.kaspresso.KaspressoRatingScreen
import ru.tinkoff.fintech.meowle.screens.kaspresso.element.NavigationBarElements
import ru.tinkoff.fintech.meowle.wiremock.WireMockHelper

class KaspressoCatDetailsTest : TestCase() {

    @get:Rule
    val prefs = PreferenceRule()

    @get: Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    val ratingScreen = KaspressoRatingScreen()
    val navigationBar = NavigationBarElements()
    val catDetailsScreen = KaspressoCatDetailsScreen()

    val newDescription = "Новое описание"

    @Test
    fun kaspressoLikeCat() = run {
        WireMock.stubFor(
            WireMock.get("/api/likes/cats/rating")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("rating_response.json"))
                )
        )

        WireMock.stubFor(
            WireMock.get("/api/likes/cats/rating")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("rating_response.json"))
                )
        )

        WireMock.stubFor(
            WireMock.get("/api/core/cats/get-by-id?id=17815")                .willReturn(
                WireMock.aResponse()
                    .withStatus(200)
                    .withBody(WireMockHelper.fileToString("get-by-id_response.json"))
            )
        )

        WireMock.stubFor(
            WireMock.get("/api/photos/cats/17815/photos")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("photos_response.json"))
                )
        )

        WireMock.stubFor(
            WireMock.post("/api/likes/cats/17815/likes")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("likes_response.json"))
                )
        )

        navigationBar.clickRatingTab()
        ratingScreen.clickDislikeTab()
        ratingScreen.clickCatInRating(0)
        catDetailsScreen.clickLike()

        WireMock.verify(
            WireMock.postRequestedFor(WireMock.urlPathMatching(".*/likes"))
                .withRequestBody(
                    WireMock.equalToJson(
                        "{\n" +
                                "\t\"dislike\": false,\n" +
                                "\t\"like\": true\n" +
                                "}"
                    )
                )
        )
    }

    @Test
    fun kaspressoEditDescriptionCat() = run {
        WireMock.stubFor(
            WireMock.get("/api/likes/cats/rating")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("rating_response.json"))
                )
        )

        WireMock.stubFor(
            WireMock.get("/api/core/cats/get-by-id?id=17815")                .willReturn(
                WireMock.aResponse()
                    .withStatus(200)
                    .withBody(WireMockHelper.fileToString("get-by-id_response.json"))
            )
        )

        WireMock.stubFor(
            WireMock.get("/api/photos/cats/17815/photos")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("photos_response.json"))
                )
        )

        WireMock.stubFor(
            WireMock.post("/api/core/cats/save-description")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("save-description_response.json"))
                )
        )

        navigationBar.clickRatingTab()
        ratingScreen.clickCatInRating(4)
        catDetailsScreen.clickEdit()
        catDetailsScreen.enterDescription(newDescription)
        Thread.sleep(2000)
        catDetailsScreen.clickConfirm()
        catDetailsScreen.checkDescription(newDescription)

        WireMock.verify(
            WireMock.postRequestedFor(WireMock.urlPathMatching(".*/save-description"))
                .withRequestBody(
                    WireMock.equalToJson(
                        "{\n" +
                                "\t\"catDescription\": \"Новое описание\",\n" +
                                "\t\"catId\": 17815\n" +
                                "}"
                    )
                )
        )
    }
}