package com.java.stream_test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        List<Dish> menu = initMenu();
        List<Dish> specialMenu = iniSpecialMenu();






        /*
         * 1. filter : 람다를 인수로 받아 스트림에서 스트렘에서 특정 요소를 제외
         * 2. map : 람다를 이용해서 한 요소를 다른 요소로 변환 혹은 정보를 추출
         * 3. limit : 정해진 개수만 저장되도록 스트림 크기를 축소
         * 4. collect : 스트림을 다른 형식으로 변환한다.
         */

        //1. 칼로리 300이상 음식 3개 출력.
        List<String> threeHighCalroricDishNames
                = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(dish -> dish.getName())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCalroricDishNames);


        //2. 채식요리 확인하기 (predicate filtering)
        List<Dish> vegiterianMenu
                = menu.stream()
                .filter(Dish::isVegiterian)
                .collect(Collectors.toList());

        System.out.println(vegiterianMenu);


        //3. 고유 요소 필터링하기(distinct)
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


        //4. 320칼로리 이하의 요리 선택하기

        /*
         * filter(java8) : 모든 요소를 탐색하며 참인 경우만 선별.
         * takeWhile(java9) : 탐색 도중 거짓인 경우, 이전 참 값들만 저장하고 반복 작업을 중단한다.
         * dropWhile(java9) : 처음으로 거짓이 되는 지점의 이전 값들을 모두 제외.
         */

        List<Dish> filteredMenu
                = specialMenu.stream()
                .filter(dish -> dish.getCalories() <= 320)
                .collect(Collectors.toList());

        System.out.println(filteredMenu);


        //5. 300칼로리 이상 넘는 요리를 처음 두 요리를 건너뛴 다음 나머지 요리를 반환
        List<Dish> dishesExceptFirstAndSecond
                = menu.stream()
                .filter(dish -> dish.getCalories() >= 300)
                .skip(2)
                .collect(Collectors.toList());

        System.out.println(dishesExceptFirstAndSecond);


        //6. 처음 등장하는 두 고리 요리를 필터링
        List<Dish> meatDishesIncludedFirstAndSecond
                = menu.stream()
                .filter(dish -> dish.getType() == Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(meatDishesIncludedFirstAndSecond);


        //7. 요리명 추출.
        List<String> dishNames
                = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(dishNames);

        //8. 요리명 길이 반환하기
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths
                = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(wordLengths);


        //9. 각 요리명의 길이를 알고 싶은 경우
        List<Integer> dishNameLengths
                = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(dishNameLengths);


        //10. 고유문자로 이루어진 리스트를 반환하기.

        /*
         * flatMap : 각 값을 다른 스트림으로 만든 다음에
         *               모든 스트림을 하나의 스트림으로 연결하는 기능을 수행.
         */
        List<String> uniqueCharacters
                = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueCharacters);


        //11. 각 숫자의 제곱근으로 이루어진 리스트를 반화하시오.
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares
                = numbers1.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println(squares);


        //12. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
        List<Integer> numbers2 = Arrays.asList(1, 2, 3);
        List<Integer> numbers3 = Arrays.asList(3, 4);
        List<int[]> pairs
                = numbers2.stream()
                .flatMap(i -> numbers3.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        for (int[] pair : pairs) {
            System.out.println(pair[0] + ":" + pair[1]);
        }

        //13.합이 3으로 나누어떨어지는 쌍만 반환하기
        List<int[]> pairs2
                = numbers2.stream()
                .flatMap(i -> numbers3.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());

        for (int[] pair : pairs2) {
            System.out.println(pair[0] + ":" + pair[1]);
        }





        /*
         * - 쇼트서킷
         *      1. 표현식에서 하나라도 거짓이라는 결과가 나오면 나머지 표현식의 결과와
         *         상관없이 전체 결과를 반환할 수 있다.
         *      2. 모든 스트림의 요소를 처리하지 않고도 결과를 반환할 수 있다.
         *      ex) allMatch, noneMatch, findFirst, findAny
         */

        //14. menu에 채식요리가 있는지 확인
        /*
         * anyMatch : 적어도 한 요소와일치하는지 확인할 때
         */
        if(menu.stream().anyMatch(Dish::isVegiterian)) {
            System.out.println("The menu is (somewwhat) vegiterian friendly!!");
        }

        //15. 모든 요리가 1000칼로리 이하면 건강식으로 간주하기
        /*
         * allMatch : 모든 요소가 주어진 프레디케이트와 일치
         * noneMatch : 주어진 프레디케이트와 일치하는 요소가 없는지 확인
         */
        boolean isHealthy
                = menu.stream()
                .allMatch(dish -> dish.getCalories() <= 1000);

        isHealthy
                = menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);

        //16. 채식 요리 중, 임의의 요리를 반환
        /*
         * findAny : 현재 스트림에서 임의의 요소를 반환.
         */
        Optional<Dish> dish
                = menu.stream()
                .filter(Dish::isVegiterian)
                .findAny();

        System.out.println(dish);
    }

    private static List<Dish> initMenu() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 400, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );
        return menu;
    }

    private static List<Dish> iniSpecialMenu() {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER)
        );
        return specialMenu;
    }
}
