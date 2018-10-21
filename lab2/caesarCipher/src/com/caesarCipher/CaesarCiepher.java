package com.caesarCipher;

import java.util.ArrayList;

class CaesarCipher
{
    private static final int LETTERS_COUNT = 26;
    private static final String ENCODE_MODE = "-e";
    private static final String DECODE_MODE = "-d";
    private static final String ERROR_LETTER_MSG = "В алфавите нет символа: ";

    private String cipherMode;
    private Integer cipherKey;
    private String cipherText, cipherResultText;

    private ArrayList<Character> uppers = new ArrayList<>();
    private ArrayList<Character> lowers = new ArrayList<>();

    public CaesarCipher(String mode, int key, String text)
    {
        this.cipherMode=mode;
        this.cipherKey=key;
        this.cipherText=text;
    }

    private void initializeLetters()
    {
        int firstLower = (int)'a';
        int firstUpper = (int)'A';

        for (int i=0; i<LETTERS_COUNT; i++)
        {
            lowers.add((char)(firstLower + i));
            uppers.add((char)(firstUpper + i));
            //System.out.println(((char)(firstLower+i))+" "+((char)(firstUpper+i)));
        }
    }

    public String GetResult()
    {
        this.initializeLetters();
        if (this.cipherMode.equals(ENCODE_MODE))
        {
            this.cipherResultText = EncodingCipher(this.cipherText, this.cipherKey);
        }
        else if (this.cipherMode.equals(DECODE_MODE))
        {
            this.cipherResultText = DecodingCipher(this.cipherText, this.cipherKey);
        }
        return(this.cipherResultText);
    }

    private String EncodingCipher(String text, int key) {
        char resultSymbol = ' ';
        String resultText = "";

        //System.out.println("in encoding   " + text + " " + key);

        for (int i = 0; i < text.length(); i++)
        {
            char symbol = text.charAt(i);

            if (this.uppers.contains(symbol))
            {
                resultSymbol = this.GetEncodeUpperLetter(symbol, key);
            }
            else if (this.lowers.contains((text.charAt(i))))
            {
                resultSymbol = this.GetEncodeLowerLetter(symbol, key);
            }
            else
            {
                System.out.println(ERROR_LETTER_MSG + symbol);
                throw new IllegalArgumentException();
            }
            resultText += resultSymbol;
        }
        return resultText;
    }

    private Character GetEncodeUpperLetter(char symbol, int key)
    {
        int indexOfCode, d, next;

        int indexOfSymbol = uppers.indexOf(symbol);
        if(indexOfSymbol+key >= uppers.size())
        {
            d = uppers.size() - indexOfSymbol;
            next = key - d;
            indexOfCode = uppers.indexOf((char)((int)'A' + next));
        }
        else
        {
            indexOfCode = indexOfSymbol+key;
        }
        char encodeUpperSymbol = uppers.get(indexOfCode);

        return encodeUpperSymbol;
    }

    private Character GetEncodeLowerLetter(char symbol, int key)
    {
        int indexOfCode, d, next;

        int indexOfSymbol = lowers.indexOf(symbol);
        if(indexOfSymbol+key >= lowers.size())
        {
            d = lowers.size() - indexOfSymbol;
            next = key - d;
            indexOfCode = lowers.indexOf((char)((int)'a' + next));
        }
        else
        {
            indexOfCode=indexOfSymbol+key;
        }

        char encodeLowerSymbol = lowers.get(indexOfCode);
        //System.out.println(encodeLowerSymbol);

        return encodeLowerSymbol;
    }

    private String DecodingCipher(String text, int key)
    {
        char resultSymbol=' ';
        String resultText="";

        for (int i = 0; i < text.length(); i++)
        {
            char symbol = text.charAt(i);

            if (this.uppers.contains(symbol))
            {
                resultSymbol = this.GetDecodeUpperLetter(symbol, key);
            }
            else if (this.lowers.contains((text.charAt(i))))
            {
                resultSymbol = this.GetDecodeLowerLetter(symbol, key);
            }
            else
            {
                System.out.println(ERROR_LETTER_MSG + symbol);
                throw new IllegalArgumentException();
            }
            resultText += resultSymbol;
        }
        return resultText;
    }

    private Character GetDecodeUpperLetter(char symbol, int key)
    {
        int indexOfCode, d, next;

        int indexOfSymbol = uppers.indexOf(symbol);
        if(indexOfSymbol - key < 0)
        {
            d = indexOfSymbol - 0;
            next = key - d;
            indexOfCode = uppers.indexOf((char)((int)'Z' - next));
        }
        else
        {
            indexOfCode = indexOfSymbol - key;
        }
        char decodeUpperSymbol = uppers.get(indexOfCode);
        //System.out.println(decodeUpperSymbol);

        return decodeUpperSymbol;
    }

    private Character GetDecodeLowerLetter(char symbol, int key)
    {
        int indexOfCode, d, next;

        int indexOfSymbol = lowers.indexOf(symbol);
        if(indexOfSymbol - key <0)
        {
            d = indexOfSymbol - 0;
            next = key - d;

            indexOfCode = lowers.indexOf((char)((int)'z' - next));
            System.out.println(indexOfCode);
        }
        else
        {
            indexOfCode = indexOfSymbol - key;
        }
        char decodeLowerSymbol = lowers.get(indexOfCode);

        return decodeLowerSymbol;
    }
}