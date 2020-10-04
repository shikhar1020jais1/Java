import java.util.Scanner;

class NoMatchingDigitException extends Exception
{
    public NoMatchingDigitException(String character)
    {
        super("There is no digit on telephone that corresponds to " + character);
    }
}

class InvalidCharacterException extends Exception
{
    public InvalidCharacterException(String character)
    {
        super(String.format("%s is not recognised by the telephone, only Uppercase letters are allowed", character));
    }
}

class Telephone660
{
    static int getDigit660(String character)
    {
        int digit;
        switch (character)
        {
            case "A" : case "B" : case "C" : digit = 2; break;
            case "D" : case "E" : case "F" : digit = 3; break;
            case "G" : case "H" : case "I" : digit = 4; break;
            case "J" : case "K" : case "L" : digit = 5; break;
            case "M" : case "N" : case "O" : digit = 6; break;
            case "P" : case "R" : case "S" : digit = 7; break;
            case "T" : case "U" : case "V" : digit = 8; break;
            case "W" : case "X" : case "Y" : digit = 9; break;
            default: throw new IllegalStateException("Unexpected value: " + character);
        }
        return digit;
    }
    public static void main (String [] arg)
    {
        Scanner scan = new Scanner(System.in);

        while (true)
        {
            System.out.println("\nEnter a single letter, and I will tell the corresponding digit on telephone" +
                    "\nTo exit press 0");
            String character = scan.next();

            if(!(character.length() > 1)) {
                if (character.equals('0')) System.exit(0);
                try {
                    if (Character.isUpperCase(character.charAt(0))) {
                        if (character.equals("Q") || character.equals("Z")) {
                            throw new NoMatchingDigitException(character);
                        } else {
                            System.out.println("The digit " + getDigit660(character) + " corresponds to " +
                                    character + " on telephone");
                        }
                    } else {
                        throw new InvalidCharacterException(character);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Enter only a single character.");
            }
        }
    }
}
