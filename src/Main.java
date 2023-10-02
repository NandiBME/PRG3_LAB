// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Beer.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {
    public static ArrayList<Beer> beers = new ArrayList<>();
    protected static void Add(String[] input) throws IOException
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
    protected static void Search(String input)
    {
        for (Beer beer :
                beers) {
            if (beer.name.equals(input))
                System.out.println(beer);
        }
    }
    protected static void Find(String input)
    {
        for (Beer beer :
                beers) {
            if (beer.name.contains(input))
                System.out.println(beer);
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
                    if(length==4) try {
                        String[] arg = {temp[1], temp[2],temp[3]};
                        Add(arg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
                case "search":
                    Search(temp[1]);
                    break;
                case "find":
                    Find(temp[1]);
                    break;
                case "delete":
                    Delete(temp);
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }

    }
}