package com.ephirium.data.repository

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.text.toSpannable
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.repository.LoadChaptersRepositoryBase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.jsoup.Jsoup
import java.nio.charset.StandardCharsets


class LoadChaptersRepository : LoadChaptersRepositoryBase<PostDto> {
    private val htmlsReference = FirebaseStorage.getInstance().reference.child("html")
    override fun loadChapters(
        post: PostDto,
        onChangeListener: (value: List<SpannableString>) -> Unit,
        onExceptionListener: (exception: Exception) -> Unit
    ) {
        val postReference = htmlsReference.child(post.id)
        val chapters = ArrayList<SpannableString>()
        for (i in 0..post.chapters.size) {
            getHtml(postReference.child("$i.html"), {
                chapters.add(it)
            }, onExceptionListener)
        }
        onChangeListener(chapters)
    }

    private fun getHtml(
        storageReference: StorageReference,
        onChangeListener: (value: SpannableString) -> Unit,
        onExceptionListener: (exception: Exception) -> Unit
    ) {
        storageReference.getBytes(Long.MAX_VALUE).addOnCompleteListener {
            val bytes = it.result
            val htmlString = String(bytes, StandardCharsets.UTF_8)
            val document = Jsoup.parse(htmlString)
            val builder = SpannableStringBuilder(document.text())
            builder.setSpan(
                ForegroundColorSpan(-0xffff01),
                0,
                builder.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            onChangeListener(builder.toSpannable() as SpannableString)
        }.addOnFailureListener {
            onExceptionListener(it)
        }
    }
}