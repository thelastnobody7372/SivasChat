package com.thelastnobody.SivasChat;

public enum ProcessCodes {
    Reg(0),
    log(1),
    RL(2),
    unknown(3);

    private int ProcessCodes;

    private ProcessCodes(int status){
        this.ProcessCodes = status;
    }
}