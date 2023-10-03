// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Beer.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {
    public static ArrayList<Beer> beers = new ArrayList<>();
    protected static void Add(String[] input)
    {
        beers.add(new Beer(input[0],input[1],Double.parseDouble(input[2])));
    }

    protected static void List(String[] input)
    {
        if(beers.isEmpty())
            return;
        Comparator<Beer> comparator = MultiComparator(input);
        if(comparator !=null) {
            Collections.sort(beers,comparator);
            for (Beer beer :
                    beers) {
                System.out.println(beer.toString());
            }
        }else {
            for (Beer beer :
                    beers) {
                System.out.println(beer.toString());
            }
        }
    }
    protected static Comparator<Beer> MultiComparator(String[] input)
    {
        ArrayList<Comparator<Beer>> comparators = new ArrayList<>();
        for (int i =1; i<input.length;i++)
        {
            switch (input[i])
            {
                case "name":
                    comparators.add(new NameComparator());
                    break;
                case"style":
                    comparators.add((new StyleComparator()));
                    break;
                case "strength":
                    comparators.add(new StrengthComparator());
                    break;
            }
        }
        return comparators.stream().reduce(Comparator::thenComparing).orElse(null);
    }
    protected static void Save() throws IOException {
        File file = new File("saves.txt");
        FileWriter writer = new FileWriter(file);
        for (Beer beer :
                beers) {
            writer.write(beer.toString()+"\n");
        }
        writer.close();
    }
    protected static void Load() throws IOException {
        beers.clear();
        File file = new File("saves.txt");
        Scanner reader = new Scanner(file);
        while(reader.hasNextLine())
        {
            String input=reader.nextLine();
            String[] temp = input.split(" ");
            Add(temp);
        }
        reader.close();

    }
    protected static void Search(String[] input)
    {
        if(input.length==2) {
            for (Beer beer :
                    beers) {
                if (beer.name.equals(input[1]))
                    System.out.println(beer);
            }
        }
        else{
            if(input[1].equals("name"))
            {
                for (Beer beer :
                        beers) {
                    if (beer.name.equals(input[2]))
                        System.out.println(beer);
                }
            }
            if(input[1].equals("style"))
            {
                for (Beer beer :
                        beers) {
                    if (beer.style.equals(input[2]))
                        System.out.println(beer);
                }
            }
            if(input[1].equals("strength"))
            {
                for (Beer beer :
                        beers) {
                    if (beer.getStrength()==Double.parseDouble(input[2]))
                        System.out.println(beer);
                }
            }
        }
    }
    protected static void Find(String[] input)
    {
        if(input.length==2)
        for (Beer beer :
                beers) {
            if (beer.name.contains(input[1]))
                System.out.println(beer);
        }
        else{
            if(input[1].equals("name"))
            for (Beer beer :
                    beers) {
                if (beer.name.contains(input[2]))
                    System.out.println(beer);
            }
            else if (input[1].equals("style")) {
                for (Beer beer :
                        beers) {
                    if (beer.style.contains(input[2]))
                        System.out.println(beer);
                }
            } else if (input[1].equals("strength")) {
                for (Beer beer :
                        beers) {
                    if (beer.getStrength()>=Double.parseDouble(input[2]))
                        System.out.println(beer);
                }
                
            } else if (input[1].equals("weaker")) {
                for (Beer beer :
                        beers) {
                    if (beer.getStrength()<Double.parseDouble(input[2]))
                        System.out.println(beer);
                }
            }
        }
    }
    protected static void Delete(String[] input)
    {
        Iterator<Beer> iterator = beers.iterator();
        while(iterator.hasNext())
        {
            Beer beer = iterator.next();
            if(beer.name.equals(input[1]))
            {
                iterator.remove();
            }
        }
    }
    protected static void Test()
    {
        PQueue<String> q = new PQueue();
        q.push("alma");
        q.push("körte");
        q.push("narancs");
        q.push("barack");
        try {
            System.out.println("Push: alma, körte, narancs, barack");
            System.out.println("Hossz: "+ q.size());
            System.out.println("Pop: "+ q.pop());
            System.out.println("Top: "+ q.top());
            System.out.println("Hossz: "+ q.size());
            q.clear();
            System.out.println("Clear: ");
            System.out.println("Hossz: "+ q.size());
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
        PQueue<Integer> s = new PQueue<Integer>();
        s.push(1); s.push(2); s.push(3); s.push(4);
        for (Integer i : s) System.out.println(i);

    }

    public static void main(String[] args) throws IOException {
        Scanner be= new Scanner(System.in);

        while (true)
        {
            String input=be.nextLine();
            String[] temp = input.split(" ");
            int length = temp.length;
            switch (temp[0]){
                case "exit":
                    System.exit(0);
                case "add":
                    if(length==4) {
                        String[] arg = {temp[1], temp[2], temp[3]};
                        Add(arg);
                    }
                    else
                        System.out.println("Wrong input\n\tadd {name} {style} {strength}");
                    break;
                case "list":
                    List(temp);
                    break;
                case "load":
                    Load();
                    break;
                case "save":
                    try{Save();} catch (IOException e){System.out.println("Error");}
                    break;
                case "search"://megegyezik a paraméterrel temp[1]
                    Search(temp);
                    break;
                case "find"://Szerepel benne a paraméter temp[1]
                    Find(temp);
                    break;
                case "delete":
                    Delete(temp);
                    break;
                case "test":
                    Test();
                    break;

                default:
                    System.out.println("Wrong input");
            }
        }

    }
}