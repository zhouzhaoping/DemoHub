package com.zhouzhaoping.springdemo.service;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zhouzhaoping.springdemo.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhouzhaoping.springdemo.model.Artist;
import com.zhouzhaoping.springdemo.model.Track;

@Service
public class LambdaService {
    private static final Logger logger = LoggerFactory.getLogger(LambdaService.class);

    /**
     * Lambda表达式
     */
    public void Chapter2() {
        // 类型推断
        Predicate<Integer> atLeast5 = x -> x > 5;
        BinaryOperator<Long> addLongs = (x, y) -> x + y;
        atLeast5.test(4);
        addLongs.apply(1L,2L);
        return;
    }

    /**
     * 流
     */
    public void Chapter3() {
        List<Artist> allArtists = new ArrayList<>(asList(
                new Artist("good party", new ArrayList<>(asList("jack", "sb")), "peking"),
                new Artist("fuck party", new ArrayList<>(asList("moon", "shit")), "shanghai")));
        /**
         * 内部迭代 > 迭代器 > for循环
         * filter-惰性求值操作：返回stream，count-及早求值操作：返回另一个值或者空；与build模式类似
         */
        long count = allArtists.stream()
                .filter(artist -> artist.isFrom("peking"))
                .count();

        // 常用流操作：collect、map、flatmap（多个stream变成一个stream）；max、min、通用reduce
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        List<String> collected2 = Stream.of("a", "b", "hello").map(string -> string.toUpperCase()).collect(toList());
        List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1").filter(value -> isDigit(value.charAt(0)))
                .collect(toList());
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4)).flatMap(numbers -> numbers.stream())
                .collect(toList());
        List<Track> tracks = asList(new Track("Bakai", 524L),
                new Track("Violets for Your Furs", 378L),
                new Track("Time Was", 451L));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();
        int sum = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        return;
    }

    private int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }
    private List<String> artistAndOrigin(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.getName() + "+" + artist.getOrigin())
                .collect(Collectors.toList());
    }
    private List<Album> under3songAlbum(List<Album> albums) {
        return albums.stream()
                .filter(album -> album.getTracks().size() <= 3)
                .collect(toList());
    }
    private void Exercise3(List<Artist> artists, Album album) {
        int totalMembers = artists.stream()
                .map(artist -> artist.getMembers().size())
                .reduce(0, Integer::sum)
                .intValue();

        //及早boolean anyMatch(Predicate<? super T> predicate);
        //惰性Stream<T> limit(long maxSize);

        //高阶boolean anyMatch(Predicate<? super T> predicate);
        //低阶Stream<T> limit(long maxSize);

        AtomicInteger count = new AtomicInteger(0);
        List<String> origins = album.getMusicians().forEach(musician -> );
    }
    private static long lowercaseCount(String string) {
        return string.chars().filter(Character::isLowerCase).count();
    }
    private Optional<String> lowercaseMaxString(List<String> strings) {
        return strings.stream()
                .max(Comparator.comparing(LambdaService::lowercaseCount));
    }
    private static <I, O> Stream<O> map(Stream<I> in_stream, Function<I, O> mapper) {
        return in_stream.reduce(new ArrayList<O>(), (acc, x) -> {
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        }).stream();
    }
    private static <I> Stream<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial,
                (List<I> acc, I x) -> {
                    if (predicate.test(x)) {
                        List<I> newAcc = new ArrayList<>(acc);
                        newAcc.add(x);
                        return newAcc;
                    } else {
                        return acc;
                    }
                },
                LambdaService::combineLists).stream();
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        // We are copying left to new list to avoid mutating it.
        List<I> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }

    public void Test() {

    }
}
