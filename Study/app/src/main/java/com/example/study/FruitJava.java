package com.example.study;

import androidx.annotation.Nullable;

public class FruitJava {
    String fruitName;
    String fruitDescription;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FruitJava){
            //FruitJava클래스로 캐스팅한다.
            FruitJava another = (FruitJava) obj;

            return fruitName.equals(another.fruitName) && fruitDescription.equals(another.fruitDescription);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (fruitName==null ? 0 : fruitName.hashCode());
        hash = 31 * hash + (fruitDescription==null ? 0 : fruitDescription.hashCode());

        return hash;
    }
}
