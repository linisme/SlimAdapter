package net.idik.lib.slimadapterlib_kotlin

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.idik.lib.slimadapter.AbstractSlimAdapter
import net.idik.lib.slimadapter.SlimViewHolder
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import java.util.*
import kotlin.reflect.KClass

/**
 * Created by linshuaibin on 10/02/2017.
 */

class KAbstractSlimTypesAdapter private constructor() : AbstractSlimAdapter() {

    var datas: List<Any>? = null

    fun setDatas(datas: List<Any>): KAbstractSlimTypesAdapter {
        this.datas = datas
        return this
    }

    public override fun getItem(position: Int): Any {
        return datas!![position]
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    private val dataClasses = ArrayList<KClass<out Any>>()

    private val creatorMap = HashMap <KClass<*>, (ViewGroup) -> SlimTypeViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlimViewHolder<Any> {
        val dataClass = dataClasses[viewType]
        return creatorMap[dataClass]!!(parent)
    }

    fun with(dataClass: KClass<*>, itemLayoutRes: Int, onBind: ((Any, IViewInjector<out IViewInjector<*>>) -> Unit)? = null): KAbstractSlimTypesAdapter {
        creatorMap.put(dataClass, { parent -> SlimTypeViewHolder(parent, itemLayoutRes, onBind) })
        return this
    }

    fun attachTo(recyclerView: RecyclerView): KAbstractSlimTypesAdapter {
        recyclerView.adapter = this
        return this
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        var index = dataClasses.indexOf(item.javaClass.kotlin)
        if (index == -1) {
            dataClasses.add(item.javaClass.kotlin)
        }
        index = dataClasses.indexOf(item.javaClass.kotlin)
        return index
    }

    class SlimTypeViewHolder constructor(parent: ViewGroup, itemLayoutRes: Int, val bindFun: ((Any, IViewInjector<out IViewInjector<*>>) -> Unit)?) : SlimViewHolder<Any>(parent, itemLayoutRes) {

        override fun onBind(data: Any, injector: IViewInjector<out IViewInjector<*>>) {
            bindFun?.invoke(data, injector)
        }

    }

    companion object {

        fun create(): KAbstractSlimTypesAdapter {
            val adapter = KAbstractSlimTypesAdapter()
            return adapter
        }
    }

}
