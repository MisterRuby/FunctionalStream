package funcInterface;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

enum Position {
    TOP("TOP"),
    MID("MID"),
    BOTTOM("BOTTOM");

    private String value;

    Position (String value) {
        this.value = value;
    }
}

abstract class Champion {
    String name;

    Champion (String name) {
        this.name = name;
    }
    abstract void move();
}

class Top extends Champion{
    Top (String name) {
        super(name);
    }
    void move() {
        System.out.println("탑에 " + name + "가 갑니다");
    }
}

class Mid extends Champion{
    Mid (String name) {
        super(name);
    }
    void move() {
        System.out.println("미드에 " + name + "가 갑니다");
    }
}

class Bot extends Champion{
    Bot (String name) {
        super(name);
    }
    void move() {
        System.out.println("바텀에 " + name + "가 갑니다");
    }
}

public class ConstructorRef {

    @Test
    public void testNotConstructorRef() {
        String[][] champs = {
                {"TOP", "티모"},
                {"MID", "아리"},
                {"BOTTOM", "베인"},
        };

        // 새로운 타입이 추가될 때마다 else if 조건절 추가
        for (String[] champ : champs) {
            Champion champion = null;
            if (Position.TOP.name().equals(champ[0])) {
                champion = new Top(champ[1]);
            } else if (Position.MID.name().equals(champ[0])) {
                champion = new Mid(champ[1]);
            } else if (Position.BOTTOM.name().equals(champ[0])) {
                champion = new Bot(champ[1]);
            }

            champion.move();
        }
    }

    @Test
    public void testConstructorRef() {
        // 새로운 타입이 추가될 때마다 해당 타입의 객체를 반환하는 Function을 Map에 추가
        Map<Position, Function<String, Champion>> champConstructorMap = new HashMap<>();
        champConstructorMap.put(Position.TOP, Top::new);
        champConstructorMap.put(Position.MID, Mid::new);
        champConstructorMap.put(Position.BOTTOM, Bot::new);

        String[][] champs = {
                {"TOP", "티모"},
                {"MID", "아리"},
                {"BOTTOM", "베인"},
        };

        for (String[] champ : champs) {
            Position position = Position.TOP.name().equals(champ[0]) ? Position.TOP
                    : Position.MID.name().equals(champ[0]) ? Position.MID : Position.BOTTOM;

            Champion champion = champConstructorMap.get(position).apply(champ[1]);
            champion.move();
        }
    }
}

