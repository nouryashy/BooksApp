package io.android.projectx.remote.features.books.model.response

import com.google.gson.annotations.SerializedName

data class Formats (
    @SerializedName("application/x-mobipocket-ebook")
    val applicationXMobipocketEbook: String,

    @SerializedName("application/epub+zip")
    val applicationEpubZip: String,

    @SerializedName("text/html")
    val textHTML: String,

    @SerializedName("application/octet-stream")
    val applicationOctetStream: String? = null,

    @SerializedName("image/jpeg")
    val imageJPEG: String,

    @SerializedName("text/plain")
    val textPlain: String? = null,

    @SerializedName("text/plain; charset=us-ascii")
    val textPlainCharsetUsASCII: String? = null,

    @SerializedName("application/rdf+xml")
    val applicationRDFXML: String,

    @SerializedName("text/html; charset=utf-8")
    val textHTMLCharsetUTF8: String? = null,

    @SerializedName("text/plain; charset=utf-8")
    val textPlainCharsetUTF8: String? = null,

    @SerializedName("text/plain; charset=iso-8859-1")
    val textPlainCharsetISO88591: String? = null,

    @SerializedName("text/html; charset=iso-8859-1")
    val textHTMLCharsetISO88591: String? = null
)