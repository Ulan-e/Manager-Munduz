package com.ulan.munduz.manager.data.repository

import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ulan.munduz.manager.data.models.Picture
import com.ulan.app.munduz.helpers.*
import com.ulan.munduz.manager.data.models.SliderImage
import java.util.*
import javax.inject.Inject

class StorageImpl @Inject constructor(context: Context) : Storage {

    private var mContext: Context = context
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageRef: StorageReference = storage.reference
    private var mSliderImage =  SliderImage()

    override fun insertImage(filePath: Uri): Picture {
        val picture = Picture()
        val random = Random()
        val randomInt = random.nextInt(10000) + 1
        val storageRef: StorageReference = storageRef.child("images/$randomInt")
        val uploadTask: UploadTask = storageRef.putFile(filePath)
        uploadTask
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                storageRef.downloadUrl
            }
            .addOnSuccessListener {
            }
            .addOnFailureListener {
                showErrorLoadingImage(mContext, it)
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    picture.urlImage = task.result.toString()
                    showSuccessLoadingImage(mContext)
                } else {
                    showNotCompletedLoadingImage(mContext, task)
                }
            }
        return picture
    }

    override fun insertSliderImage(filePath: Uri) {
        val random = Random()
        val randomInt = random.nextInt(1000) + 1
        val storageRef: StorageReference = storageRef.child("sliderImages/$randomInt")
        val uploadTask: UploadTask = storageRef.putFile(filePath)
        uploadTask
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                storageRef.downloadUrl
            }
            .addOnSuccessListener {
            }
            .addOnFailureListener {
                showErrorLoadingImage(mContext, it)
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mSliderImage.image = task.result.toString()
                    showSuccessLoadingImage(mContext)
                } else {
                    showNotCompletedLoadingImage(mContext, task)
                }
            }
    }

    override fun getInsertedSliderImage(): SliderImage {
        return mSliderImage
    }

    override fun removeImage(picture: Picture) {
        val deletedFile = storageRef.child(picture.urlImage)
        deletedFile.delete().addOnSuccessListener(object : OnSuccessListener<Void> {
            override fun onSuccess(p0: Void?) {
                showImageRemoved(mContext)
            }
        }).addOnFailureListener(object : OnFailureListener {
            override fun onFailure(p0: Exception) {
                showImageRemovedFailure(mContext)
            }

        })
    }
}