package com.zhouzhaoping.springdemo.service;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // 常用流操作：collect、map、flatmap、max、min
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
    }

    public void Test() {

    }
}
