package com.test.quizexampleinterview.model

/**
 * Created by macstudent on 2017-12-05.
 */
class Questions {
    var iD: Int
    var qUESTION: String
    var oPTA: String
    var oPTB: String
    var oPTC: String
    var oPTD: String
    var aNSWER: String
    var aNSWEREXPLANATION: String

    constructor() {
        iD = 0
        qUESTION = ""
        oPTA = ""
        oPTB = ""
        oPTC = ""
        oPTD = ""
        aNSWER = ""
        aNSWEREXPLANATION = ""
    }

    constructor(ID: Int, QUESTION: String, OPTA: String, OPTB: String, OPTC: String, OPTD: String, ANSWER: String, ANSWEREXPLANATION: String) {
        iD = ID
        qUESTION = QUESTION
        oPTA = OPTA
        oPTB = OPTB
        oPTC = OPTC
        oPTD = OPTD
        aNSWER = ANSWER
        aNSWEREXPLANATION = ANSWEREXPLANATION
    }
}