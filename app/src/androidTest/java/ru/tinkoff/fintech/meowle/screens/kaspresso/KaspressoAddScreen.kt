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

class KaspressoAddScreen : KScreen<KaspressoAddScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    private val addButton = KButton { withId(R.id.btn_add) }
    private val editNameField = KTextInputLayout { withId(R.id.til_name) }

    fun enterName(description: String) {
        editNameField.edit.replaceText(description)
    }

    fun clickAdd() {
        addButton.click()
    }
}