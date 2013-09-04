
import java.io.*

//File theWordFile = new File ("ValidWordList.txt")
//File theWordsScoredFile = new File("WordsScored.txt")

reader = System.in.newReader()

def scoreWord(word){
    score = 0
    word.each{
        if(it in ['a','e','i','o','u']){
            score +=1
        } else if(it in ['z','x','v','q','w']) {
            score +=10
        } else {
            score +=2
        }
    }
    return score
}

def makeTheWordsScoredFile(inFile, outFile){
    outFile.withWriter { out ->
        inFile.eachLine{ key ->
            out.writeLine("${key}:${scoreWord(key)}")
        }
    }
}

def getKeyValuePair(line, theMap){
    toMap = line.split(":")
    theMap[toMap[0]] = toMap[1].toInteger()
}

def makeTheWordScoreMap(inFile){
    wordScoreMap = [:]
    inFile.eachLine{
        getKeyValuePair(it, wordScoreMap)
    }
    return wordScoreMap
}

def checkWord() {
    word = null
    tempWord = null
    wordScore = 0
    while (tempWord != "STOP") {
        print  'input: '
        tempWord = reader.readLine()
        if(tempWord != "STOP"){
            word = tempWord
            println "Your single word score would be " + wordScoreMap[word].toString() + " STOP to continue"
        }
    }
    println word
    return word
}

def distCalc(word) {
    myMove = []
    xStart = 0
    xEnd = 0
    yStart = 0
    yEnd = 0
    direction = 'x'

    print  'input xStart: '
    xStart = reader.readLine().toInteger()
    print  'input yStart: '
    yStart = reader.readLine().toInteger()
    print "input 'x' for horizontal or 'y' for vertical: "
    direction = reader.readLine()

    xDiff = xEnd - xStart
    yDiff = yEnd - yStart

    /*TODO Check if word too long for board*/

    if (direction == 'x') {
        for(x in 0..word.size()-1){
            myMove <<  (x + xStart).toString() + yStart.toString()
        }
    }else{
        for(y in 0..(word.size()-1)){
            myMove << xStart.toString() + (y+ yStart).toString()
        }
    }
return myMove
}


def playWord(word,move){
    letter = 0
    for(i in move){
        theBoard[i] = word[letter]
        letter ++
    }
println theBoard
}

theBoard = ["11":" ","12":" ","13":" ","21":" ","22":" ","23":" ","31":" ","32":" ","33":" "]


def mainGame(){
    //File theWordFile = new File ("ValidWordList.txt")
    File theWordFile = new File("words.txt")
    File theWordsScoredFile = new File("WordsScored.txt")

    reader = System.in.newReader()

    //really just just need to run this once
    makeTheWordsScoredFile(theWordFile,theWordsScoredFile)

    wordScoreMap = makeTheWordScoreMap(theWordsScoredFile)

    //println wordScoreMap.keySet()
    word = checkWord()
    myMove = distCalc(word)
    playWord(word,myMove)

}

mainGame()

