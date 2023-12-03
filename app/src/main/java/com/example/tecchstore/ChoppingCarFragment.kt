package com.example.tecchstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import com.example.tecchstore.db.AppDatabase
import com.google.android.material.button.MaterialButton

class ChoppingCarFragment : Fragment() {
    private val db by lazy { AppDatabase.getDatabase(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_chopping_car, container, false)
        val linearLayout = root.findViewById<LinearLayout>(R.id.verticalLayout)

        db.carDao().getAll().forEach {
            db.productDao().getByCode(it.code)?.let {it2 ->
                linearLayout.addView(inflater.inflate(R.layout.custom_card_car_layout, linearLayout, false).apply {
                    this.findViewById<ImageView>(R.id.imageView_prod)
                        .setImageDrawable(AppCompatResources.getDrawable(requireContext(), it2.imgId))

                    this.findViewById<TextView>(R.id.textView_description)
                        .text = it2.description

                    this.findViewById<TextView>(R.id.textView_price)
                        .text = it2.price.toString()

                    this.setOnClickListener {
                        root.findNavController().navigate(R.id.action_choppingCarFragment_to_detailFragment, Bundle().apply { putString("code", it2.code) })
                    }
                })
            }
        }

        root.findViewById<MaterialButton>(R.id.materialButton_clean)
            .setOnClickListener {
                db.carDao().getAll().forEach {it3 -> db.carDao().delete(db.carDao().findById(it3.code)) }
                root.findNavController().navigateUp()
                root.findNavController().navigateUp()
                Toast.makeText(context, "Los elementos han sido borrados del carrito", Toast.LENGTH_LONG).show()
            }

        root.findViewById<MaterialButton>(R.id.materialButton_buy)
            .setOnClickListener {
                Toast.makeText(context, "Has comprado todos los productos", Toast.LENGTH_LONG).show()
            }

        return root
    }
}