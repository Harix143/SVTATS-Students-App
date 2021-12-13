package com.example.login_screen

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and


class Fee_Payment_2 : AppCompatActivity() {
    var postData = ""
    private val mWebView: WebView? = null

    private val Jazz_MerchantID = "MC29964"
    private val Jazz_Password = "vz8t76b4tz"
    private val Jazz_IntegritySalt = "853tyyve5w"

    var paymentReturnUrl = "http://localhost/order.php"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fee_payment2)

//
//        val intentData = intent
//        val fees = intentData.getStringExtra("fees")
//
//        val Date = Date()
//        val dateFormat = SimpleDateFormat("yyyyMMddkkmmss")
//        val DateString: String = dateFormat.format(Date)
//        println("AhmadLogs: DateString : $DateString")
//
//        // Convert Date to Calendar
//
//        // Convert Date to Calendar
//        val c: Calendar = Calendar.getInstance()
//        c.setTime(Date)
//        c.add(Calendar.HOUR, 1)
//
//        // Convert calendar back to Date
//
//        // Convert calendar back to Date
//        val currentDateHourPlusOne: Date = c.getTime()
//        val expiryDateString: String = dateFormat.format(currentDateHourPlusOne)
//        println("AhmadLogs: expiryDateString : $expiryDateString")
//
//        val TransactionIdString = "T$DateString"
//        println("AhmadLogs: TransactionIdString : $TransactionIdString")
//
//        val pp_MerchantID = Jazz_MerchantID
//        val pp_Password = Jazz_Password
//        val IntegritySalt = Jazz_IntegritySalt
//        val pp_ReturnURL = paymentReturnUrl
//        val pp_Amount: String = fees
//        val pp_TxnDateTime = DateString
//        val pp_TxnExpiryDateTime = expiryDateString
//        val pp_TxnRefNo = TransactionIdString
//        val pp_Version = "1.1"
//        val pp_TxnType = ""
//        val pp_Language = "EN"
//        val pp_SubMerchantID = ""
//        val pp_BankID = "TBANK"
//        val pp_ProductID = "RETL"
//        val pp_TxnCurrency = "PKR"
//        val pp_BillReference = "billRef"
//        val pp_Description = "Description of transaction"
//        var pp_SecureHash = ""
//        val pp_mpf_1 = "1"
//        val pp_mpf_2 = "2"
//        val pp_mpf_3 = "3"
//        val pp_mpf_4 = "4"
//        val pp_mpf_5 = "5"
//
//
//        var sortedString = ""
//        sortedString += "$IntegritySalt&"
//        sortedString += "$pp_Amount&"
//        sortedString += "$pp_BankID&"
//        sortedString += "$pp_BillReference&"
//        sortedString += "$pp_Description&"
//        sortedString += "$pp_Language&"
//        sortedString += "$pp_MerchantID&"
//        sortedString += "$pp_Password&"
//        sortedString += "$pp_ProductID&"
//        sortedString += "$pp_ReturnURL&"
//        //sortedString += pp_SubMerchantID + "&";
//        //sortedString += pp_SubMerchantID + "&";
//        sortedString += "$pp_TxnCurrency&"
//        sortedString += "$pp_TxnDateTime&"
//        sortedString += "$pp_TxnExpiryDateTime&"
//        //sortedString += pp_TxnType + "&";
//        //sortedString += pp_TxnType + "&";
//        sortedString += "$pp_TxnRefNo&"
//        sortedString += "$pp_Version&"
//        sortedString += "$pp_mpf_1&"
//        sortedString += "$pp_mpf_2&"
//        sortedString += "$pp_mpf_3&"
//        sortedString += "$pp_mpf_4&"
//        sortedString += pp_mpf_5
//
//        pp_SecureHash = php_hash_hmac(sortedString, IntegritySalt)
//        println("AhmadLogs: sortedString : $sortedString")
//        println("AhmadLogs: pp_SecureHash : $pp_SecureHash")
//
//        try {
//            postData += URLEncoder.encode("pp_Version", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_Version, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_TxnType", "UTF-8")
//                .toString() + "=" + pp_TxnType + "&"
//            postData += URLEncoder.encode("pp_Language", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_Language, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_MerchantID", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_MerchantID, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_SubMerchantID", "UTF-8")
//                .toString() + "=" + pp_SubMerchantID + "&"
//            postData += URLEncoder.encode("pp_Password", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_Password, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_BankID", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_BankID, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_ProductID", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_ProductID, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_TxnRefNo", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_TxnRefNo, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_Amount", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_Amount, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_TxnCurrency", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_TxnCurrency, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_TxnDateTime", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_TxnDateTime, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_BillReference", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_BillReference, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_Description", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_Description, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_TxnExpiryDateTime", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_TxnExpiryDateTime, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_ReturnURL", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_ReturnURL, "UTF-8") + "&"
//            postData += URLEncoder.encode("pp_SecureHash", "UTF-8")
//                .toString() + "=" + pp_SecureHash + "&"
//            postData += URLEncoder.encode("ppmpf_1", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_mpf_1, "UTF-8") + "&"
//            postData += URLEncoder.encode("ppmpf_2", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_mpf_2, "UTF-8") + "&"
//            postData += URLEncoder.encode("ppmpf_3", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_mpf_3, "UTF-8") + "&"
//            postData += URLEncoder.encode("ppmpf_4", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_mpf_4, "UTF-8") + "&"
//            postData += URLEncoder.encode("ppmpf_5", "UTF-8")
//                .toString() + "=" + URLEncoder.encode(pp_mpf_5, "UTF-8")
//        } catch (e: UnsupportedEncodingException) {
//            e.printStackTrace()
//        }
//
//        println("AhmadLogs: postData : $postData")
//
//        mWebView!!.postUrl(
//            "https://sandbox.jazzcash.com.pk/CustomerPortal/transactionmanagement/merchantform/",
//            postData.toByteArray()
//        )

    }

