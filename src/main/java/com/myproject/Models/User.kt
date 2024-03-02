package com.myproject.Models

class User {
    var image:String?=null
    var Name:String?=null
    var email:String?=null
    var password:String?=null
    constructor()
    constructor(Name: String?, email: String?, password: String?) {
        this.Name = Name
        this.email = email
        this.password = password
    }

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }


}