package com.caesarCipher;
public class Main {

    public static void main(String[] args)
    {
        try
        {
            ArgsController argsHandler = new ArgsController(args);
            String mode = argsHandler.GetMode();
            Integer key = argsHandler.GetKey();
            String text = argsHandler.GetText();
            CaesarCipher cipherController = new CaesarCipher(mode, key, text);
            String result = cipherController.GetResult();
            System.out.println(result);
        }
        catch(Exception e)
        {
            System.out.println("Неверные параметры");
        }
    }


}
