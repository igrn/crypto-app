package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;
import com.javarush.zelenin.ui.console.Message;

public class ExitController {

    public Result exitApplication() {
        return new Result(Code.EXIT, Message.SELECTED_MODE[0]);
    }
}
