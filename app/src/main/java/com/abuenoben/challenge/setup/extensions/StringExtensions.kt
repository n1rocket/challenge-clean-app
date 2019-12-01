package com.abuenoben.challenge.setup.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.regex.Pattern


private val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
    "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$",
    Pattern.CASE_INSENSITIVE
)

fun String.isEmail(): Boolean {
    val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this)
    return matcher.find()
}

fun String.isValidDNI(): Boolean {

    var esValido = false
    var i = 0
    var caracterASCII: Int
    val letra: Char
    val miDNI: Int
    val resto: Int
    val asignacionLetra = charArrayOf(
        'T',
        'R',
        'W',
        'A',
        'G',
        'M',
        'Y',
        'F',
        'P',
        'D',
        'X',
        'B',
        'N',
        'J',
        'Z',
        'S',
        'Q',
        'V',
        'H',
        'L',
        'C',
        'K',
        'E'
    )


    if (this.length == 9 && Character.isLetter(this[8])) {

        do {
            caracterASCII = this.codePointAt(i)
            esValido = caracterASCII > 47 && caracterASCII < 58
            i++
        } while (i < this.length - 1 && esValido)
    }

    if (esValido) {
        letra = Character.toUpperCase(this[8])
        miDNI = Integer.parseInt(this.substring(0, 8))
        resto = miDNI % 23
        esValido = letra == asignacionLetra[resto]
    }

    return esValido
}

fun String.openWebPage(context: Context) {
    val webpage = Uri.parse(this)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}