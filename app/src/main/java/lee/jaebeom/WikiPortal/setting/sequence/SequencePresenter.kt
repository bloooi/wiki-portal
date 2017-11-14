package lee.jaebeom.WikiPortal.setting.sequence

import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by leejaebeom on 2017. 11. 14..
 */
class SequencePresenter(view: SequenceContract.View, private val helper: ItemTouchHelper) : SequenceContract.Presenter{
    override fun onStartDrag(viewHolder: SequenceRecyclerAdapter.ViewHolder) {
        helper.startDrag(viewHolder)
    }

    init {
        view._presenter(this)
    }
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}