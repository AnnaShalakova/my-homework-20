package ru.tinkoff.fintech.meowle.screens.kaspresso

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.qameta.allure.kotlin.Allure
import org.hamcrest.Matcher
import ru.tinkoff.fintech.meowle.R

/**
 * @author Ruslan Ganeev
 */
class KaspressoRatingScreen : KScreen<KaspressoRatingScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val dislikeTab = onView(withContentDescription(R.string.rating_tab_dislikes_title))
    private val catsRating = KRecyclerView(
        builder = { withId(R.id.rv_cats_list) },
        itemTypeBuilder = { itemType(::CatCardd) }
    )

    fun clickCatInRating(position: Int) {
        catsRating.childAt<CatCardd>(position) {
            this.click()
        }
    }

    fun clickDislikeTab() {
        dislikeTab.perform(click())
    }
}

private class CatCardd(matcher: Matcher<View>) : KRecyclerItem<CatCardd>(matcher) {
    val catName = KTextView(matcher) { withId(R.id.cat_name) }
}