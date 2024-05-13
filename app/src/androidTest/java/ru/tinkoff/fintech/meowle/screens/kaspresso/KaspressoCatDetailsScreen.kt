package ru.tinkoff.fintech.meowle.screens.kaspresso

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.qameta.allure.kotlin.Allure
import org.hamcrest.BaseDescription
import org.hamcrest.Matcher
import ru.tinkoff.fintech.meowle.R

class KaspressoCatDetailsScreen : KScreen<KaspressoCatDetailsScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val likeButton = KButton { withId(R.id.ib_like) }
    private val editButton = KButton { withId(R.id.btn_edit) }
    private val confirmButton = KButton { withId(R.id.confirm_button) }
    private val descriptionText = KTextView { withId(R.id.cat_description) }
    private val editDescriptionField = KTextInputLayout { withId(R.id.til_desc) }
    private val nameText = KTextView { withId(R.id.cat_name) }

    fun clickLike() {
        likeButton.click()
    }

    fun clickEdit() {
        editButton.click()
    }

    fun clickConfirm() {
        confirmButton.click()
    }

    fun enterDescription(description: String) {
        editDescriptionField.edit.replaceText(description)
    }

    fun checkDescription(description: String) {
        descriptionText.hasText(description)
    }

    fun checkName(name: String) {
        nameText.hasText(name)
    }
}
