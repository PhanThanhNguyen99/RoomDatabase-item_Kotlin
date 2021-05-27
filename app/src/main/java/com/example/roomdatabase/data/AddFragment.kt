package com.example.roomdatabase.data

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
   private lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.btn_ok.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val name = edt_name.text.toString()
        val address = address_edt.text.toString()
        if (inputCheck(name,address)){
            val user :User = User(0,name,address)
            mUserViewModel.addUser(user)
//            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            edt_name.text  = null
            address_edt.text = null
            Toast.makeText(requireContext(),"Thêm thành công",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"That bai",Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(name:String , address: String):Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(address))
    }

}