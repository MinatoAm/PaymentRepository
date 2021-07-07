package com.example.testapplication.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.api.response.ResponsePaymentsList
import java.text.SimpleDateFormat
import java.util.*

class PaymentAdapter: RecyclerView.Adapter<PaymentAdapter.PaymentHolder>(){

    private var payments: List<ResponsePaymentsList.ResponsePayments> = listOf()

    fun setPayments(payments: List<ResponsePaymentsList.ResponsePayments>) {
        this.payments = payments
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_item_row, parent, false)
        return PaymentHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val itemPayment = payments[position]
        holder.bindPayment(itemPayment.amount, itemPayment.currency, itemPayment.created, itemPayment.desc)
    }


    override fun getItemCount(): Int = payments.size

    inner class PaymentHolder(v: View) : RecyclerView.ViewHolder(v){

        private var view: View = v
        private var amount: Double? = null
        private var currency: String? = ""
        private var created: String? = null
        private var description: String? = ""
        private var tvAmount: TextView = view.findViewById(R.id.tv_payment_amount)
        private var tvCurrency: TextView = view.findViewById(R.id.tv_payment_currency)
        private var tvCreated: TextView = view.findViewById(R.id.tv_payment_created)
        private var tvDescription: TextView = view.findViewById(R.id.tv_payment_description)


        fun bindPayment(amount: Double?, currency: String?, created: Long?, description: String?) {
            this.amount = amount
            this.currency = currency
            this.created = convertLongToTime(created)
            this.description = description

            createView()
        }

        private fun createView() {

            tvAmount.text = this.amount.toString()
            tvCurrency.text = this.currency
            tvCreated.text = this.created
            tvDescription.text = this.description
        }
    }


    fun convertLongToTime(time: Long?): String {
        val date = time?.let { Date(it) }
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
}

