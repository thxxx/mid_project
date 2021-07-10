package com.example.study;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    List<Actor> leadingActors;

    List<Actor> supportActors;

    public String title;

    public int showingAge;

    public String genre;

    public static class Actor {
        //본명
        public String RealName;
        //예명
        public String StageName;
        //나이
        public int age;

        //성별 - 남자 M ,여자 W
        public boolean isMan;

        // 출연작
        public List<Movie> actedMovies;
    }

    // 배우 캐스팅 후보를 반환하는 함수
    public static List<Actor> casting(List<Movie> movies){
        List<Actor> recommendActors = new ArrayList<>();

        //전달받은 Movie 목록을 순회
        for(Movie movie : movies){
            if(movie.title.contains("도시")){
                for(Actor actor: movie.leadingActors){
                    if(actor.isMan == false && actor.actedMovies.size() >= 5 && actor.age >= 20 && actor.age < 30){
                        recommendActors.add(actor);
                    }
                }

                for(Actor actor:movie.supportActors){
                    if(actor.isMan == true && actor.actedMovies.size() >= 5 && actor.age >= 30 && actor.age < 40){
                        for(Movie actedMovie : actor.actedMovies){
                            if(actedMovie.genre == "공포"){
                                recommendActors.add(actor);
                            }
                        }
                    }
                }
            }
        }

        return recommendActors;
    };
}
