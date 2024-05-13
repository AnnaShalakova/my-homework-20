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
import ru.tinkoff.fintech.meowle.screens.kaspresso.KaspressoSearchScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockHelper

class KaspressoSearchTest : TestCase() {

    @get:Rule
    val prefs = PreferenceRule()

    @get: Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    val searchScreen = KaspressoSearchScreen()
    val detailsScreen = KaspressoCatDetailsScreen()

    val catName ="Барсик"

    @Test
    fun kaspressoSearchCat() = run {

        WireMock.stubFor(
            WireMock.post("/api/core/cats/search")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("search_response.json"))
                )
        )
        searchScreen.findCat(catName)
        searchScreen.checkCatName(catName,0)
    }

    @Test
    fun kaspressoSearchCatAndClick() = run {

        WireMock.stubFor(
            WireMock.post("/api/core/cats/search")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("search_response.json"))
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

        searchScreen.findCat(catName)
        searchScreen.clickCatInSearchResult(0)
        detailsScreen.checkName(catName)
    }
}