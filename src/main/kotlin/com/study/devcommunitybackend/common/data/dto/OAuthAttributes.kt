package com.study.devcommunitybackend.common.data.dto

import lombok.Getter

@Getter
data class OAuthAttributes (
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String,
    val provider: String,
) {
    companion object {
        fun of (registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes? {
            when (registrationId) {
                "google" -> return ofGoogle(registrationId, userNameAttributeName, attributes)
                "naver" -> return ofNaver(registrationId, userNameAttributeName, attributes)
            }
            return null
        }

        fun ofGoogle(registrationId: String, userNameAttributeKey: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeKey,
                provider = registrationId)
        }

        fun ofNaver(registrationId: String, userNameAttributeKey: String, attributes: Map<String, Any>): OAuthAttributes {
            val response = attributes["response"] as Map<String, Any>

            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response["profile_image"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeKey,
                provider = registrationId)
        }
    }

    fun convertToMap(): Map<String, Any> {
        val map = HashMap<String, Any>()
        map["id"] = nameAttributeKey
        map["key"] = nameAttributeKey
        map["email"] = email
        map["name"] = name
        map["picture"] = picture
        map["provider"] = provider
        return map
    }
}



