package com.example.coffeeshop.Repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeeshop.Domain.BannerModel
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.Domain.itemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        val liveData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let { list.add(it) }
                }
                liveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return liveData
    }


    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val liveData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                liveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return liveData
    }


    fun loadPopular(): LiveData<MutableList<itemsModel>> {
        val liveData = MutableLiveData<MutableList<itemsModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<itemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(itemsModel::class.java)
                    item?.let { list.add(it) }
                }
                liveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return liveData
    }


//    fun loadItemCategory(categoryId: String): LiveData<MutableList<itemsModel>> {
//
//        val itemsliveData = MutableLiveData<MutableList<itemsModel>>()
//        val ref = firebaseDatabase.getReference("Items").child(categoryId)
//        val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)
//        query.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val list = mutableListOf<itemsModel>()
//                for (childSnapshot in snapshot.children) {
//                    val item = childSnapshot.getValue(itemsModel::class.java)
//                    item?.let { list.add(it) }
//                }
//                itemsliveData.value = list
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//        return itemsliveData
//    }
fun loadItemCategory(categoryId: String): LiveData<MutableList<itemsModel>> {
    val itemsliveData = MutableLiveData<MutableList<itemsModel>>()
    val ref = firebaseDatabase.getReference("Items")
    val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)

    query.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = mutableListOf<itemsModel>()
            for (childSnapshot in snapshot.children) {
                val item = childSnapshot.getValue(itemsModel::class.java)
                item?.let { list.add(it) }
            }
            itemsliveData.value = list
        }

        override fun onCancelled(error: DatabaseError) {
            // handle error properly instead of TODO
        }
    })

    return itemsliveData
}

}




