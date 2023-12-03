package com.example.tecchstore

import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.navigation.findNavController
import com.example.tecchstore.db.AppDatabase
import com.example.tecchstore.db.model.product.ProductEntity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import org.w3c.dom.Text

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var aceptarButton: MaterialButton
    private lateinit var listarButton: MaterialButton

    private val db by lazy { AppDatabase.getDatabase(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        aceptarButton = root.findViewById(R.id.verCarritoButton)
        listarButton = root.findViewById(R.id.listarButton)

        aceptarButton.setOnClickListener {
            root.findNavController().navigate(R.id.action_mainFragment2_to_choppingCarFragment)
        }

        var productList = this.db.productDao().getAll()

        if (productList.isEmpty()) {
            val productEntity1 = ProductEntity(
                code = "2n3n4",
                description = "Gafas de realidad virtual",
                price = 1234,
                imgId = R.drawable.vr
            )
            val productEntity2 = ProductEntity(
                code = "3n3n3",
                description = "Drone mega pro",
                price = 1234,
                imgId = R.drawable.drone
            )
            val productEntity3 = ProductEntity(
                code = "4n4n4",
                description = "Audífonos para escuchar el bajo",
                price = 1234,
                imgId = R.drawable.sound
            )
            val productEntity4 = ProductEntity(
                code = "5n5n5",
                description = "Mc book super Apple",
                price = 1234,
                imgId = R.drawable.mcbook
            )

            this.db.productDao().insert(productEntity1)
            this.db.productDao().insert(productEntity2)
            this.db.productDao().insert(productEntity3)
            this.db.productDao().insert(productEntity4)

            productList = this.db.productDao().getAll()
        }

        root.findViewById<ImageView>(R.id.imageview)
            .setImageDrawable(AppCompatResources.getDrawable(requireContext(), productList[0].imgId))
        root.findViewById<TextView>(R.id.texto)
            .text = productList[0].description
        root.findViewById<MaterialCardView>(R.id.carView1)
            .setOnClickListener {root.findNavController().navigate(R.id.action_mainFragment2_to_detailFragment, Bundle().apply { putString("code", productList[0].code) })}


        root.findViewById<ImageView>(R.id.imageview2)
            .setImageDrawable(AppCompatResources.getDrawable(requireContext(), productList[1].imgId))
        root.findViewById<TextView>(R.id.texto2)
            .text = productList[1].description
        root.findViewById<MaterialCardView>(R.id.carView2)
            .setOnClickListener {root.findNavController().navigate(R.id.action_mainFragment2_to_detailFragment, Bundle().apply { putString("code", productList[1].code) })}

        root.findViewById<ImageView>(R.id.imageview3)
            .setImageDrawable(AppCompatResources.getDrawable(requireContext(), productList[2].imgId))
        root.findViewById<TextView>(R.id.texto3)
            .text = productList[2].description
        root.findViewById<MaterialCardView>(R.id.carView3)
            .setOnClickListener {root.findNavController().navigate(R.id.action_mainFragment2_to_detailFragment, Bundle().apply { putString("code", productList[2].code) })}

        root.findViewById<ImageView>(R.id.imageview4)
            .setImageDrawable(AppCompatResources.getDrawable(requireContext(), productList[3].imgId))
        root.findViewById<TextView>(R.id.texto4)
            .text = productList[3].description
        root.findViewById<MaterialCardView>(R.id.carView4)
            .setOnClickListener {root.findNavController().navigate(R.id.action_mainFragment2_to_detailFragment, Bundle().apply { putString("code", productList[3].code) })}

        /*val gridLayout: GridLayout = root.findViewById(R.id.mainFragmentGridLayout)

        productList.forEach {
            gridLayout.addView(
                (layoutInflater.inflate(R.layout.custom_card_layout, null) as MaterialCardView).apply {
                    this.findViewById<ImageView>(R.id.imageview)
                        .setImageDrawable(AppCompatResources.getDrawable(context, it.imgId))
                    this.findViewById<TextView>(R.id.textVew).text = it.description
                }
            )
        }*/

        return root
    }
}