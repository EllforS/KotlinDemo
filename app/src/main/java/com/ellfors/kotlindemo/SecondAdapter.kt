package com.ellfors.kotlindemo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import com.ellfors.kotlindemo.base.recyclerview.BaseRecyclerAdapter
import com.ellfors.kotlindemo.base.recyclerview.BaseRecyclerData
import com.ellfors.kotlindemo.base.recyclerview.BaseRecyclerViewHolder
import com.ellfors.kotlindemo.http.model.response.FuliResponse

/**
 * SecondAdapter
 * 2018/7/3 15:44
 */
class SecondAdapter constructor(mContext: Context, items: MutableList<BaseRecyclerData>?) : BaseRecyclerAdapter(mContext, items)
{
    companion object
    {
        const val TYPE_ITEM = 1
        const val TYPE_OTHER = 2
    }

    override fun onCreate(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder?
    {
        return when (viewType)
        {
            TYPE_ITEM ->
            {
                val viewHolder = ItemViewHolder(getItemView(R.layout.item_second, parent), this@SecondAdapter)
                viewHolder.setOnClickListener(viewHolder.img)
                viewHolder
            }
            else -> null
        }
    }

    override fun onBind(holder: BaseRecyclerViewHolder, position: Int)
    {
        when (holder.itemViewType)
        {
            TYPE_ITEM ->
            {
                val itemHolder = holder as ItemViewHolder
                val bean: FuliResponse = items?.get(position)?.data as FuliResponse
                itemHolder.img.displayImg(bean.url)
            }
        }
    }

    class ItemViewHolder constructor(itemView: View, adapter: BaseRecyclerAdapter) : BaseRecyclerViewHolder(itemView, adapter)
    {
        @BindView(R.id.iv_item_sencond)
        lateinit var img: ImageView
    }

}