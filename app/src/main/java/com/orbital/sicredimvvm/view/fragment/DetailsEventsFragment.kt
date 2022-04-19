package com.orbital.sicredimvvm.view.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.orbital.core.enums.ExtrasEnum
import com.orbital.core.enums.FormatEnums
import com.orbital.core.enums.Status
import com.orbital.core.utils.FormatUtils
import com.orbital.sicredimvvm.R
import com.orbital.sicredimvvm.databinding.CustomDialogCheckinBinding
import com.orbital.sicredimvvm.databinding.FragmentDetailsBinding
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsEventsFragment:Fragment() {


    private val viewModel by viewModel<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var bindingCustom: CustomDialogCheckinBinding
    private lateinit var dialog: Dialog

    private var data: EventsData?=null
    private var price:String=""
    private var desc:String=""
    private var date:String=""
    private var title:String=""

    companion object{
        @JvmStatic
        fun newInstance(data: EventsData) = DetailsEventsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ExtrasEnum.EVENT.value,data)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        data = arguments?.getParcelable(ExtrasEnum.EVENT.value)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return getBindingView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponents()
    }

    private fun getBindingView():View{
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        bindingCustom = CustomDialogCheckinBinding.inflate(layoutInflater)

        return binding.root
    }
    private fun initComponents(){
        setImage()
        setExtras()
        setTexts()
        setDialog()
        clickComponents()
    }
    private fun setExtras(){
        title = data?.title.toString()
        desc = data?.description.toString()
        price = FormatEnums.CODEREAL.value + FormatUtils.formataMoeda(data?.price)
        date = data?.let { FormatUtils.timeToDate(it.data.toString(),FormatEnums.FORMATDATABRASIL.value) }
            .toString()
    }
    private fun setCheckInObserver(name:String,email:String,eventId:String){
        viewModel.checkIn(name, email, eventId)
        viewModel.resultLiveData.observe(viewLifecycleOwner){
            when (it.status) {
                Status.SUCESS -> sucess()
                Status.ERROR -> error(it.mensage.toString())
                Status.ERRORNETWORK -> errorNetwork()
            }
        }
    }
    private fun compartilharEvento(){

        val intent = Intent(Intent.ACTION_SEND)
        val msg = gerarMsg()


        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.title_share))
        intent.putExtra(Intent.EXTRA_TEXT,msg)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,getString(R.string.app_name)))

    }
    private fun gerarMsg():String{
        return title+
                "\n"+
                "\n"+ getString(R.string.dia_share)+" "+date+
                "\n"+
                "\n"+ getString(R.string.price_share)+" "+price+
                "\n"+
                "\n"+ getString(R.string.final_share)
    }


    private fun setImage(){
        binding.apply {
            Glide.with(this@DetailsEventsFragment)
                .load(data?.image)
                .centerCrop()
                .error(R.drawable.ic_img_not_found)
                .into(ivFundo)
        }
    }
    private fun setTexts(){
        binding.apply {
            tvDesc.text = desc
            tvPrice.text = price
            tvCalendar.text = date
        }
    }
    private fun setDialog(){
        dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingCustom.root)
        clickDialog()
    }
    private fun clickDialog(){
        bindingCustom.apply {
            btnCheck.setOnClickListener {
                val name    =   etNome.text.toString()
                val email   =   etEmail.text.toString()
                if(name.isNotEmpty()){
                    if(FormatUtils.isValidateEmail(email)){
                        data?.id?.let { it1 -> setCheckInObserver(name,email, it1) }
                    }else{
                        Toast.makeText(context,R.string.error_email,Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context,R.string.error_nome, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun clickComponents(){
        binding.apply {
            btCheck.setOnClickListener {
                dialog.show()
            }
            btShare.setOnClickListener {
                compartilharEvento()
            }
        }
    }
    private fun error(msg:String){
        Log.e(javaClass.name,msg)
        Toast.makeText(context,getString(R.string.check_error),Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }
    private fun errorNetwork(){
        Log.e(javaClass.name,getString(R.string.error_connection))
        Toast.makeText(context,getString(R.string.error_connection),Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }
    private fun sucess(){
        Toast.makeText(context,getString(R.string.check_sucess),Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }
}