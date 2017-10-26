
package at.yeoman.mutabor.functional;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Currying
{
    public static <T,U,R> Function<U,R> apply(BiFunction<T,U,R> original, T firstArgument)
    {
        return secondArgument -> original.apply(firstArgument, secondArgument);
    }
    
    public static <T,U,R> Function<T,R> applyLast(BiFunction<T,U,R> original, U secondArgument)
    {
        return firstArgument -> original.apply(firstArgument, secondArgument);
    }
    
    public static <T,U> Consumer<U> apply(BiConsumer<T,U> original, T firstArgument)
    {
        return secondArgument -> original.accept(firstArgument, secondArgument);
    }
    
    public static <T,U> Consumer<T> applyLast(BiConsumer<T,U> original, U secondArgument)
    {
        return firstArgument -> original.accept(firstArgument, secondArgument);
    }
}
