package com.example.roomdatabase.data

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import kotlinx.android.synthetic.main.fragment_updatefragment.*
import kotlinx.android.synthetic.main.fragment_updatefragment.view.*

class Updatefragment : Fragment() {
  private val args by navArgs<UpdatefragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_updatefragment, container, false)

          mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.updata_edt_name.setText(args.currerUser.name)
        view.update_address_edt.setText(args.currerUser.address)

        view.btn_update.setOnClickListener {
          updateData()
        }
         setHasOptionsMenu(true)
          return view
    }

  private fun updateData() {
    val name = updata_edt_name.text.toString()
    val address = update_address_edt.text.toString()
        if(inputCheck(name,address)) {
          val updateUser = User(args.currerUser.id, name, address)
          mUserViewModel.updateUser(updateUser)
          Toast.makeText(requireContext(),"Thành công ",Toast.LENGTH_SHORT).show()
        }
    else{
      Toast.makeText(requireContext(),"That bai",Toast.LENGTH_SHORT).show()
    }
  }
  private fun inputCheck(name :String, address :String) : Boolean{
     return  !(TextUtils.isEmpty(name) || TextUtils.isEmpty(address))
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu,menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(item.itemId == R.id.delete_menu){
      deleteUser()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun deleteUser() {
      val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("Yes"){_,_ ->
        mUserViewModel.deleteUser(args.currerUser)
      Toast.makeText(requireContext(),"Delete",Toast.LENGTH_LONG).show()
      findNavController().navigate(R.id.action_updatefragment_to_listFragment)
//      updata_edt_name.text = null
//      update_address_edt.text = null
    }
    builder.setNegativeButton("No"){_,_ ->}
    builder.setTitle("Delete ${args.currerUser.name} ?")
    builder.setMessage("Are you want  to delete ${args.currerUser.name} ?")
    builder.create().show()

  }
}