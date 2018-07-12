package com.digital_pusauli.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.digital_pusauli.R
import com.digital_pusauli.view.login_upload.RegisterShop.AllUploadListActivity
import com.tapadoo.alerter.Alerter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


object Utils {

    private const val isCheckLog: Boolean = true


    /*Animation Right To Left */
    fun leftToRight(cnt: AppCompatActivity) {
        cnt.overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    fun rightToLeft(cnt: AppCompatActivity) {
        cnt.overridePendingTransition(R.anim.left_to_right_silder, R.anim.right_to_left__silder);
    }

    //Custom Snackbar
    fun showSnackBar(context: Context, message: String, color: Int): Snackbar {
        val sb = Snackbar.make((context as Activity).findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_LONG)
        sb.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        val textView = sb.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(color)
        sb.show()
        return sb
    }

    //show Toast
    fun Context.toast(mess: String) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    //show Log
    fun log(tag: String, mess: String) {
        if (isCheckLog) {
            Log.d(tag, mess)
        } else Log.d(tag, "Disable Log...")
    }
}


//Extension method to load imageView from url.
fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun Alerters(cnt: AppCompatActivity, mess: String) {

    Alerter.create(cnt)
            .setTitle(mess)
            .enableProgress(true)
            .setBackgroundColorRes(R.color.test1)
            .setProgressColorRes(R.color.colorAccent)
            .show()
}


    @SuppressLint("SetTextI18n")
    fun showPopUp(context: AppCompatActivity, s: String) {
    val openDialog = Dialog(context)
    openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    openDialog.setContentView(R.layout.booking_confirmation_dialog)
    openDialog.setTitle("Custom Dialog Box")
    openDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val btnAgree = openDialog.findViewById<Button>(R.id.btn_done)
    val txt_booking_id = openDialog.findViewById<TextView>(R.id.txt_booking_id)
    txt_booking_id.text = "# ${s.toUpperCase()}"
    btnAgree.setOnClickListener {
        openDialog.dismiss()
         context.startActivity(Intent(context, AllUploadListActivity::class.java))
    }
    openDialog.show()
   }


  fun timeStamp(timestamp:String):String{
      val cal = Calendar.getInstance(Locale.ENGLISH)
      cal.timeInMillis = timestamp.toLong() * 1000L
     // return  DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
      return  DateFormat.format("dd/MM/yyyy", cal).toString()
  }



//   val ed = edit.text.toString()

//   val bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.nk)

//   img.setImageBitmap(bitmap.resize(1000, 1200))


//       Log.d("TAGS", "  :  " + bitmap.toBase64())


/*
            if(ed.equalsIgnoreCase("kaju"))
            Toast.makeText(applicationContext,"Right ",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext,"Not Right ",Toast.LENGTH_SHORT).show()
*/


//  img.loadFromUrl("https://s33.postimg.cc/lite9hzlb/IMG_20180606_104125_094.jpg")


//   img.setImageBitmap(edit.getBitmap())


//   edit.setBackgroundColor(this.getColors(R.color.colorAccent))


/**
 * Extension method to get a view as bitmap.
 */
fun View.getBitmap(): Bitmap {
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    draw(canvas)
    canvas.save()
    return bmp
}


fun Context.getColors(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Context.getDrawables(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)


fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}


fun Context.sms(phone: String?, body: String = "") {
    val smsToUri = Uri.parse("smsto:$phone")
    val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
    intent.putExtra("sms_body", body)
    startActivity(intent)
}

fun Context.dial(tel: String?) {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")))
}


/**
 * Extension method to share for Context.
 */
fun Context.share(text: String, subject: String = ""): Boolean {
    val intent = Intent()
    intent.type = "text/plain"
    intent.putExtra(EXTRA_SUBJECT, subject)
    intent.putExtra(EXTRA_TEXT, text)
    try {
        startActivity(createChooser(intent, null))
        return true
    } catch (e: ActivityNotFoundException) {
        return false
    }
}


//Extension method to check if String is Email.
fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}


//Extension method to check if String is Number.
fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}


//Extension method to check String equalsIgnoreCase
fun String.equalsIgnoreCase(other: String) = this.toLowerCase().contentEquals(other.toLowerCase())


//  Extension method to get base64 string for Bitmap.
fun Bitmap.toBase64(): String {
    var result = ""
    val baos = ByteArrayOutputStream()
    try {
        compress(Bitmap.CompressFormat.JPEG, 100, baos)
        baos.flush()
        baos.close()
        val bitmapBytes = baos.toByteArray()
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            baos.flush()
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}

/**
 * Extension method to resize Bitmap to specified height and width.
 */
fun Bitmap.resize(w: Number, h: Number): Bitmap {
    val width = width
    val height = height
    val scaleWidth = w.toFloat() / width
    val scaleHeight = h.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    if (width > 0 && height > 0) {
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}


/**
 * Extension method to save Bitmap to specified file path.
 */
fun Bitmap.saveFile(path: String) {
    val f = File(path)
    if (!f.exists()) {
        f.createNewFile()
    }
    val stream = FileOutputStream(f)
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.flush()
    stream.close()
}


