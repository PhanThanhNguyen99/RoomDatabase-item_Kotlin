package com.example.roomdatabase.data

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() ,SearchView.OnQueryTextListener {
    private lateinit var  mUserViewModel: UserViewModel
    private var  apdapter = ListAdapter()
  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view :View =inflater.inflate(R.layout.fragment_list, container, false)
      

    mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
      apdapter.setData(user)
    })
      val  recycler = view.rcv_lisr
      recycler.adapter = apdapter
      recycler.layoutManager = LinearLayoutManager(requireContext())


      view.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
      }
       setHasOptionsMenu(true)
      return view
    }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu,menu)
    val search = menu.findItem(R.id.delete_menu)
    val  searchView :SearchView = search.actionView as SearchView
    searchView.isSubmitButtonEnabled = true
    searchView.setOnQueryTextListener(this)

  }




  override fun onQueryTextSubmit(query: String?): Boolean {
    if(query != null){
      searchUser(query)
    }
    return false
  }

   override fun onQueryTextChange(query: String?): Boolean {
    if(query != null){
      searchUser(query)
    }
    return true
  }
  private fun searchUser(query: String) {
    val searchQuery = "%$query%"
    mUserViewModel.search(searchQuery).observe(this, {
        apdapter.setData(it)

    })
  }
}