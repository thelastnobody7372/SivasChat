package com.thelastnobody.SivasChat;

public enum Adminstration {
    Visitor(new StringBuffer("Visitor")),
    Member(new StringBuffer("Member")),
    Admin(new StringBuffer("Admin"));

    private StringBuffer Adminstration;

    private Adminstration(StringBuffer buffer){
        this.Adminstration = buffer;
    }
}
