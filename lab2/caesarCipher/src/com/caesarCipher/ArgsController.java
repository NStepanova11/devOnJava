package com.caesarCipher;

public class ArgsController
{
    private static final int ARGC = 3;
    private static final String ENCODE_MODE = "-e";
    private static final String DECODE_MODE = "-d";


    private String[] args;

    public ArgsController(String[] args)
    {
        this.args = args;
        try
        {
            if (this.args.length!=ARGC)
            {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException argcExeption)
        {
            System.out.println("Неверное количество параметров");
        }
    }

    public String GetMode()
    {
        String mode = this.args[0];
        if (!mode.equals(ENCODE_MODE) && !mode.equals(DECODE_MODE))
        {
            throw new IllegalArgumentException();
        }
        return mode;
    }

    public Integer GetKey()
    {
        Integer key = Integer.parseInt(this.args[1]);
        return key;
    }

    public String GetText()
    {
        String text = this.args[2];
        return text;
    }
}
