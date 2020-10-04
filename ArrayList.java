import java.util.ListIterator;
import java.util.Scanner;
import java.util.ArrayList;

class Queue
{
    private static ArrayList<String> queue = new ArrayList<String>();

    static void addInQueue(String name)
    {
        queue.add(name);
    }

    static String RemoveFromQueue()
    {
        int HEAD = 0;
        return queue.remove(HEAD);
    }

    static void displayList()
    {
        ListIterator<String> Iterator = queue.listIterator();
        while(Iterator.hasNext())
        {
            System.out.print(Iterator.next() + " -> ");
        }
    }

    public static void main (String [] arg)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Maintain Social Distancing");
        while(true)
        {
            System.out.println("\nEnter An Option");
            System.out.println("1] Insert   2] Remove   3] List All  4] Exit");
            int input = scan.nextInt();

            switch (input)
            {
                case 1:     System.out.println("Enter Your Name : ");
                            String name = scan.next();
                            addInQueue(name);
                            break;
                case 2:     System.out.println("Removed From Queue : " + RemoveFromQueue());
                            break;
                case 3:     System.out.println("Currently, in Queue : ");
                            displayList();
                            break;
                case 4:     System.exit(0);
                            break;
                default:    System.err.println("Invalid Option Selected");
            }
        }
    }
}