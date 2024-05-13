package ru.tinkoff.fintech.meowle.kaspresso

import androidx.test.ext.junit.rules.activityScenarioRule
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.fintech.meowle.PreferenceRule
import ru.tinkoff.fintech.meowle.presentation.MainActivity
import ru.tinkoff.fintech.meowle.screens.kaspresso.KaspressoAddScreen
import ru.tinkoff.fintech.meowle.screens.kaspresso.element.NavigationBarElements
import ru.tinkoff.fintech.meowle.wiremock.WireMockHelper
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.verify

class KaspressoAddTest : TestCase() {

    @get:Rule
    val prefs = PreferenceRule()

    @get: Rule
    val mock = WireMockRule(5000)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    val navigationBar = NavigationBarElements()
    val addScreen = KaspressoAddScreen()

    val newCatName = "Барсик"

    @Test
    fun kaspressoAddCat() = run {
        stubFor(
            WireMock.post("/api/core/cats/add")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(WireMockHelper.fileToString("add_response.json"))
                )
        )
        navigationBar.clickAddTab()
        addScreen.enterName(newCatName)
        addScreen.clickAdd()

        verify(
            postRequestedFor(WireMock.urlPathMatching(".*/add"))
                .withRequestBody(equalToJson("{\n" +
                        "\t\"cats\": [{\n" +
                        "\t\t\"description\": \"\",\n" +
                        "\t\t\"gender\": \"unisex\",\n" +
                        "\t\t\"name\": \"Барсик\"\n" +
                        "\t}]\n" +
                        "}"))
        )
    }
}