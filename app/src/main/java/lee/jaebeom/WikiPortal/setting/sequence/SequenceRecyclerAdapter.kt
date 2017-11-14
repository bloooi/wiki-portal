package lee.jaebeom.WikiPortal.setting.sequence

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import lee.jaebeom.WikiPortal.BR
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.databinding.ItemSequenceBinding
import lee.jaebeom.WikiPortal.util.SharedPreference
import lee.jaebeom.WikiPortal.wiki.WikiData
import lee.jaebeom.WikiPortal.wiki.WikiData.Companion.useWikis
import lee.jaebeom.WikiPortal.wiki.WikiModel
import java.util.*

/**
 * Created by leejaebeom on 2017. 11. 14..
 */
class SequenceRecyclerAdapter(val context: Context) :RecyclerView.Adapter<SequenceRecyclerAdapter.ViewHolder>(), SequenceItemTouchCallback.OnItemMoveListener, SequenceContract.View{
    lateinit var presenter:SequenceContract.Presenter
    override fun _presenter(presenter: SequenceContract.Presenter) {
        this.presenter = presenter
    }
    init {
        WikiData.sortSequence()
    }
    override fun onItemMove(fromPosition: Int, toPosition: Int){
        Collections.swap(useWikis,fromPosition, toPosition)
        for (i in 0 until useWikis.size){
            useWikis.get(i).sequence = i+1
            WikiData.wikiURL.get(useWikis.get(i).name)!!.sequence = useWikis.get(i).sequence
            SharedPreference.savePreference("${SharedPreference.keySequence}_${useWikis.get(i).name}", useWikis.get(i).sequence, context)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(val binding: ItemSequenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: WikiModel){
            binding.setVariable(BR.wikiModel, model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<ItemSequenceBinding>(inflater, viewType,  parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: WikiModel = useWikis.get(position)
        holder?.bind(model)
        holder?.binding?.dragPoint?.setOnTouchListener(View.OnTouchListener { p0, p1 ->
            if (p1?.action == MotionEvent.ACTION_DOWN){
                presenter.onStartDrag(holder)
            }
            false
        })
    }

    override fun getItemCount(): Int {
        return useWikis.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_sequence
    }
}