package by.bsuir.lr4;

import java.io.Serializable;
import java.util.List;

public class Numbers implements Serializable {
    List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Numbers{" +
                "numbers=" + numbers +
                '}';
    }
}
