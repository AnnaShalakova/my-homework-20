package ru.tinkoff.fintech.meowle.kaspresso

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.fintech.meowle.PreferenceRule
import ru.tinkoff.fintech.meowle.presentation.MainActivity
import ru.tinkoff.fintech.meowle.screens.kaspresso.KaspressoComposeSearchScreen
import ru.tinkoff.fintech.meowle.wiremock.WireMockHelper

class KaspressoComposeSearchTest  : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val prefs = PreferenceRule()

    @get: Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun searchCats() = run {
        WireMock.stubFor(
            WireMock.post("/api/core/cats/search")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("search_response.json"))
                )
        )
        val searchScreen = KaspressoComposeSearchScreen(composeTestRule)

        searchScreen.searchBar.findCat("Барсик")

        searchScreen.checkFirstCatName("Барсик")
    }
}