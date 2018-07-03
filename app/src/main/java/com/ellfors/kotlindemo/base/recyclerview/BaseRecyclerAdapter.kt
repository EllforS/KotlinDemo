package com.ellfors.kotlindemo.base.recyclerview

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.ellfors.kotlindemo.extension.ImageExt
import com.ellfors.kotlindemo.extension.ToastExt
import com.ellfors.kotlindemo.extension.ValueExt
import java.util.*

/**
 * RecyclerView适配器基类
 */
abstract class BaseRecyclerAdapter
(var mContext: Context, var items: MutableList<BaseRecyclerData>?) :
        RecyclerView.Adapter<BaseRecyclerViewHolder>(), ImageExt, ValueExt, ToastExt
{
    protected var mInflater: LayoutInflater? = null
    var mListener: OnItemClickListener? = null
    var mLongListener: OnItemLongClickListener? = null
    protected var textChange: OnTextWatcher? = null
    protected var mSpanList: List<BaseRecyclerTypeSpanCount>? = null
    //默认权重
    private var defWeight = 1

    /**
     * ********************* 抽象方法与接口 *************************************
     */
    protected fun init()
    {
    }

    protected abstract fun onCreate(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder?

    protected abstract fun onBind(holder: BaseRecyclerViewHolder, position: Int)

    interface OnItemClickListener
    {
        fun onItemClick(view: View, position: Int, bean: BaseRecyclerData)
    }

    interface OnItemLongClickListener
    {
        fun onItemLongClick(view: View, position: Int, bean: BaseRecyclerData)
    }

    interface OnTextWatcher
    {
        fun afterTextChanged(etView: EditText, position: Int, s: String)
    }

    init
    {
        mInflater = LayoutInflater.from(mContext)
        init()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder
    {
        return onCreate(parent, viewType)!!
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int)
    {
        onBind(holder, position)
    }

    override fun getItemCount(): Int
    {
        return if (items == null) 0 else items!!.size
    }

    override fun getItemViewType(position: Int): Int
    {
        if (null != items && items!!.size > position)
        {
            val obj = items!![position]
            return obj.type
        }
        return 0
    }

    /* GridLayoutManager 权重设置 */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager)
        {
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup()
            {
                override fun getSpanSize(position: Int): Int
                {
                    if (mSpanList != null && items!!.size > 0)
                    {
                        for (bean in mSpanList!!)
                        {
                            if (getItemViewType(position) == bean.type)
                                return bean.spanCount!!
                        }
                    }
                    return defWeight
                }
            }
        }
    }

    /**
     * ******************************** 外部暴露的方法 **************************************
     */
    /* 获取ItemLayout的View实例 */
    protected fun getItemView(layoutId: Int, parent: ViewGroup): View
    {
        return mInflater!!.inflate(layoutId, parent, false)
    }

    /* 设置默认grid的权重 */
    fun setDefGridWeight(defWeight: Int)
    {
        this.defWeight = defWeight
    }

    /* 获取指定指针的数据 */
    fun getData(position: Int): BaseRecyclerData?
    {
        return if (null == items)
        {
            null
        }
        else items!![position]
    }

    /* 初始化数据 */
    fun setData(msg: MutableList<BaseRecyclerData>)
    {
        this.items = msg
        notifyDataSetChanged()
    }

    /* 添加数据 */
    fun addData(msg: List<BaseRecyclerData>?)
    {
        if (msg != null && msg.size > 0)
        {
            items!!.addAll(msg)
            notifyDataSetChanged()
        }
    }

    /* 设置权重占比数据 */
    fun setSpanCountList(list: Array<BaseRecyclerTypeSpanCount>?)
    {
        if (list != null && list.size > 0)
            this.mSpanList = Arrays.asList(*list)
    }

    /* 设置权重占比数据 */
    fun setSpanCountList(list: List<BaseRecyclerTypeSpanCount>?)
    {
        if (list != null && list.size > 0)
            this.mSpanList = list
    }

    /* 设置Item点击监听 */
    fun setOnItemClickListener(listener: OnItemClickListener?)
    {
        if (listener != null)
            this.mListener = listener
    }

    /* 设置Item长按监听 */
    fun setOnItemLongClickListener(listener: OnItemLongClickListener?)
    {
        if (listener != null)
            this.mLongListener = listener
    }

    /* 根据编辑状态设置回调 */
    protected inner class BaseRecyclerTextWatcher(private val etView: EditText, private val viewHolder: BaseRecyclerViewHolder) : TextWatcher
    {
        private var position: Int = 0

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
        {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
        {

        }

        override fun afterTextChanged(s: Editable)
        {
            position = viewHolder.adapterPosition
            if (position == RecyclerView.NO_POSITION)
                position = viewHolder.position
            if (position != RecyclerView.NO_POSITION)
                textChange!!.afterTextChanged(etView, position, s.toString().trim { it <= ' ' })
        }
    }

}
