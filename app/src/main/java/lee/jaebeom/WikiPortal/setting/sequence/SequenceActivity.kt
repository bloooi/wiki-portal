package lee.jaebeom.WikiPortal.setting.sequence

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.helper.ItemTouchHelper
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.databinding.ActivitySequenceBinding

/**
 * Created by leejaebeom on 2017. 11. 13..
 */
class SequenceActivity : AppCompatActivity(){
    lateinit var presenter: SequenceContract.Presenter
    lateinit var binding : ActivitySequenceBinding
    lateinit var helper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sequence)

        binding.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_action_navigation_arrow_back_inverted)
        binding.toolbar.title = getString(R.string.pref_wiki_sequence_title)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener { finish() }
        val adapter = SequenceRecyclerAdapter(this)
        binding.recyclerView.adapter = adapter
        helper = ItemTouchHelper(SequenceItemTouchCallback(adapter))
        helper.attachToRecyclerView(binding.recyclerView)

        presenter = SequencePresenter(adapter, helper)
    }
}