//    private class MyWebViewClient : WebViewClient() {
//        private val jsCode = "" + "function parseForm(form){" +
//                "var values='';" +
//                "for(var i=0 ; i< form.elements.length; i++){" +
//                "   values+=form.elements[i].name+'='+form.elements[i].value+'&'" +
//                "}" +
//                "var url=form.action;" +
//                "console.log('parse form fired');" +
//                "window.FORMOUT.processFormData(url,values);" +
//                "   }" +
//                "for(var i=0 ; i< document.forms.length ; i++){" +
//                "   parseForm(document.forms[i]);" +
//                "};"
//
//        //private static final String DEBUG_TAG = "CustomWebClient";
//        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//            if (url == "http://localhost/order.php" ) {
//                println("AhmadLogs: return url cancelling")
//                view.stopLoading()
//                return
//            }
//            super.onPageStarted(view, url, favicon)
//        }
//
//        override fun onPageFinished(view: WebView, url: String) {
//            //Log.d(DEBUG_TAG, "Url: "+url);
//            if (url == "http://localhost/order.php") {
//                return
//            }
//            view.loadUrl("javascript:(function() { $jsCode})()")
//            super.onPageFinished(view, url)
//        }
//    }
//
//    private class FormDataInterface {
//        @JavascriptInterface
//        fun processFormData(url: String, formData: String) {
//            val i = Intent(this@Fee_Payment_2, DashBoard::class.java)
//            println("AhmadLogs: Url:$url form data $formData")
//            if (url == "http://localhost/order.php") {
//                val values = formData.split("&").toTypedArray()
//                for (pair in values) {
//                    val nameValue = pair.split("=").toTypedArray()
//                    if (nameValue.size == 2) {
//                        println("AhmadLogs: Name:" + nameValue[0] + " value:" + nameValue[1])
//                        i.putExtra(nameValue[0], nameValue[1])
//                    }
//                }
//                setResult(RESULT_OK, i)
//                finish()
//                return
//            }
//        }
//    }
//
//    fun php_hash_hmac(data: String, secret: String): String? {
//        var returnString: String? = ""
//        try {
//            val sha256_HMAC: Mac = Mac.getInstance("HmacSHA256")
//            val secret_key = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
//            sha256_HMAC.init(secret_key)
//            val res: ByteArray = sha256_HMAC.doFinal(data.toByteArray())
//            returnString = bytesToHex(res)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return returnString
//    }
//
//    fun bytesToHex(bytes: ByteArray): String? {
//        val hexArray = "0123456789abcdef".toCharArray()
//        val hexChars = CharArray(bytes.size * 2)
//        var j = 0
//        var v: Int
//        while (j < bytes.size) {
//            v = bytes[j] and 0xFF
//            hexChars[j * 2] = hexArray[v ushr 4]
//            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
//            j++
//        }
//        return String(hexChars)
//    }
}