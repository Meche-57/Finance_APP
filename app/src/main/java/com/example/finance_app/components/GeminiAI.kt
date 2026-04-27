package com.example.finance_app.components

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend

object GeminiAI {


    // Initialize the Gemini Developer API backend service
// Create a `GenerativeModel` instance with a model that supports your use case
   val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-3-flash-preview")

    suspend fun askGemini(prompt: String): String {
        return try {
        val response = model.generateContent(prompt)
        return response.text ?: "No response from Gemini AI"
    }
    catch (e:Exception){ "Error: ${e.message}" }
    }


}