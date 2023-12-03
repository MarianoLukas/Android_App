package com.example.tecchstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tecchstore.db.AppDatabase
import com.example.tecchstore.db.model.car.CarEntity
import com.example.tecchstore.db.model.product.ProductEntity
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "code"

class DetailFragment : Fragment() {
    private val db by lazy { AppDatabase.getDatabase(requireContext()) }
    private var code: String? = null
    private lateinit var editTextCode: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var producto: ProductEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println(it)
            code = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        this.producto = db.productDao().getByCode(code!!)

        val imageView = root.findViewById<ImageView>(R.id.mainImageview)
        val materialButtonSave = root.findViewById<MaterialButton>(R.id.materialButton_save)
        val materialButtonAdd = root.findViewById<MaterialButton>(R.id.materialButton_add)
        this.editTextCode = root.findViewById<EditText>(R.id.editText_code)
        this.editTextDescription = root.findViewById<EditText>(R.id.editText_description)
        this.editTextPrice = root.findViewById<EditText>(R.id.editText_value)

        imageView.apply {
            this.setImageDrawable(AppCompatResources.getDrawable(requireContext(), producto.imgId))
        }

        editTextCode.setText(producto.code)
        editTextCode.addTextChangedListener {
            materialButtonSave.isEnabled = isDiferent()
        }




        editTextDescription.setText(producto.description)
        editTextDescription.addTextChangedListener{
            materialButtonSave.isEnabled = isDiferent()
        }

        editTextPrice.setText(producto.price.toString())
        editTextPrice.addTextChangedListener {
            materialButtonSave.isEnabled = isDiferent()
        }

        materialButtonSave.setOnClickListener {
            db.productDao().update(producto.apply {
                description = editTextDescription.text.toString()
                code = editTextCode.text.toString()
                price = editTextPrice.text.toString().toInt()
            })

            root.findNavController().navigateUp()
        }

        var isin: Boolean = false

        db.carDao().getAll().forEach {
            if (it.code == producto.code) isin = true
        }

        materialButtonAdd.isEnabled = !isin
        materialButtonAdd.setOnClickListener {
            db.carDao().upsert(CarEntity(code = producto.code))
            materialButtonAdd.isEnabled = false
        }


        return root
    }

    fun isDiferent(): Boolean = editTextCode.text.toString() != producto.code ||
                editTextPrice.text.toString() != producto.price.toString() ||
                editTextDescription.text.toString() != producto.description

    companion object {
        @JvmStatic
        fun newInstance(code: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, code)
                }
            }
    }
}