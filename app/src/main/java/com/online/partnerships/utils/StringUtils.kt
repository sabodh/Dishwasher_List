package com.online.partnerships.utils

import com.online.partnerships.model.data.SliderItem


object StringUtils {

    fun getFormattedURL(url: String?):String{
        return String.format("%s%s","https:", url)
    }
    fun getFormattedText(htmlString: String):String{
        return htmlString.replace(Regex("<.*?>"), "")
    }

    fun convertToTitleCase(str: String): String {
        val words = str.toLowerCase().split(" ")
        val titleCaseWords = mutableListOf<String>()
        for (word in words) {
            if (word == "yes" || word == "no") {
                titleCaseWords.add(word.capitalize())
            } else {
                titleCaseWords.add(word.capitalize())
            }
        }
        return titleCaseWords.joinToString(" ")
    }
    fun getMediaURLs(urls : ArrayList<String>) : List<SliderItem>{
        return  urls.map { url -> SliderItem(getFormattedURL(url)) } as ArrayList<SliderItem>
    }
}