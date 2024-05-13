package ru.tinkoff.fintech.meowle.screens.kaspresso.element

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import ru.tinkoff.fintech.meowle.R

class NavigationBarElements : KScreen<NavigationBarElements>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val tabRatingButton = KButton { withId(R.id.tab_btn_rating) }
    private val tabAddButton = KButton { withId(R.id.tab_btn_add) }

    fun clickRatingTab() {
        tabRatingButton.click()
    }

    fun clickAddTab() {
        tabAddButton.click()
    }
}