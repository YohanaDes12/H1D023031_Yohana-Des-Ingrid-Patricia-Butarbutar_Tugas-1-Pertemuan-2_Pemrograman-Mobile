package com.unsoed.informatikamobile.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unsoed.informatikamobile.R
import com.unsoed.informatikamobile.data.model.BookDoc
import com.unsoed.informatikamobile.databinding.FragmentBookDetailBinding

class BookDetailFragment(
    private val title: String,
    private val author: String,
    private val year: String,
    private val coverId: Int? = null
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        loadData()
    }

    private fun setupUI() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun loadData() {
        binding.tvBookTitle.text = title
        binding.tvAuthor.text = author
        binding.tvPublishYear.text = year

        // Map untuk beberapa buku dengan cover ID yang diketahui ada
        val knownCovers = mapOf(
            "Mastering Kotlin" to 10541840,
            "Kotlin Programming" to 12404606,
            "Kotlin Basics" to 8739978
        )

        val finalCoverId = coverId ?: knownCovers[title]

        finalCoverId?.let { id ->
            val imageUrl = "https://covers.openlibrary.org/b/id/$id-L.jpg"
            println("DEBUG: Loading cover with ID: $id, URL: $imageUrl")
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.book_not_found)
                .error(R.drawable.book_not_found)
                .into(binding.ivBookCover)
        } ?: run {
            println("DEBUG: No cover ID available for '$title', using default image")
            binding.ivBookCover.setImageResource(R.drawable.book_not_found)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_AUTHOR = "author"
        private const val ARG_YEAR = "year"
        private const val ARG_COVER_ID = "cover_id"

        fun newInstance(bookDoc: BookDoc): BookDetailFragment {
            return BookDetailFragment(
                title = bookDoc.title ?: "Unknown Title",
                author = bookDoc.authorName?.joinToString(", ") ?: "Unknown Author",
                year = bookDoc.firstPublishYear?.toString() ?: "Unknown Year",
                coverId = bookDoc.coverId
            )
        }
    }
}