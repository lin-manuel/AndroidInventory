package com.were.myfirstapp.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.were.myfirstapp.model.Product
import com.were.myfirstapp.navigation.ROUTE_DASHBOARD
import com.were.myfirstapp.navigation.ROUTE_VIEW_PRODUCTS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream

class ProductViewModel: ViewModel(){
    val cloudinaryurl="https://api.cloudinary.com/v1_1/dpb55u4fy/image/upload"
    val uploadPreset="app_image"

    private val _products = mutableStateListOf<Product>()
    val products: List<Product> = _products

    fun uploadProduct(imageUri: Uri?, productname: String, productcategory: String,
                      productprice: String, productquantity: String, context:
                      Context, navController: NavController){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val uploadedImageUrl = imageUri?.let { uploadtocloudinary(context, it) }
                val ref = FirebaseDatabase.getInstance().getReference("Products").push()
                val productdata = mapOf(
                    "id" to ref.key,
                    "productname" to productname,
                    "productcategory" to productcategory,
                    "productprice" to productprice,
                    "productquantity" to productquantity,
                    "imageurl" to uploadedImageUrl
                )
                ref.setValue(productdata).await()
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Product saved successful",
                        Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_PRODUCTS)
                }

            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Failed to save",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun uploadtocloudinary(context: Context, uri: Uri): String{
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileByte = inputStream?.readBytes() ?: throw Exception("Image failed to read")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file","image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(),
                    fileByte))
            .addFormDataPart("upload_preset",uploadPreset).build()

        val request = Request.Builder().url(cloudinaryurl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image url")
    }

    fun fetchProducts(context: Context){
        val ref = FirebaseDatabase.getInstance().getReference("Products")
        ref.get().addOnSuccessListener { snapshot ->
            _products.clear()
            for(child in snapshot.children){
                val product = child.getValue(Product::class.java)
                product?.let {
                    it.project_id = child.key
                    _products.add(it)
                }
            }
        }.addOnFailureListener{
            Toast.makeText(context,"Failed to load products",Toast.LENGTH_LONG).show()
        }
    }

    fun fetchProductById(productId: String, onResult: (Product?) -> Unit) {
        val ref = FirebaseDatabase.getInstance().getReference("Products").child(productId)
        ref.get().addOnSuccessListener { snapshot ->
            val product = snapshot.getValue(Product::class.java)
            product?.project_id = snapshot.key
            onResult(product)
        }.addOnFailureListener {
            onResult(null)
        }
    }

    fun deleteProduct(productId: String, context: Context){
        val ref = FirebaseDatabase.getInstance()
            .getReference("Products").child(productId)
        ref.removeValue().addOnSuccessListener {
            _products.removeAll{ it.project_id == productId }
            Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Product not deleted", Toast.LENGTH_LONG).show()
        }
    }

    fun updateProduct(productId: String,
                      imageUri: Uri?,
                      productname: String,
                      productcategory: String,
                      productquantity: String,
                      productprice: String,
                      context: Context,
                      navController: NavController){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val uploadedImageUrl = imageUri?.let { uploadtocloudinary(context, it) }
                val updateData = mutableMapOf<String, Any?>(
                    "id" to productId,
                    "productname" to productname,
                    "productcategory" to productcategory,
                    "productprice" to productprice,
                    "productquantity" to productquantity
                )
                if (uploadedImageUrl != null) {
                    updateData["imageurl"] = uploadedImageUrl
                }
                
                val ref = FirebaseDatabase.getInstance()
                    .getReference("Products").child(productId)
                ref.updateChildren(updateData).await()
                fetchProducts(context)
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Product updated successfully",
                        Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_DASHBOARD)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Update failed",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
