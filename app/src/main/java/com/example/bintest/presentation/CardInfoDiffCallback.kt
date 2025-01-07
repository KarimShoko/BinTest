package com.example.bintest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.bintest.domain.CardInfo

//класс колбек,показывает как сравнивать объекты(в скобках элемены,которые будем сравнивать)
object CardInfoDiffCallback : DiffUtil.ItemCallback<CardInfo>() {

    //сравнивает сами объекты(чтобы адаптер понял,работает с одним и тем же объектом или нет)
    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.binNumber == newItem.binNumber
    }
    //сравнивает поля объектов(чтобы узнать,нужно ли перерисовывать конкретный какой то элемент)
    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        //data классы,у них equals переопределен(будет сравнение по  всем полям в первичном конструкторе)
        return oldItem==newItem
    }
}