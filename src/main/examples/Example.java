package examples;

import com.bukalapak.cache.Callable;
import com.bukalapak.cache.HotCache;

public class Example {

    public static void main(String[] args) {

        HotCache<String> cache = new HotCache<String>(4);
        cache.write("one", "satu");
        cache.write("two", "dua");
        cache.write("three", "tiga");
        System.out.println(cache.inspect());
        cache.write("four", "empat");
        cache.write("five", "lima");
        System.out.println(cache.inspect());
        System.out.println(cache.read("two"));
        System.out.println(cache.inspect());
        cache.write("six", "enam");
        System.out.println(cache.inspect());
        cache.fetch("seven", new Callable() {
            @Override
            public Object call() {
                System.out.println("Run the block");
                return "tujuh";
            }
        });
        System.out.println(cache.inspect());
        String val = cache.fetch("six", new Callable() {
            @Override
            public Object call() {
                System.out.println("Run the block");
                return "tujuh";
            }
        });
        System.out.println(val);
        System.out.println(cache.inspect());
        cache.delete("seven");
        System.out.println(cache.inspect());

        final String name = "xinuc";
        System.out.println(cache.inspect());
        cache.fetch("name", new Callable() {
            @Override
            public Object call() {
                System.out.println("Run the block");
                return name;
            }
        });

        System.out.println(cache.inspect());

    }

}