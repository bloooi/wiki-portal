package lee.jaebeom.WikiPortal.setting.sequence

import lee.jaebeom.WikiPortal.BasePresenter
import lee.jaebeom.WikiPortal.BaseView

/**
 * Created by leejaebeom on 2017. 11. 14..
 */
interface SequenceContract {
    interface View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter{
        fun onStartDrag(viewHolder: SequenceRecyclerAdapter.ViewHolder)
    }
}