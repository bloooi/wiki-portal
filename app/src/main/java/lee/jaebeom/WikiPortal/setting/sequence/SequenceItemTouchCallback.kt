package lee.jaebeom.WikiPortal.setting.sequence

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by leejaebeom on 2017. 11. 14..
 */
class SequenceItemTouchCallback(val itemMoveListener : OnItemMoveListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlag = ItemTouchHelper.UP  or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlag, 0)

    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        itemMoveListener.onItemMove(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface OnItemMoveListener{
        fun onItemMove(fromPosition: Int, toPosition: Int)
    }
}