import java.util.*;
import java.util.List;

/**
 * Created by Luke on 4/29/2015.
 */
public class Alphabetizer {

    public static void main(String[] args) {
        String s;
        //use argument
        if(args.length >0 )
            s=args[0];
        else
        {
            //get user input
            System.out.println("enter a word");
            Scanner in = new Scanner(System.in);
            s = in.nextLine();
        }


        //output rank
        System.out.println(rank(s));
    }

    private static long rank(String input)
    {
        long rank;

        char[] charArray = input.toCharArray();

        Set<Character> uniqueSet = new HashSet<Character>();
        for (int i=0; i< charArray.length; i++)
            uniqueSet.add(charArray[i]);

        rank = getTotalPermutations(charArray, uniqueSet) + 1;
        return rank;
    }

    private static long getTotalPermutations(char[] charArray,Set<Character> uniqueSet )
    {
        long permutations = 0;

        List<Character> list = new ArrayList<Character>();
        list.addAll(uniqueSet);
        Collections.sort(list);
        Iterator it = list.iterator();

        while(it.hasNext()) {
            char next = (Character)it.next();
            if(next == charArray[0])
                break;

            int newCharArrayAmount = charArray.length-1;
            char[] getperms = new char[newCharArrayAmount];

            int occurrence = 0;

            for (int i = 0; i < charArray.length; i++)
            {
                if(charArray[i] == next && occurrence == 0)
                {
                    occurrence++;
                }
                else
                {
                    int index = i-occurrence;
                    getperms[index] = charArray[i];
                }
            }
            permutations = permutations + getPermutations(getperms);
        }
        char[] charArrayOneRemoved = Arrays.copyOfRange(charArray, 1,charArray.length);

        if(charArrayOneRemoved.length > 1)
        {
            Set<Character> newUniqueHS = new HashSet<Character>();

            for (int i=0; i< charArrayOneRemoved.length; i++)
                newUniqueHS.add(charArrayOneRemoved[i]);

            permutations = permutations + getTotalPermutations(charArrayOneRemoved, newUniqueHS);
        }
        return permutations;
    }


    private static long factorial(int number)
    {
        long factorial = number;
        for (int i = 1; i < number; i++)
        {
            factorial = factorial * (number - i);
            //System.out.println(factorial);
        }

        return factorial;
    }

    private static long getPermutations (char[] chars)
    {

        int total = chars.length;
        long totalFac = factorial(total);

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

        //System.out.println(total +":"+totalFac);

        for(int i = 0; i < total; i++)
        {
            char ch = chars[i];
            if(!hm.containsKey(ch))
                hm.put(ch, 1);
            else
                hm.replace(ch, hm.get(ch) + 1);
        }

        long denominator = 1;

        Iterator it =  hm.entrySet().iterator();
        while(it.hasNext())
        {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            int pairValue = (Integer)pair.getValue();
            if (pairValue > 1)
                denominator = denominator * factorial(pairValue);
        }

        return (totalFac/denominator);
    }


}

